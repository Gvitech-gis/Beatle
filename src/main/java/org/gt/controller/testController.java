package org.gt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/start")
@Api(tags = "测试Swagger2",description="简单的API")
public class testController {
    // return json
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息note")
    @GetMapping("/getUser")
    public String getUser() {

        return "zhangsan";
    }

    @ApiOperation(value = "获取用户信息1", notes = "获取用户信息1")
    @GetMapping("/getUsers")
    public String getUsers() {
        return "zhangsan";
    }
}
