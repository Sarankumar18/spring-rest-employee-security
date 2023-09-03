package com.stepahead.springrest.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stepahead.springrest.entity.Employee;
import com.stepahead.springrest.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	// Inject Employee dao (using constructor Injection)
	private EmployeeService employeeService;

	public EmployeeRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	// expose "/employees" and return all the employees
	@GetMapping("/employees")
	public List<Employee> findAll() {

		return employeeService.findAll();
	}

	// add mapping for GET /employees/{employeeId}

	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		if (theEmployee == null) {
			throw new RuntimeException("Employee Id not Found0" + employeeId);
		}
		return theEmployee;
	}

	// add mapping for POST /employees - add new Employee
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {

		// also just incase they pass an Id in JSON... set Id to 0
		// save the employee in the database
		theEmployee.setId(0);
		Employee dbEmployee = employeeService.save(theEmployee);

		// return the saved employee
		return dbEmployee;
	}

	// add mapping for PUT /employees - update existing employee
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {

		employeeService.save(theEmployee);

		return theEmployee;
	}

	// add mapping for DELETE /employees/{Id} - delete Employee by Id
	@DeleteMapping("/employees/{theId}")
	public String deleteEmployee(@PathVariable int theId) {
		// find Employee by Id
		Employee theEmployee = employeeService.findById(theId);

		if (theEmployee == null)
			throw new RuntimeException("Employee Id Not Found:" + theId);

		employeeService.deleteById(theId);
		return "Deleted Employee Id: " + theId;
	}

}
