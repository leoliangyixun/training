/**
 * 
 */
package com.training.apns.api.controller;

import com.training.apns.core.model.Notification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author yangkai
 *
 */
@RestController
@RequestMapping(value = "/v1")
public class PushController {

    @RequestMapping(value = "/push", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> send(@RequestBody Notification notification) {
        return new ResponseEntity<Object>("hello", HttpStatus.OK);
    }

}
