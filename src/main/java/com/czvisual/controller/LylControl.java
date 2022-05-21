package com.czvisual.controller;

import com.czvisual.entity.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author laiyulong
 * @date 2022/5/21 10:58
 */
@Controller
@RequestMapping("/gs")
public class LylControl {
    @RequestMapping("nmsl")
    public String test(Model model,@RequestBody(required = false) String dd ){
        //dd.setAge(dd.getAge());
        Dog dog = new Dog();
        dog.setAge(13);
        dog.setName("EJIFIJ");
        model.addAttribute("tmsl",dog);
        //dd.setName("hah");
        return "tmslmb";
    }
}
