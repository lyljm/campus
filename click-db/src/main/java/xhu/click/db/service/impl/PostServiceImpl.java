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
import xhu.click.db.entity.vo.PageResult;
import xhu.click.db.entity.pojo.Post;
import xhu.click.db.entity.vo.PostVo;
import xhu.click.db.mapper.PostMapper;
import xhu.click.db.service.IPostService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import xhu.click.file.config.MinioConfig;
import xhu.click.file.constants.FilePathConstants;
import xhu.click.file.service.MinioService;

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
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    MinioService minioService;

    @Autowired
    MinioConfig minioConfig;

    private ExecutorService es = Executors.newFixedThreadPool(9);

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
        lambdaQueryWrapper.eq("subject_id", subject);
//        已发布
        lambdaQueryWrapper.eq("status", PostStatus.POSTED.getStatus());
        /**
         *  排序策略
         */
        if (strategy == PostStrategy.LATEST.getStatus()) {
            lambdaQueryWrapper.orderByAsc("add_time");
        } else if (strategy == PostStrategy.Hottest.getStatus()) {
            lambdaQueryWrapper.orderByDesc("liked");
        } else if (strategy == PostStrategy.RESENT_REPLY.getStatus()) {
            lambdaQueryWrapper.orderByDesc("update_time");
        }
//        分页查询
        PageHelper.startPage(cur, size);
        List<Post> posts = postMapper.selectList(lambdaQueryWrapper);
        PageInfo<Post> postPageInfo = new PageInfo<>(posts);
        long total = postPageInfo.getTotal();
        //查询出来的数据
        List<Post> list = postPageInfo.getList();
        //要转换成PostVo
        List<PostVo>postVoList=new ArrayList<>();
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
        return stringRedisTemplate.opsForSet().isMember(RedisConstants.POST_IS_LIKED + id, userDto.getId());
    }

    @Override
    public boolean likedPost(Long id) {
        UserDto userDto = (UserDto) LocalHolder.getObject();
        boolean liked = isLiked(id);
        String key = RedisConstants.POST_IS_LIKED + id;
        //已经点赞
        if (liked) {
            boolean isSuccess = this.update().setSql("liked=liked-1").eq("id", id).update();
            stringRedisTemplate.opsForSet().remove(key, userDto.getId());
        } else {
//            未点赞
            boolean isSuccess = this.update().setSql("liked=liked+1").eq("id", id).update();
            stringRedisTemplate.opsForSet().add(key, userDto.getId());
        }
        return !liked;
    }

    @Override
    public boolean uploadPost(PostDto dto, MultipartFile[] files) {
        List<String> list = uploadPhotos(files);
        Post post = BeanUtil.copyProperties(dto, Post.class);
        post.setPhotoUrl(list.stream().collect(Collectors.joining(",")));
        //获取当前用户
        UserDto user = (UserDto) LocalHolder.getObject();
        post.setUserId(user.getId());
        return this.save(post);
    }

    @NotNull
    private List<String> uploadPhotos(MultipartFile[] files) {
        long begin = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(files.length);
        List<String> list = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString().replace("-", "") + "." + extension;
            //获取文件存储路径
            list.add(FilePathConstants.POST_PATH + fileName);
            Runnable task = () -> {
                minioService.putObject(FilePathConstants.POST_PATH, fileName, file);
                //执行任务
                latch.countDown();
            };
            es.submit(task);
        });
        long end = System.currentTimeMillis();
        //等待所有任务完成
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new BusinessException(ResultCode.FILE_UPLOAD_ERROR);
        }
        log.info("文件上传时间：{}s", (end - begin) / 1000);
        return list;
    }


}
