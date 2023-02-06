package com.vivek.practice.rest;

import com.vivek.practice.dao.EmployeeDAO;
import com.vivek.practice.entity.Employee;
import com.vivek.practice.service.EmployeeService;
import com.vivek.practice.service.EmployeeServiceImpl;
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
            throw new RuntimeException("Employee id not found:" + employeeID);
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

    @PutMapping("/employees")
    public  Employee updateEmployee(@RequestBody Employee employee){

        employeeService.save(employee);
        return employee;
    }

    @DeleteMapping("/employee/{employeeID}")
    public String deleteByID(@PathVariable int employeeID){
        Employee employee = employeeService.findById(employeeID);

        if(employee == null){
            throw new RuntimeException("Employee id not found:" + employeeID);
        }

        employeeService.deleteByID(employeeID);

        return "Deleted";
    }


}
