package com.yk.spring.boot.mvc.core;

import com.yk.spring.boot.mvc.exception.RequestException;
import org.springframework.stereotype.Service;

/**
 * Created by yangkai on 2016/10/23.
 */
@Service
public class UserService {

    public void execute() {
        throw new RequestException(500, "system error");
    }
}
