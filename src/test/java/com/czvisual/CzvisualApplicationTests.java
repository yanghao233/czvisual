package com.czvisual;

import com.czvisual.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CzvisualApplicationTests {
        @Autowired
        private UserService service;

        @Test
        public void findUser(){
            System.out.println(service.findUserById(1).getUsername());
        }
    }
