package xhu.click.server.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xhu.click.server.service.TestService;

@Api(tags = "测试controller")
@CrossOrigin
@RestController //@Controller + ReponseBody
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;
    @ApiOperation("测试")
    @GetMapping("Test")
    public Integer  getMes(){
       return testService.testService();
    }

}
