package com.aimprosoft.task4.controller;

import com.aimprosoft.task4.exception.AppException;
import com.aimprosoft.task4.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/{departmentName}/list")
    public ModelAndView list(@PathVariable String departmentName) throws AppException {
        ModelAndView modelAndView = new ModelAndView("employeeList");
        modelAndView.addObject("employees",employeeService.listEmployees(departmentName));
        return modelAndView;

    }

    @RequestMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") long id, @RequestParam("department") String department) throws AppException {
        employeeService.deleteEmployee(id);
        return list(department);
    }

    @RequestMapping(value ="/edit/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") long id) throws AppException {
        ModelAndView modelAndView = new ModelAndView("employeeEdit");
        try {
            modelAndView.addObject("dto", employeeService.getById(id));
        }
        catch (Exception e) { /*just show add page*/}
        return modelAndView;
    }

}
