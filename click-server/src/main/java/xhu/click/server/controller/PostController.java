package xhu.click.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xhu.click.common.entity.enums.ResultCode;
import xhu.click.common.entity.pojo.ResultVO;
import xhu.click.db.entity.vo.PageResult;
import xhu.click.db.service.IPostService;

@Api(tags = "图文")
@RequestMapping("/post")
@RestController
public class PostController {
    @Autowired
    IPostService postService;

    @ApiOperation("分页获取图文")
    @GetMapping("{cur}/{size}/{subject}/{strategy}")
    ResultVO getPage(@PathVariable int cur
            , @PathVariable int size
            , @PathVariable int subject
            , @PathVariable int strategy) {
        PageResult postPage = postService.getPostPage(cur, size, subject, strategy);
        return ResultVO.ok(postPage);
    }

    @ApiOperation("上传图文")
    @PostMapping
    ResultVO uploadPost(){


        return ResultVO.ok();
    }

    @ApiOperation("修改图文")
    @PutMapping
    ResultVO modifyPost(){

        return ResultVO.ok();
    }

    @ApiOperation("删除图文")
    @DeleteMapping("{id}")
    ResultVO deletePost(@PathVariable Long id){
        if(postService.deletePostById(id)==0){
            return ResultVO.error(ResultCode.NO_POST);
        }
        return ResultVO.ok();
    }

    @ApiOperation("获取是否点赞该图文,1为赞，0为否")
    @GetMapping("liked/{id}")
    ResultVO isLiked(@PathVariable Long id){
        if(!postService.isLiked(id)){
            return ResultVO.ok(0);
        }
        return ResultVO.ok(1);
    }

    @ApiOperation("点赞图文,,1为赞，0为否")
    @PostMapping("liked/{id}")
    ResultVO likedPost(@PathVariable Long id){
       if(!postService.likedPost(id)){
           return ResultVO.ok(0);
       }
        return ResultVO.ok(1);
    }


}
