package com.ballfuns.controller;

import org.springframework.stereotype.Controller;

/**
 * Created by Revol on 2015/12/15.
 */
@Controller
public class testController {
   // @RequestMapping("/")
    public String home(){
        return "index";
    }
}
