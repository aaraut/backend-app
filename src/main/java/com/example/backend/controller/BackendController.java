package com.example.backend.controller;

import com.example.backend.entity.Designation;
import com.example.backend.entity.Employee;
import com.example.backend.repository.DesignationRepository;
import com.example.backend.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BackendController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DesignationRepository designationRepository;

    // Endpoint to return all available designations
    @GetMapping("/designations")
    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    // Endpoint to return first name and last name based on ID
    @GetMapping("/employee/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable long id) {
        Employee employee = employeeRepository.findById(id);
        if (employee != null) {
            return new EmployeeResponse(employee.getFirstName(), employee.getLastName());
        } else {
            // Returning a response with null values if employee not found
            return new EmployeeResponse(null, null);
        }
    }

    // Endpoint to return salary based on query parameter
    @GetMapping("/salary")
    public String getSalaryByQueryParam(@RequestParam double salary) {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            if (employee.getSalary().equals(salary)) {
                return "Employee Name: " + employee.getFirstName() + employee.getLastName();
            }
        }
        return "No employee with salary: " + salary;
    }
}
