package test;

import com.yk.test.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangkai on 2017/5/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.yk.test"})
@EnableAspectJAutoProxy
public class TestAOP {

    @Autowired
    EmployeeService service;

    @Test
    public void test() {
        System.out.println("");
    }

    @Test
    public void testAOP() {
        Facade.Employee e = new Facade.Employee("yk", 20, "yangkai@hujang.com", "shanghai");

        service.exec(e);
    }
}
