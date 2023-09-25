package xhu.click.db.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.multipart.MultipartFile;
import xhu.click.common.entity.constants.RedisConstants;
import xhu.click.common.entity.enums.ResultCode;
import xhu.click.common.exception.BusinessException;
import xhu.click.common.utils.thread.LocalHolder;
import xhu.click.db.entity.dto.PostDto;
import xhu.click.db.entity.dto.UserDto;
import xhu.click.db.entity.enums.PostStatus;
import xhu.click.db.entity.enums.PostStrategy;
import xhu.click.db.entity.pojo.Collect;
import xhu.click.db.entity.pojo.Postliked;
import xhu.click.db.entity.vo.PageResult;
import xhu.click.db.entity.pojo.Post;
import xhu.click.db.entity.vo.PostVo;
import xhu.click.db.mapper.PostMapper;
import xhu.click.db.service.ICollectService;
import xhu.click.db.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xhu.click.db.service.IPostlikedService;
import xhu.click.file.config.MinioConfig;
import xhu.click.file.constants.FilePathConstants;
import xhu.click.file.service.MinioService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * <p>
 * post表 服务实现类
 * </p>
 *
 * @author eifying
 * @since 2023-09-17
 */
@Slf4j
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements IPostService {
    @Autowired
    PostMapper postMapper;

    @Autowired
    IPostlikedService postlikedService;

    @Autowired
    ICollectService collectService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    MinioService minioService;

    @Autowired
    MinioConfig minioConfig;



    /**
     * 获取图文
     *
     * @param cur
     * @param size
     * @param subject
     * @param strategy
     * @return
     */
    @Override
    public PageResult getPostPage(int cur, int size, int subject, int strategy) {
        QueryWrapper<Post> lambdaQueryWrapper = new QueryWrapper<>();
        // 主页推荐，获取所有类
        if(subject!=-1){
            lambdaQueryWrapper.eq("subject_id", subject);
        }
//        已发布
        lambdaQueryWrapper.eq("status", PostStatus.POSTED.getStatus());
        /**
         *  排序策略
         */
        if (strategy == PostStrategy.LATEST.getStatus()) {
            lambdaQueryWrapper.orderByDesc("add_time");
        } else if (strategy == PostStrategy.RESENT_REPLY.getStatus()) {
            lambdaQueryWrapper.orderByDesc("update_time");
        } else{
//            strategy == PostStrategy.Hottest.getStatus()
            lambdaQueryWrapper.orderByDesc("liked");
        }
//        分页查询
        PageHelper.startPage(cur, size);
        List<Post> posts = postMapper.selectList(lambdaQueryWrapper);
        PageInfo<Post> postPageInfo = new PageInfo<>(posts);
        long total = postPageInfo.getTotal();
        //查询出来的数据
        List<Post> list = postPageInfo.getList();
        //要转换成PostVo
        List<PostVo> postVoList = new ArrayList<>();
//        图片地址加前缀
        list.forEach(post -> {
            String photoUrl = post.getPhotoUrl();
            String[] arrayPhotos = photoUrl.split(",");
            List<String> photoList = Arrays.stream(arrayPhotos).map(photo ->
                    minioConfig.getPrefixUrl() + photo).toList();
            PostVo postVo = BeanUtil.copyProperties(post, PostVo.class);
            postVo.setPhotos(photoList);
            postVoList.add(postVo);
        });

        //封装pageResult
        PageResult<PostVo> pageResult = new PageResult();
        pageResult.setCurrent(cur);
        pageResult.setTotal(total);
        pageResult.setPageSize(size);
        pageResult.setList(postVoList);
        return pageResult;
    }

    /**
     * 删除图文
     *
     * @param id
     * @return 0, 1
     */
    @Override
    public int deletePostById(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userDto.getId());
        queryWrapper.eq("id", id);
        return postMapper.delete(queryWrapper);
    }

    /**
     * 是否已经点赞图文
     *
     * @param id
     * @return
     */
    @Override
    public boolean isLiked(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        // 判断是否点赞
        Double score = stringRedisTemplate.opsForZSet().score(RedisConstants.POST_IS_LIKED + id, userDto.getId());
        // 如果有记录,返回结果
        if (score != null) {
            if (score < 0.1) {
                return false;
            }
            return true;
        }
        // 没有记录，查询数据库
        Long liked = postlikedService.isLiked(new Postliked(id, userDto.getId(), null));
        // 添加到缓存
        stringRedisTemplate.opsForZSet().add(RedisConstants.POST_IS_LIKED + id, userDto.getId(), liked);
        if (liked == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean likedPost(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        boolean liked = isLiked(id);
        String key = RedisConstants.POST_IS_LIKED + id;
        //已经点赞
        if (liked) {
            boolean isSuccess = this.update().setSql("liked=liked-1").eq("id", id).update();
            postlikedService.delete(new Postliked(id, userDto.getId(), null));
            stringRedisTemplate.opsForZSet().add(key, userDto.getId(), 0);
        } else {
            // 未点赞
            boolean isSuccess = this.update().setSql("liked=liked+1").eq("id", id).update();
            postlikedService.save(new Postliked(id, userDto.getId(), LocalDateTime.now()));
            stringRedisTemplate.opsForZSet().add(key, userDto.getId(), LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        }
        return !liked;
    }

    @Override
    public boolean uploadPost(PostDto dto, MultipartFile[] files) {
        UserDto user = (UserDto) LocalHolder.getObject();
        Post post = BeanUtil.copyProperties(dto, Post.class);
        post.setUserId(user.getId());
        if(files!=null){
            List<String> list = minioService.uploadPhotos(FilePathConstants.POST_PATH,files);
            post.setPhotoUrl(list.stream().collect(Collectors.joining(",")));
        }else {
            post.setPhotoUrl("");
        }
        return this.save(post);
    }

    @Override
    public boolean isCollect(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        // 判断是否点赞
        Double score = stringRedisTemplate.opsForZSet().score(RedisConstants.POST_IS_COLLECT + id, userDto.getId());
        // 如果有记录,返回结果
        if (score != null) {
            if (score < 0.1) {
                return false;
            }
            return true;
        }
        // 没有记录，查询数据库
        Long collected = collectService.isCollect(new Collect(userDto.getId(), id, null));
        // 添加到缓存
        stringRedisTemplate.opsForZSet().add(RedisConstants.POST_IS_COLLECT + id, userDto.getId(), collected);
        if (collected == 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean collectPost(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        boolean collect = isCollect(id);
        String key = RedisConstants.POST_IS_COLLECT + id;
        //已经点赞
        if (collect) {
            boolean isSuccess = this.update().setSql("collect=collect-1").eq("id", id).update();
            collectService.delete(new Collect(userDto.getId(), id, null));
            stringRedisTemplate.opsForZSet().add(key, userDto.getId(), 0);
        } else {
            // 未点赞
            boolean isSuccess = this.update().setSql("collect=collect+1").eq("id", id).update();
            collectService.save(new Collect(userDto.getId(), id, LocalDateTime.now()));
            stringRedisTemplate.opsForZSet().add(key, userDto.getId(), LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        }
        return !collect;
    }

}
