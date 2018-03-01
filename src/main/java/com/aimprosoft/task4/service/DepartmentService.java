package com.aimprosoft.task4.service;

import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.model.Department;

public interface DepartmentService {

    Iterable<Department> listDepartments() throws AppException;

    void addDepartment(Department department) throws AppException;

    void deleteDepartment(long id) throws AppException;

    void editDepartment(long id, Department department) throws AppException;

    Department getById(long id) throws AppException;

    Department getByName(String name) throws AppException;

    boolean isNameUnique(long id, String name) throws AppException;
}
