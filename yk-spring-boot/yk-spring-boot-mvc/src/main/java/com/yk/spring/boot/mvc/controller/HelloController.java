/**
 * 
 */
/**
 * @author yangkai
 *
 */
package com.yk.spring.boot.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    public String greeting() {
        return "hello world";
      
    }
    
    @RequestMapping(value="/test", method=RequestMethod.GET)
    public void greeting(Model model,HttpServletResponse response) throws IOException {
       
        model.addAttribute("name", "yangkai");
        response.getWriter().write(model.toString());
      
    }
    
    @RequestMapping(value="/test2", method=RequestMethod.GET)
    public Model test(Model model) {
        model.addAttribute("name", "yangkai");
        return model;
      
    }
    
    @RequestMapping(value="/test3", method=RequestMethod.GET)
    public String greeting(@RequestParam(value="name", required=false, defaultValue="yangkai") String name, Model model) {
        System.out.println("/hello");
        model.addAttribute("name", name);
        return "hello";
      
    }
}