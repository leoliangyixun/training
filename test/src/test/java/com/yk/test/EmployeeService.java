package com.yk.test;

import com.yk.test.aop.OpLogger;
import org.springframework.stereotype.Service;
import test.Facade;

/**
 * Created by yangkai on 2017/5/26.
 */
@Service
public class EmployeeService {

    @OpLogger("exec")
    public void exec(Object obj) {
        Facade.Person p = (Facade.Person)obj;
        System.out.println(p.getName() + " : " + p.getAge());
    }
}
