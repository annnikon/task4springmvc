package com.aimprosoft.task4.dao.springhibernate;

import com.aimprosoft.task4.dao.DepartmentDao;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.exception.NotEmptyEmployeesException;
import com.aimprosoft.task4.model.Department;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceException;

@Repository
public class SpringHibernateDepartmentDaoImpl implements DepartmentDao, HqlStatements {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Department> list() throws AppException {
       try {
            return sessionFactory.getCurrentSession().createQuery(DEPARTMENTS_ALL_HQL).list();

        } catch (Exception e) {
            throw new AppException("Cannot find departments", e);
        }

    }

    @Override
    public Department getById(long id) throws AppException {
        try {
            return sessionFactory.getCurrentSession().get(Department.class, id);

        } catch (Exception e) {
            throw new AppException("Cannot get department#" + id, e);
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public Department getByName(String name) throws AppException {

        try {
            Query<Department> query = sessionFactory.getCurrentSession()
                    .createQuery(DEPARTMENTS_ALL_HQL + NAME_CONDITION);
            query.setParameter("name", name);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new AppException("Cannot get department " + name, e);
        }

    }

    @Override
    public void add(Department department) throws AppException {

        try {
            sessionFactory.getCurrentSession().persist(department);
        } catch (GenericJDBCException e) {
            throw new AppException("Cannot add department " + department.getName(), e);
        }
    }

    @Override
    public void delete(long id) throws NotEmptyEmployeesException {
        try {

            Session session = sessionFactory.getCurrentSession();
            session.delete(session.get(Department.class, id));

        } catch (PersistenceException e) {
            throw new NotEmptyEmployeesException("Department has employees and cannot be removed", e);

        }
    }

    @Override
    public void update(long id, Department newDepartment) throws AppException {

        try {
            Session session = sessionFactory.getCurrentSession();
            Department department = session.get(Department.class, id);
            fillDepartent(department, newDepartment);
            session.flush();
        } catch (GenericJDBCException e) {
            throw new AppException("Cannot update department #" + id, e);
        }

    }

    private void fillDepartent(Department department, Department newDepartment) {
        department.setName(newDepartment.getName());
        department.setInfo(newDepartment.getInfo());
    }
}
