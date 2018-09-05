package org.gt.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/start")
public class testController {
    // return json
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息note")
    @GetMapping("/getUser")
    public String getUser() {

        return "zhangsan";
    }


}
