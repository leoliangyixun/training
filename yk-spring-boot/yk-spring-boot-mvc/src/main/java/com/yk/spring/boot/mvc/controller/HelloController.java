/**
 * 
 */
/**
 * @author yangkai
 *
 */
package com.yk.spring.boot.mvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yk.spring.boot.mvc.core.UserService;
import com.yk.spring.boot.mvc.rest.Msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @Autowired
    private UserService userService;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    @ResponseBody
    public String greeting() {
        return "hello world";
      
    }
    
    @RequestMapping(value="/test", method=RequestMethod.GET)
    public void test(Model model,HttpServletResponse response) throws IOException {
       
        model.addAttribute("name", "yangkai");
        response.getWriter().write(model.toString());
      
    }
    
    @RequestMapping(value="/test2", method=RequestMethod.GET)
    @ResponseBody
    public Msg test2(Model model) {
        model.addAttribute("name", "yangkai");
        return Msg.ok(model);
      
    }
    
    @RequestMapping(value="/test3", method=RequestMethod.GET)
    public String test3(@RequestParam(value="name", required=false, defaultValue="yangkai") String name, Model model) {
        System.out.println("/hello");
        model.addAttribute("name", name);
        return "hello";
      
    }

    @RequestMapping(value="/test4", method=RequestMethod.GET)
    @ResponseBody
    public void test4(Model model) {
        Msg msg = Msg.ok();
    }

    @RequestMapping(value="/test5", method=RequestMethod.GET)
    public void test5(Model model) {
        Msg msg = Msg.ok();
    }

    @RequestMapping(value="/test6", method=RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public Msg test6() {
        return Msg.ok();
    }

    @RequestMapping(value="/test7", method=RequestMethod.GET)
    @ResponseBody
    public Msg test7(Model model) {
        Msg msg = Msg.ok();
        userService.execute();
        return msg;
    }


    @RequestMapping(value="/test8", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> test8(HttpServletRequest req, HttpServletResponse response) {
        userService.execute();
        return new ResponseEntity<Object>("null", HttpStatus.MULTI_STATUS);
    }

    @RequestMapping(value="/test9", method=RequestMethod.GET)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> test9(HttpServletRequest req, HttpServletResponse response) {
        try {
            Thread.sleep(1000);
            throw new RuntimeException("error");
        } catch (InterruptedException e) {

        }

        return new ResponseEntity<Object>("null", HttpStatus.MULTI_STATUS);
    }

    @RequestMapping(value="/test10", method=RequestMethod.GET)
    public ResponseEntity<Object> test10(HttpServletRequest req, HttpServletResponse response) {
        try {
            Thread.sleep(1000);
            throw new RuntimeException("error");
        } catch (InterruptedException e) {

        }

        return new ResponseEntity<Object>("null", HttpStatus.MULTI_STATUS);
    }
}