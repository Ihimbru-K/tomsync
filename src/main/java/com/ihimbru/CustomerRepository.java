package com.ihimbru;

import org.springframework.data.jpa.repository.JpaRepository;


//This is an interface that would help us perform CRUD operations on the database without necessarily
//writing sql commands directly
public interface CustomerRepository extends JpaRepository<Customer, Integer > {

///
}
