package org.gt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gis")
public class FileManagerController {
    @RequestMapping("/file_manager")
    public String file_manager() {
        return "file_manager";
    }
    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
