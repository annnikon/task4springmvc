package com.aimprosoft.task4.service;

import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.model.Employee;


  public interface EmployeeService {

      void addEmployee(Employee employee) throws AppException;

      Iterable<Employee> listEmployees(String department) throws AppException;

      void deleteEmployee(long id) throws AppException;

      void editEmployee(long id, Employee employee) throws AppException;

      Employee getById(long id) throws AppException;

      Employee getByEmail(String email) throws AppException;

      boolean isEmailUnique(long id, String email) throws AppException;

  }
