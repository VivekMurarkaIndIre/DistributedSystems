package com.vivek.practice.dao;

import com.vivek.practice.entity.Employee;


import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Hibernate implementation
 */
@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO{

    //define fields for entity manager

    @PersistenceContext
    private EntityManager entityManager;

    //setup constructor injection


    @Override

    public List<Employee> findAll() {

        //get current hibernate session
        //Session currentSession = entityManager.unwrap(Session.class);

        //create a query
        String jpql = "SELECT c FROM Employee c";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);

        //execute query and get result
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void save(Employee employee) {
        entityManager.persist(employee);
    }

    @Override
    public void deleteByID(int id) {

    }
}
