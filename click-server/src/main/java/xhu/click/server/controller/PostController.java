package xhu.click.server.controller;

import cn.hutool.http.body.MultipartBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xhu.click.common.entity.enums.ResultCode;
import xhu.click.common.entity.pojo.ResultVO;
import xhu.click.db.entity.dto.PostDto;
import xhu.click.db.entity.pojo.Post;
import xhu.click.db.entity.vo.PageResult;
import xhu.click.db.service.IPostService;

import java.util.Arrays;

@Api(tags = "图文")
@RequestMapping("/post")
@RestController
public class PostController {
    @Autowired
    IPostService postService;

    @ApiOperation("分页获取图文")
    @GetMapping("{cur}/{size}/{subject}/{strategy}")
    ResultVO<PageResult> getPage(@PathVariable int cur
            , @PathVariable int size
            , @PathVariable int subject
            , @PathVariable int strategy) {
        PageResult postPage = postService.getPostPage(cur, size, subject, strategy);
        return ResultVO.ok(postPage);
    }

    @ApiOperation("上传图文")
    @PostMapping
    ResultVO uploadPost(@ModelAttribute PostDto dto ) {
        if (dto.getFiles().length>9){
            return ResultVO.error(ResultCode.FILE_TOO_LONG);
        }
        postService.uploadPost(dto,dto.getFiles());
        return ResultVO.ok();
    }

    @ApiOperation("修改图文")
    @PutMapping
    ResultVO modifyPost() {
/**
 * todo
 */
        return ResultVO.ok();
    }

    @ApiOperation("删除图文")
    @DeleteMapping("{id}")
    ResultVO deletePost(@PathVariable Long id) {
        if (postService.deletePostById(id) == 0) {
            return ResultVO.error(ResultCode.NO_POST);
        }
        return ResultVO.ok();
    }

    @ApiOperation("获取是否点赞该图文,1为赞，0为否")
    @ApiImplicitParam(name = "id",value = "图文的id")
    @GetMapping("liked/{id}")
    ResultVO<Integer> isLiked(@PathVariable Long id) {
        if (!postService.isLiked(id)) {
            return ResultVO.ok(0);
        }
        return ResultVO.ok(1);
    }

    @ApiOperation("点赞图文,,1为赞，0为否")
    @ApiImplicitParam(name = "id",value = "图文的id")
    @PostMapping("liked/{id}")
    ResultVO<Integer> likedPost(@PathVariable Long id) {
        if (!postService.likedPost(id)) {
            return ResultVO.ok(0);
        }
        return ResultVO.ok(1);
    }


}
