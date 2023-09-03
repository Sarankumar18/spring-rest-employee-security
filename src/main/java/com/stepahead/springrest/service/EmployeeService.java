package com.stepahead.springrest.service;

import java.util.List;

import com.stepahead.springrest.entity.Employee;

public interface EmployeeService {

	public List<Employee> findAll();

	Employee findById(int theId);

	Employee save(Employee theEmployee);

	void deleteById(int theId);

}
