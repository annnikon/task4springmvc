package com.aimprosoft.task4.handler.impl;

import com.aimprosoft.task4.context.SpringContext;
import com.aimprosoft.task4.dto.DepartmentDto;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.handler.AbstractHandler;
import com.aimprosoft.task4.model.Department;
import com.aimprosoft.task4.service.DepartmentService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class DepartmentSubmitHandler extends AbstractHandler {

    private DepartmentService departmentService = SpringContext.getDepartmentService();

    public DepartmentSubmitHandler() {
        setViewName(DEPARTMENT_EDIT);
    }

    @Override
    public void handleInternal(HttpServletRequest request, HttpServletResponse response) throws AppException, ServletException, IOException {

        Validator validator = new Validator();
        DepartmentDto dto = getDto(request);
        List<ConstraintViolation> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            request.setAttribute("warning", "Please fill this fields correctly:");
            request.setAttribute("violations", violations);
            reject(request, response, dto);
            return;
        }

        if (!departmentService.isNameUnique(dto.getId(), dto.getName())) {
            request.setAttribute("warning", "This department already exists: " + dto.getName());
            reject(request, response, dto);
            return;
        }

        Department department = convertDtoToInstance(dto);

        if (dto.getId() == 0) {
            departmentService.addDepartment(department);
        } else {
            departmentService.editDepartment(dto.getId(), department);
        }

        redirect(response, "departments?action=list");

    }

    private void reject(HttpServletRequest request, HttpServletResponse response, DepartmentDto dto) throws ServletException, IOException {

        request.setAttribute("dto", dto);
        forward(request, response);
    }


    private DepartmentDto getDto(HttpServletRequest request) throws AppException {
        DepartmentDto dto = new DepartmentDto();
        String idString = request.getParameter("id");
        long id;
        if (idString == null || idString.isEmpty()) {
            id = 0; //create new
        } else {
            try {
                id = Long.parseLong(idString); //edit existing
            } catch (NumberFormatException e) {
                throw new AppException("Invalid id given", e);
            }
        }

        dto.setId(id);
        dto.setName(request.getParameter("name"));
        dto.setInfo(request.getParameter("info"));

        return dto;
    }

    private Department convertDtoToInstance(DepartmentDto dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setInfo(dto.getInfo());
        return department;
    }


}
