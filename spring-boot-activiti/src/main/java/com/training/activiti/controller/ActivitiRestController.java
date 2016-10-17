/**
 * 
 */
package com.training.activiti.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangkai
 *
 */
@RestController
public class ActivitiRestController {
    @RequestMapping(value="/process", method= RequestMethod.POST)
    public void startProcessInstance() {
        
    }

}
