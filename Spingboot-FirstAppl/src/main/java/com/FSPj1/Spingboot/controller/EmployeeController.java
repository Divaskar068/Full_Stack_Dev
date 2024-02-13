package com.FSPj1.Spingboot.controller;

import com.FSPj1.Spingboot.exception.ResourceNotFoundException;
import com.FSPj1.Spingboot.model.Employee;
import com.FSPj1.Spingboot.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")         //We define a standard Url, Generally we use "/api/v1 " as an endpoint for rest APIs
                                  // V1- written to indicate that all the APIs are released as version 1
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;    //injecting employee repository

    //get all employees
    @GetMapping("/employees")          //So once you type the url in the web browser like "localhost:8080/api/v1/employees" you will get the list of employees
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.findAll();     //findAll() - We use to get list<Employee>
    }

    //create employees rest api
    @PostMapping("/employees") // This will handle Http post requests
    public Employee createEmployee(@RequestBody  Employee employee)       //post request contains adjacent request body and that we want to map directly to Employee object
    {
        return employeeRepository.save(employee);
    }

    //get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)         //@PathVariable will map/store the id from the url to the variable in the method
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: "+id));        //orElseThrow internally uses lamda expressions , we use lamda exp to implement a functional interface so give a lamda exp in bracket
        return ResponseEntity.ok(employee);           //we have to return a http status, so we need to use a response entity
    }

    //update employee rest api
    @PutMapping("/employees/{id}")               //PutMapping for update operation, handles put request.
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails)        //use RequestBody to map request json obj to java object basically get input from postman
    {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: "+id));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setRole(employeeDetails.getRole());
        employee.setEmailID(employeeDetails.getEmailID());

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);     //sending 200 OK
    }

    //Delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Map<String , Boolean>> deleteEmployee(@PathVariable Long id)                  //we want to return a deleted employee with a message so create a map
    {                                                                                                   //Because the delete method does not respond anything
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with id: "+id));        //Get existing employee info

        employeeRepository.delete(employee);    //NOTE: return type of delete method is void
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
