package com.vivek.practice.dao;
import java.util.List;
import com.vivek.practice.entity.Employee;

public interface EmployeeDAO {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteByID(int id);
}
