package com.aimprosoft.task4.dao;

import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.model.Department;


public interface DepartmentDao {

    Iterable<Department> list() throws AppException;

    Department getById(long id) throws AppException;

    Department getByName(String name) throws AppException;

    void add(Department department) throws AppException;

    void delete(long id) throws AppException;

    void update(long id, Department department) throws AppException;
}
