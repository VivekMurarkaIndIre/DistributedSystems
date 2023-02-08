package com.vivek.practice.repository;

import com.vivek.practice.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Here we are escaping CRUD operation we did using JPA earlier by now using spring data JPA
 * It is observed that direct JDBC query are faster than JPA
 * Spring Data JPA is almost as fast as JDBC query and faster than JPA for simple databases with minimal relations
 * less boilerplate code is the biggest advantage
 */
public interface EmployeeDataJPARepository extends JpaRepository<Employee,Integer> {
}
