package com.aimprosoft.task4.service.impl;

import com.aimprosoft.task4.dao.DepartmentDao;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.model.Department;
import com.aimprosoft.task4.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentDao departmentDao;

    public void setDepartmentDao(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Transactional
    public void addDepartment(Department department) throws AppException {
        departmentDao.add(department);
    }

    @Transactional
    public Iterable<Department> listDepartments() throws AppException {
        return departmentDao.list();
    }

    @Transactional
    public void deleteDepartment(long id) throws AppException {
        departmentDao.delete(id);
    }

    @Transactional
    public void editDepartment(long id, Department department) throws AppException {
        departmentDao.update(id, department);
    }

    @Transactional
    public Department getById(long id) throws AppException {
        return departmentDao.getById(id);
    }

    @Transactional
    public Department getByName(String name) throws AppException {
        return departmentDao.getByName(name);
    }

    @Transactional
    public boolean isNameUnique(long id, String name) throws AppException {
        Department existing = getByName(name);
        return existing == null || existing.getId() == id;
    }

}
