package com.stepahead.springrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stepahead.springrest.entity.Employee;

public interface EmployeeRepositoryDAO  extends JpaRepository<Employee, Integer>{

}
