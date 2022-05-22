package com.czvisual.controller;

import com.czvisual.entity.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/gs")
public class LylController {
    @RequestMapping("nmsl")
    public Object test( Model model,@RequestBody(required = false) Dog dd ){
        if(dd == null){
            return new RedirectView("/404.html");
        }
        model.addAttribute("dog",dd);
        return "tms";
    }
    @RequestMapping("jb")
    @ResponseBody
    public Object jb(@RequestParam(name = "GS",required = false) String hast, HttpServletResponse resp, HttpServletRequest req){
        return hast;
    }
}
