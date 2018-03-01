package com.aimprosoft.task4.dao;

import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.model.Employee;

public interface EmployeeDao {

    Iterable<Employee> list(String departmentName) throws AppException;

    Employee getById(long id) throws AppException;

    Employee getByEmail(String email) throws AppException;

    void add(Employee employee) throws AppException;

    void delete(long id) throws AppException;

    void update(long id, Employee employee) throws AppException;
}
