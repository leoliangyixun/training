/**
 * 
 */
package com.training.springboot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.springboot.mvc.model.Greeting;

/**
 * @author yangkai
 *
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public Object index() {
    	Greeting greeting = new Greeting(0, "xxx");
        return greeting;
    }
}
