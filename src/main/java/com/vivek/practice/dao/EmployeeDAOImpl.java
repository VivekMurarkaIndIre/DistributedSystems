package com.vivek.practice.dao;

import com.vivek.practice.entity.Employee;


import jakarta.persistence.EntityManager;

import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Hibernate implementation
 */
@Repository
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

       // Query q2= entityManager.createQuery("from Employee");

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
        //save or update if id=0
        Employee dbEmployee= entityManager.merge(employee);

        // update with id  from db..so we can use auto generate id
        employee.setId(dbEmployee.getId());
    }

    @Override
    public void deleteByID(int id) {

        Query  query = entityManager.createQuery("delete from Employee  where id=:employeeId");
        //set the value of parameter employeeId in above query
        query.setParameter("employeeId",id);

        query.executeUpdate();
    }
}
