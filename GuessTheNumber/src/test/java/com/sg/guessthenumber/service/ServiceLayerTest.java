/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.guessthenumber.service;

import com.sg.guessthenumber.TestApplicationConfiguration;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


// Service Layer class
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class ServiceLayerTest {
    
    @Autowired
    ServiceLayer serv;
    
    public ServiceLayerTest() {
    }
    
    @Test
    public void testDetermineRes1() {
        String guess = "1234";
        String ans = "2159";
        String res = serv.determineResult(guess, ans);

        assertEquals("e:0:p:2", res);
    }
    
    @Test
    public void testDetermineRes2() {
        String guess = "1234";
        String ans = "1234";
        String res = serv.determineResult(guess, ans);
        
        assertEquals("e:0:p:4", res);
    }
    
    @Test
    public void testDetermineRes3() {
        String guess = "1234";
        String ans = "4321";
        String res = serv.determineResult(guess, ans);
        
        assertEquals("e:0:p:4", res);
    }
    
    @Test
    public void testDetermineRes4() {
        String guess = "1234";
        String ans = "1324";
        String res = serv.determineResult(guess, ans);
        
        assertEquals("e:2:p:2", res);
    }
    
    @Test
    public void testDetermineRes5() {
        String guess = "1234";
        String ans = "5678";
        String res = serv.determineResult(guess, ans);
        
        assertEquals("e:0:p:0", res);
    }
    
}
