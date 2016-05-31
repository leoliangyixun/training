/**
 * 
 */
package com.training.mongodb;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.training.mongodb.dao.impl.UserDaoImpl;
import com.training.mongodb.model.User;

/**
 * @author yangkai
 *
 */
public class MongoDBConnectionTest {

    public MongoDBConnectionTest() {
       
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = null;
        context = new ClassPathXmlApplicationContext("spring-mongo.xml");

        UserDaoImpl userDao = context.getBean(UserDaoImpl.class);
        userDao._test();
        User entity1 = new User();
        entity1.setId("6");
        entity1.setAge(2);
        entity1.setBirth(new Date());
        entity1.setPassword("asdfasdf");
        entity1.setRegionName("北京");
        entity1.setWorks(2);
        userDao.insert(entity1);
        userDao.update(entity1);
        userDao.createCollection();

/*        List<User> list = userDao.findList(0, 10);
        for (User e : list) {
            System.out.println("all - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword()
                    + ", regionName=" + e.getRegionName() + ", special=" + Arrays.toString(e.getSpecial()) + ", name="
                    + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());
        }

        list = userDao.findListByAge(1);
        for (User e : list) {
            System.out.println("age=1 - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword()
                    + ", regionName=" + e.getRegionName() + ", special=" + Arrays.toString(e.getSpecial()) + ", name="
                    + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());
        }

        User e = userDao.findOne("1");
        System.out.println("id=1 - id=" + e.getId() + ", age=" + e.getAge() + ", password=" + e.getPassword()
                + ", regionName=" + e.getRegionName() + ", special=" + Arrays.toString(e.getSpecial()) + ", name="
                + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());

        e = userDao.findOneByUsername("limingnihao");
        System.out.println("username=limingnihao - id=" + e.getId() + ", age=" + e.getAge() + ", password="
                + e.getPassword() + ", regionName=" + e.getRegionName() + ", special=" + Arrays.toString(e.getSpecial())
                + ", name=" + e.getName().getUsername() + "-" + e.getName().getNickname() + ", birth=" + e.getBirth());*/
        context.close();

    }

}
