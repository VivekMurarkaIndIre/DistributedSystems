package com.vivek.practice.rest;

import com.vivek.practice.dao.EmployeeDAO;
import com.vivek.practice.entity.Employee;
import com.vivek.practice.service.EmployeeService;
import com.vivek.practice.service.EmployeeServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;
    //

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/employee")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //get employee by id
    @GetMapping("/employee/{employeeID}")
    public  Employee getEmployee(@PathVariable int employeeID){
        Employee employee = employeeService.findById(employeeID);

        if(employee == null){
                throw new EntityNotFoundException("Cannot find element");
        }
        return employee;
    }

    //save employee to database
    @PostMapping("/employee")
    public  Employee addEmployee(@RequestBody Employee employee){
        //to avoid using id from the request
        employee.setId(0);
        employeeService.save(employee);
        return employee;
    }

    @PutMapping("/employee")
    public  Employee updateEmployee(@RequestBody Employee employee){

        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/employee/{employeeID}")
    public String deleteByID(@PathVariable int employeeID){
        Employee employee = employeeService.findById(employeeID);

        if(employee == null){
            throw new EntityNotFoundException("Cannot find element");
        }

        employeeService.deleteByID(employeeID);

        return "Record Deleted";
    }


}
