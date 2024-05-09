package com.ihimbru;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//**
// SpringBootApplication controller basically autoconfigures a new springboot project
//
// *///



//this annotation declares that this is a spring application
@SpringBootApplication
// this annotation helps us create methods that will be exposed as rest APIs
//all methods under this controller return a response in JSON format
@RestController
@RequestMapping("api/v1/customers")    // this will return an empty list from the getcustomers method
public class Main {

    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
       // System.out.println("Hello World!");
        SpringApplication.run(Main.class, args); //runnable springboot application
    }

    @GetMapping("/greet") //Its for http CRUD request (get request)
    public GreetResponse greet(){
       GreetResponse response = new  GreetResponse("Hello ma pipo dem, I salot wena", new Person("Ihimbru"), List.of("Java, Dart, Python, Kotlin, Typescript")
       );
       return response;
    }
    record Person(String person){}

    record GreetResponse(String greet, Person person, List<String> favProgLang){}


    @GetMapping
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }


}
