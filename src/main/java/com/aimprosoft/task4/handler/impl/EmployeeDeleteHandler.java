package com.aimprosoft.task4.handler.impl;

import com.aimprosoft.task4.context.SpringContext;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.handler.AbstractHandler;
import com.aimprosoft.task4.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeDeleteHandler extends AbstractHandler {

    private EmployeeService employeeService = SpringContext.getEmloyeeService();

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws AppException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        String department = request.getParameter("department");
        employeeService.deleteEmployee(id);
        redirect(response, "employees?action=list&department=" + department);
    }
}
