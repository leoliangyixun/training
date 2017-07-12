package com.yk.test;

import com.yk.test.aop.OpLogger;
import org.springframework.stereotype.Service;
import test.Main;

/**
 * Created by yangkai on 2017/5/26.
 */
@Service
public class EmployeeService {

    @OpLogger("exec")
    public void exec(Object obj) {
        Main.Person p = (Main.Person)obj;
        System.out.println(p.getName() + " : " + p.getAge());
    }
}
