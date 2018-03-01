package com.aimprosoft.task4.handler.impl;


import com.aimprosoft.task4.context.SpringContext;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.handler.AbstractHandler;
import com.aimprosoft.task4.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeListHandler extends AbstractHandler {

    private EmployeeService employeeService = SpringContext.getEmloyeeService();

    public EmployeeListHandler() {
        setViewName(EMPLOYEE_LIST);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws AppException, ServletException, IOException {
        request.setAttribute("employees", employeeService.listEmployees(request.getParameter("department")));
        forward(request, response);

    }
}
