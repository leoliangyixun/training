package com.training.spring.boot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
/*
@RunWith(SpringJUnit4ClassRunner.class)   // 1
@SpringApplicationConfiguration(classes = Application.class)   // 2
@WebAppConfiguration   // 3
@IntegrationTest("server.port:0")   // 4
public class BaseTest {
 

 
    @Value("${local.server.port}")   // 6
    int port;
 

 
    // 10
    @Test
    public void canFetchMickey() {
      
 
        when().
                get("/characters/{id}", mickeyId).
        then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.is("Mickey Mouse")).
                body("id", Matchers.is(mickeyId));
    }
 
    @Test
    public void canFetchAll() {
        when().
                get("/characters").
        then().
                statusCode(HttpStatus.SC_OK).
                body("name", Matchers.hasItems("Mickey Mouse", "Minnie Mouse", "Pluto"));
    }
 
    @Test
    public void canDeletePluto() {
       
 
        when()
                .delete("/characters/{id}", null).
        then().
                statusCode(HttpStatus.NO_CONTENT);
    }
   
}
*/
