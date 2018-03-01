package com.aimprosoft.task4.handler.impl;


import com.aimprosoft.task4.context.SpringContext;
import com.aimprosoft.task4.dto.EmployeeDto;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.handler.AbstractHandler;
import com.aimprosoft.task4.model.Employee;
import com.aimprosoft.task4.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;


public class EmployeeEditHandler extends AbstractHandler {
    private final static String DATE_FORMAT = "yyy-MM-dd";

    private EmployeeService employeeService = SpringContext.getEmloyeeService();

    public EmployeeEditHandler() {
        setViewName(EMPLOYEE_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws AppException, ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter("id"));
            EmployeeDto dto = convertInstanceToDto(employeeService.getById(id));
            request.setAttribute("dto", dto);
        } catch (NullPointerException | NumberFormatException e) {
            // just show add page
        }
        request.setAttribute("department", request.getParameter("department"));
        forward(request, response);
    }

    private EmployeeDto convertInstanceToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setEmail(employee.getEmail());
        dto.setName(employee.getName());
        dto.setRoom(String.valueOf(employee.getRoom()));
        dto.setBirthday(new SimpleDateFormat(DATE_FORMAT).format(employee.getBirthday()));
        dto.setDepartmentName(employee.getDepartmentName());
        return dto;
    }
}
