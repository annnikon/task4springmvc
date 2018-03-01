package com.aimprosoft.task4.controller;

import com.aimprosoft.task4.dto.DepartmentDto;
import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.model.Department;
import com.aimprosoft.task4.service.DepartmentService;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/list")
    public ModelAndView list() throws AppException{
        ModelAndView modelAndView = new ModelAndView("departmentList");
        modelAndView.addObject("departments", departmentService.listDepartments());
        return modelAndView;
    }

    @RequestMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id) throws AppException {
       departmentService.deleteDepartment(id);
       return list();
    }

    @RequestMapping(value ="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") long id) throws AppException {
        ModelAndView modelAndView = new ModelAndView("departmentEdit");
        try {
            modelAndView.addObject("dto", departmentService.getById(id));
        }
        catch (Exception e) { /*just show add page*/}
        return modelAndView;
    }

    @RequestMapping(value ="/edit/{id}", method = RequestMethod.POST)
    public ModelAndView submit(@PathVariable("id") long id, @ModelAttribute DepartmentDto dto) throws AppException {
        ModelAndView modelAndView = new ModelAndView("departmentEdit");
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(dto);

        if (!violations.isEmpty()) {
           modelAndView.addObject("warning", "Please fill this fields correctly:");
            modelAndView.addObject("violations", violations);
            return modelAndView;
        }

        if (!departmentService.isNameUnique(dto.getId(), dto.getName())) {
            modelAndView.addObject("warning", "This department already exists: " + dto.getName());
            return modelAndView;
        }

        Department department = convertDtoToInstance(dto);

        if (dto.getId() == 0) {
            departmentService.addDepartment(department);
        } else {
            departmentService.editDepartment(dto.getId(), department);
        }

        return list();
    }

    private Department convertDtoToInstance(DepartmentDto dto) {
        Department department = new Department();
        department.setId(dto.getId());
        department.setName(dto.getName());
        department.setInfo(dto.getInfo());
        return department;
    }


}
