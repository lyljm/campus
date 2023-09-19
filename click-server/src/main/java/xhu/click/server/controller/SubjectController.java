package xhu.click.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xhu.click.common.entity.pojo.ResultVO;
import xhu.click.db.entity.pojo.Subject;
import xhu.click.db.service.ISubjectService;

import java.util.List;

@Api(tags = "主题")
@RequestMapping("/topic")
@RestController
public class SubjectController {
    @Autowired
    ISubjectService subjectService;

    @ApiOperation("获取所有的主题")
    @GetMapping("all")
    public ResultVO getAll(){
        List<Subject> list = subjectService.list();
        return ResultVO.ok(list);
    }


}
