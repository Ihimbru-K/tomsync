package com.ihimbru;


import jakarta.transaction.Transactional;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

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

    record NewCustomerRequest(String name, String email, Integer age){}

    //this method accepts a request, converts it to a customer object and saves it
    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest request){
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setEmail(request.email);
        customer.setAge(request.age);
        customerRepository.save(customer);

    }

    @DeleteMapping("{customerId}")
    public void deleteCustomer(@PathVariable("customerId") Integer id){
        customerRepository.deleteById(id);
    }

    @PutMapping("{customerId}")
    public void updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody NewCustomerRequest request) {
        // Find the existing customer by ID
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Update the customer with the new values
        existingCustomer.setName(request.name);
        existingCustomer.setEmail(request.email);
        existingCustomer.setAge(request.age);

        // Save the updated customer to the database
        customerRepository.save(existingCustomer);
    }





//    @PutMapping("{customerId}")
//    public void updateCustomer(@PathVariable("customerId") @RequestBody NewCustomerRequest request){
//        Customer customer = new Customer();
//        customer.setName(request.name);
//        customer.setAge(request.age);
//
//        customer.setEmail(request.email);
//        customerRepository.saveAndFlush(customer);
//
//    }


}
