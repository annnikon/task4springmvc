package com.aimprosoft.task4.handler.impl;


import com.aimprosoft.task4.context.SpringContext;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.exception.NotEmptyEmployeesException;
import com.aimprosoft.task4.handler.AbstractHandler;
import com.aimprosoft.task4.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DepartmentDeleteHandler extends AbstractHandler {


    private DepartmentService departmentService = SpringContext.getDepartmentService();

    public DepartmentDeleteHandler() {
        setViewName(DEPARTMENT_LIST);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws AppException, IOException, ServletException {
        long id = Long.parseLong(request.getParameter("id"));
        try {
            departmentService.deleteDepartment(id);
        } catch (NotEmptyEmployeesException e) {
            request.setAttribute("status", e.getMessage());
            request.setAttribute("departments", departmentService.listDepartments());
            forward(request, response);
            return;
        }

        redirect(response, "departments?action=list");

    }
}
