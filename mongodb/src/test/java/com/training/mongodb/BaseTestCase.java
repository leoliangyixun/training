/**
 * 
 */
package com.training.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/**
 * @author yangkai
 *
 */
public class BaseTestCase {
    
    public static String HOST = "localhost";
    public static Integer PORT = 27017;
    
    @Test
    public void testConnection() {
        MongoClient mongoClient = new MongoClient(HOST, PORT);
                DB db = mongoClient.getDB( "training" );
                System.out.println(db.getCollectionNames());
    }
    
    @Test
    public void testCridential() {
        try {

            MongoCredential credential = MongoCredential.createCredential("root", "training",
                    "rooot".toCharArray());
            MongoClient mongoClient = new MongoClient(new ServerAddress("localhost"), Arrays.asList(credential));
            DB db = mongoClient.getDB("training");
            System.out.println(db);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Test
    public void testInsert() {
        try {
            MongoClient mongoClient = new MongoClient(HOST, PORT);
            DB db = mongoClient.getDB("training");
            DBCollection coll = db.getCollection("users");
            DBObject doc = new BasicDBObject("name", "owen").append("age", 47).append("email", "owen@mail.com")
                    .append("phone", "111-222-333");
            coll.insert(doc);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Test
    public void testInsertJavaBean() {
        User user = new User("jiqingchuan", 28, "13166016298");
        System.out.println(user);

        MongoClient mongoClient = new MongoClient(HOST, PORT);
        DB db = mongoClient.getDB("training");
        DBCollection coll = db.getCollection("users");

        List<DBObject> kids = new ArrayList<>();
        kids.add(new BasicDBObject("name", "mike"));
        kids.add(new BasicDBObject("name", "faye"));
        DBObject doc = new BasicDBObject("name", "john").append("age", 35).append("kids", kids).append("info",
                new BasicDBObject("email", "john@mail.com").append("phone", "876-134-667"));
        coll.insert(doc);

    }
    
    public static class User {
        private String name;
        private Integer age;
        private String phone;
        public User(String name, Integer age, String phone) {
            super();
            this.name = name;
            this.age = age;
            this.phone = phone;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public Integer getAge() {
            return age;
        }
        public void setAge(Integer age) {
            this.age = age;
        }
        public String getPhone() {
            return phone;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }
        @Override
        public String toString() {
            return "User [name=" + name + ", age=" + age + ", phone=" + phone + "]";
        }
        
        
        
    }
}
