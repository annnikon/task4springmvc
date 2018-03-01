package com.aimprosoft.task4.context;

import com.aimprosoft.task4.service.DepartmentService;
import com.aimprosoft.task4.service.EmployeeService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ImportResource({"classpath:spring-config.xml"})
public class SpringContext {
    static ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

    public static DepartmentService getDepartmentService() {
        return context.getAutowireCapableBeanFactory().getBean("departmentService", DepartmentService.class);
    }

    public static EmployeeService getEmloyeeService() {
        return context.getAutowireCapableBeanFactory().getBean("employeeService", EmployeeService.class);
    }
}