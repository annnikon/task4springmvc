package com.aimprosoft.task4.dao.springhibernate;

import com.aimprosoft.task4.dao.EmployeeDao;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.exception.NoSuchDepartmentException;
import com.aimprosoft.task4.model.Employee;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class SpringHibernateEmployeeDaoImpl implements EmployeeDao, HqlStatements {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Iterable<Employee> list(String departmentName) throws AppException {

        try {
            Query<Employee> query = sessionFactory.getCurrentSession()
                    .createQuery(EMPLOYEES_ALL_HQL + DEPARTMENT_CONDITION);
            query.setParameter("department", departmentName);
            return query.list();
        } catch (GenericJDBCException e) {
            throw new AppException("Cannot get employees at " + departmentName, e);
        }

    }

    @Override
    public Employee getById(long id) throws AppException {

        try {
            return sessionFactory.getCurrentSession().get(Employee.class, id);
        } catch (GenericJDBCException e) {
            throw new AppException("Cannot get employee#" + id, e);
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public Employee getByEmail(String email) throws AppException {

        try {
            Query<Employee> query = sessionFactory.getCurrentSession()
                    .createQuery(EMPLOYEES_ALL_HQL + EMAIL_CONDITION);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (GenericJDBCException e) {
            throw new AppException("Cannot get employee with email " + email, e);
        }

    }

    @Override
    public void add(Employee employee) throws NoSuchDepartmentException {

        try {
            sessionFactory.getCurrentSession().persist(employee);
        } catch (ConstraintViolationException e) {
            throw new NoSuchDepartmentException("No such department: " + employee.getDepartmentName(), e);
        }

    }

    @Override
    public void delete(long id) throws AppException {

        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(session.get(Employee.class, id));
            session.flush();
        } catch (GenericJDBCException e) {
            throw new AppException("Cannot delete employee #" + id, e);
        }
    }

    @Override
    public void update(long id, Employee newEmployee) throws NoSuchDepartmentException {

        try {
            Session session = sessionFactory.getCurrentSession();
            Employee employee = session.get(Employee.class, id);
            fillEmployee(employee, newEmployee);
            session.flush();
        } catch (ConstraintViolationException e) {
            throw new NoSuchDepartmentException("No such department: " + newEmployee.getDepartmentName(), e);
        }

    }

    private void fillEmployee(Employee employee, Employee newEmployee) {
        employee.setName(newEmployee.getName());
        employee.setEmail(newEmployee.getEmail());
        employee.setBirthday(newEmployee.getBirthday());
        employee.setRoom(newEmployee.getRoom());
        employee.setDepartmentName(newEmployee.getDepartmentName());
    }
}
