package com.ihims;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;





///**
// SpringBootApplication controller basically autoconfigures a new springboot project
//
// *///
@SpringBootApplication //this annotation declares that this is a spring application
@RestController  // this annotation helps us create methods that will be exposed as rest APIs
//all methods under this controller return data in JSON format
public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world");
        SpringApplication.run(Main.class, args);

    //tomcat is an embedded web server which provides a pure Java http web server
        // environment in which java code can run, hence it is a java web server


    }

    @GetMapping("/greet") //used in http get requests
    public getResponse greet(){
        return new  getResponse("Hello");
    }


    record getResponse(String greet){}

}
