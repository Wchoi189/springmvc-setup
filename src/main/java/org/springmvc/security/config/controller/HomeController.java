package org.springmvc.security.config.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public ModelAndView homeController (){
        ModelAndView mav = new ModelAndView("jsp/index.jsp");
        return mav;
    }

    @GetMapping("/home")
    public ModelAndView htmlController (){
        ModelAndView mav = new ModelAndView("index.html");
        return mav;
    }
}
