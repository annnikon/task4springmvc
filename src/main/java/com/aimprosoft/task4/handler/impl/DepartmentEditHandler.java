package com.aimprosoft.task4.handler.impl;

import com.aimprosoft.task4.context.SpringContext;
import com.aimprosoft.task4.dto.DepartmentDto;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.handler.AbstractHandler;
import com.aimprosoft.task4.model.Department;
import com.aimprosoft.task4.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DepartmentEditHandler extends AbstractHandler {

    private DepartmentService departmentService = SpringContext.getDepartmentService();

    public DepartmentEditHandler() {
        setViewName(DEPARTMENT_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws AppException, ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            request.setAttribute("dto", convertInstanceToDto(departmentService.getById(id)));

        } catch (NumberFormatException | NullPointerException e) {
            /*do nothing: new department will be added*/
        }

        forward(request, response);

    }

    private DepartmentDto convertInstanceToDto(Department department) {
        DepartmentDto dto = new DepartmentDto();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setInfo(department.getInfo());
        return dto;
    }
}
