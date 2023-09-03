package com.stepahead.springrest.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.stepahead.springrest.dao.EmployeeRepositoryDAO;
import com.stepahead.springrest.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	// define employeeDAO field
	EmployeeRepositoryDAO employeeRepositoryDAO;

	// inject employeeDAO DAO using constructor injection
	public EmployeeServiceImpl(EmployeeRepositoryDAO employeeDAO) {
		this.employeeRepositoryDAO = employeeDAO;
	}

	@Override
	public List<Employee> findAll() {
		return employeeRepositoryDAO.findAll();
	}

	@Override
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepositoryDAO.findById(theId);

		Employee theEmployee = null;
		if (result.isPresent()) {
			theEmployee = result.get();
		} else {
			throw new RuntimeException("Employee Id Not Found: "+ theId);
		}
		return theEmployee;
	}

	@Override
	public Employee save(Employee theEmployee) {
		return employeeRepositoryDAO.save(theEmployee);
	}

	@Override
	public void deleteById(int theId) {
		employeeRepositoryDAO.deleteById(theId);
	}

}
