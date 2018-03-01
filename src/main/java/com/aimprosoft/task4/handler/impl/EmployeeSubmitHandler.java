package com.aimprosoft.task4.handler.impl;

import com.aimprosoft.task4.context.SpringContext;
import com.aimprosoft.task4.dto.EmployeeDto;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.exception.NoSuchDepartmentException;
import com.aimprosoft.task4.handler.AbstractHandler;
import com.aimprosoft.task4.model.Employee;
import com.aimprosoft.task4.service.EmployeeService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


public class EmployeeSubmitHandler extends AbstractHandler {

    private EmployeeService employeeService = SpringContext.getEmloyeeService();

    public EmployeeSubmitHandler() {
        setViewName(EMPLOYEE_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {

        EmployeeDto dto = getDto(request);
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            request.setAttribute("violations", violations);
            request.setAttribute("warning", "Please fill this fields correctly:");
            reject(request, response, dto);
            return;
        }

        if (!employeeService.isEmailUnique(dto.getId(), dto.getEmail())) {
            request.setAttribute("warning", "Email already belongs to another employee:" + dto.getEmail());
            reject(request, response, dto);
            return;
        }

        try {
            if (dto.getId() == 0) employeeService.addEmployee(convertDtoToInstance(dto));
            else employeeService.editEmployee(dto.getId(), convertDtoToInstance(dto));

        } catch (NoSuchDepartmentException e) {
            request.setAttribute("warning", e.getMessage());
            reject(request, response, dto);
            return;

        }
        redirect(response, "employees?action=list&department=" + dto.getDepartmentName());

    }

    private void reject(HttpServletRequest request, HttpServletResponse response, EmployeeDto dto) throws ServletException, IOException {

        request.setAttribute("dto", dto);
        forward(request, response);
    }

    private Employee convertDtoToInstance(EmployeeDto dto) {

        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setEmail(dto.getEmail());
        employee.setName(dto.getName());
        employee.setRoom(Integer.parseInt(dto.getRoom()));
        try {
            employee.setBirthday(new SimpleDateFormat(EmployeeDto.DATE_FORMAT).parse(dto.getBirthday()));
        } catch (ParseException ignored) { /*will not appear because dto is valid*/}
        employee.setDepartmentName(dto.getDepartmentName());
        return employee;
    }

    private EmployeeDto getDto(HttpServletRequest request) throws AppException {
        EmployeeDto dto = new EmployeeDto();
        String idString = request.getParameter("id");
        long id;
        if (idString == null || idString.isEmpty()) {
            id = 0;
        } else try {
            id = Long.parseLong(idString); //edit existing
        } catch (NumberFormatException e) {
            throw new AppException("Invalid id given", e);
        }
        dto.setId(id);
        dto.setEmail(request.getParameter("email"));
        dto.setName(request.getParameter("name"));
        dto.setBirthday(request.getParameter("birthday"));
        dto.setRoom(request.getParameter("room"));
        dto.setDepartmentName(request.getParameter("departmentName"));
        return dto;
    }

}
