/**
 * 
 */
/**
 * @author yangkai
 *
 */
package com.yk.spring.boot.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.training.ykgwj.model.Msg;

@Controller
public class HelloController {

    @RequestMapping(value="/filter", method=RequestMethod.GET)
    @ResponseBody
    public Msg filter(Model model) {
       return Msg.fail("success", 200);
    }

}