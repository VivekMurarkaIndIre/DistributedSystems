package com.vivek.practice.service;

import com.vivek.practice.repository.EmployeeDataJPARepository;
import com.vivek.practice.entity.Employee;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class EmployeeServiceImplWithDataJPA implements EmployeeService{

    private EmployeeDataJPARepository employeeRepository;

    @Autowired
    public EmployeeServiceImplWithDataJPA(EmployeeDataJPARepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }else{
            throw new EntityNotFoundException("Cannot find element");
        }

    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteByID(int id) {

        employeeRepository.deleteById(id);
    }
}
