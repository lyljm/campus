package xhu.click.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xhu.click.common.entity.pojo.ResultVO;
import xhu.click.db.service.ICommentService;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Api(tags = "评论相关")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    ICommentService commentService;

    @ApiOperation("获取所有的评论")
    @GetMapping("all/{id}")
    @ApiImplicitParam(name = "id",value = "图文的id")
    ResultVO<Map> commentPost(@NotNull @PathVariable Long id){
        Map commentById = commentService.getCommentById(id);
        return ResultVO.ok(commentById);
    }

    @ApiOperation("判断用户是否点赞")
    @GetMapping("isliked/{id}")
    ResultVO<Integer> isLiked(@NotNull@PathVariable Long id){
        boolean liked = commentService.isLiked(id);
        return ResultVO.ok(liked);
    }


    @ApiOperation("点赞评论")
    @ApiImplicitParam(name = "id",value = "评论的id")
    @GetMapping("liked/{id}")
    ResultVO<Integer> likeComment(@NotNull @PathVariable Long id){
        boolean liked = commentService.likeComment(id);
        if(!liked){
            return ResultVO.ok(0);
        }
        return ResultVO.ok(1);
    }


}
