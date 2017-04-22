package com.tianzeng.react; /**
 * Created by tianzeng on 2017-03-04.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
@EnableAutoConfiguration
//重点
@ServletComponentScan
public class SampleController extends WebMvcConfigurerAdapter {
    public static void main(String[] args){
        SpringApplication.run(SampleController.class,args);
    }
}