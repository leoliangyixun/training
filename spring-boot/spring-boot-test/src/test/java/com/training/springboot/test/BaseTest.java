package com.training.springboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.training.springboot.test.main.AppStart;

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = AppStart.class)
@WebAppConfiguration
public class BaseTest {

    @Test
    public void test() {

    }
/*
    @Value("${local.server.port}")
    int port;

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
    }*/
   
}
