package com.training.mongodb.dao.impl;  
  
import java.util.List;  
import java.util.Set;  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.data.domain.Sort;  
import org.springframework.data.domain.Sort.Direction;  
import org.springframework.data.domain.Sort.Order;  
import org.springframework.data.mongodb.core.MongoTemplate;  
import org.springframework.data.mongodb.core.query.Criteria;  
import org.springframework.data.mongodb.core.query.Query;  
import org.springframework.data.mongodb.core.query.Update;  
import org.springframework.stereotype.Repository;  
  
import com.mongodb.DB;
import com.training.mongodb.model.User;  
  
@Repository  
public class UserDaoImpl {  
  
    public static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);  
  
    @Autowired  
    private MongoTemplate mongoTemplate;  
  
    
    public void _test() {  
        Set<String> colls = this.mongoTemplate.getCollectionNames();  
        for (String coll : colls) {  
            logger.info("CollectionName=" + coll);  
        }  
        DB db = this.mongoTemplate.getDb();  
        logger.info("db=" + db.toString());  
    }  
  
     
    public void createCollection() {  
        if (!this.mongoTemplate.collectionExists(User.class)) {  
            this.mongoTemplate.createCollection(User.class);  
        }  
    }  
  
     
    public List<User> findList(int skip, int limit) {  
        Query query = new Query();  
        query.with(new Sort(new Order(Direction.ASC, "_id")));  
        query.skip(skip).limit(limit);  
        return this.mongoTemplate.find(query, User.class);  
    }  
  
     
    public List<User> findListByAge(int age) {  
        Query query = new Query();  
        query.addCriteria(new Criteria("age").is(age));  
        return this.mongoTemplate.find(query, User.class);  
    }  
  
     
    public User findOne(String id) {  
        Query query = new Query();  
        query.addCriteria(new Criteria("_id").is(id));  
        return this.mongoTemplate.findOne(query, User.class);  
    }  
  
     
    public User findOneByUsername(String username) {  
        Query query = new Query();  
        query.addCriteria(new Criteria("name.username").is(username));  
        return this.mongoTemplate.findOne(query, User.class);  
    }  
  
     
    public void insert(User user) {  
        this.mongoTemplate.insert(user);  
  
    }  
  
     
    public void update(User user) {  
        Query query = new Query();  
        query.addCriteria(new Criteria("_id").is(user.getId()));  
        Update update = new Update();  
        update.set("age", user.getAge());  
        update.set("password", user.getPassword());  
        update.set("regionName", user.getRegionName());  
        update.set("special", user.getSpecial());  
        update.set("works", user.getWorks());  
        update.set("name", user.getName());  
        this.mongoTemplate.updateFirst(query, update, User.class);  
  
    }  
  
}  