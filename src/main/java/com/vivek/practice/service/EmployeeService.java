package com.vivek.practice.service;

import com.vivek.practice.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteByID(int id);
}
