package com.aimprosoft.task4.service.impl;

import com.aimprosoft.task4.dao.EmployeeDao;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.model.Employee;
import com.aimprosoft.task4.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional
    public void addEmployee(Employee employee) throws AppException {
        employeeDao.add(employee);
    }

    @Transactional
    public Iterable<Employee> listEmployees(String department) throws AppException {
        return employeeDao.list(department);
    }

    @Transactional
    public void deleteEmployee(long id) throws AppException {
        employeeDao.delete(id);
    }

    @Transactional
    public void editEmployee(long id, Employee employee) throws AppException {
        employeeDao.update(id, employee);
    }

    @Transactional
    public Employee getById(long id) throws AppException {
        return employeeDao.getById(id);
    }

    @Transactional
    public Employee getByEmail(String email) throws AppException {
        return employeeDao.getByEmail(email);
    }

    @Transactional
    public boolean isEmailUnique(long id, String email) throws AppException{
        Employee existing = getByEmail(email);
        return existing==null || existing.getId()==id;
    }

}
