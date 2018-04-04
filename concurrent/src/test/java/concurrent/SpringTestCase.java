/**
 *
 */
package concurrent;

import com.yk.concurrent.config.TestConfig;

import lombok.extern.slf4j.Slf4j;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @author yangkai
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"com.yk.concurrent"})
@Import(value = {TestConfig.class})// can't work if not import
public class SpringTestCase {
  public ApplicationContext ctx = new AnnotationConfigApplicationContext(TestConfig.class);
   // public ApplicationContext ctx = new AnnotationConfigWebApplicationContext(TestConfig.class);
    //


    @Test
    public void start() {
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        log.info("{}");
    }




}
