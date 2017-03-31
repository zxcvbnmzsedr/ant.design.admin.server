package com.tianzeng.react; /**
 * Created by tianzeng on 2017-03-04.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableAutoConfiguration
public class SampleController {
    public static void main(String[] args){
        SpringApplication.run(SampleController.class,args);
    }

}