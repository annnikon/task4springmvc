package com.aimprosoft.task4.dto;

import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.ValidateWithMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeDto {

    public final static String EMAIL_REGEXP = "[\\w\\d_.]+[@]([\\w]+[\\.][\\w]+)+";
    public final static String NAME_REGEXP = "[\\p{L}\\s]+";
    public final static String DATE_FORMAT = "yyy-MM-dd";


    private long id;

    @MatchPattern(pattern = NAME_REGEXP, message = "May contain any letters in uppercase or lowercase, and spaces", errorCode = "Name")
    private String name;

    @MatchPattern(pattern = EMAIL_REGEXP, message = "Should contain letters, _, digits, @ and .", errorCode = "Email")
    private String email;

    @ValidateWithMethod(methodName = "checkDate", parameterType = String.class, message = "Should be in format " + DATE_FORMAT, errorCode = "Birthday")
    private String birthday;

    @Min(value = 1, message = "Should be positive integer number", errorCode = "Room")
    private String room;

    @NotEmpty(message = "Please specify employee's department", errorCode = "Department")
    private String departmentName;

    public boolean checkDate(String input) {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            format.parse(birthday);
            return true;
        } catch (ParseException | NullPointerException e) {
            return false;
        }
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;

    }

    public void setName(String name) {
        this.name = name;

    }

    public void setBirthday(String birthday) {

        this.birthday = birthday;

    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;

    }

    public long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getRoom() {
        return room;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                " email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", room='" + room + '\'' +
                ", departmentName='" + departmentName + '\'' +

                '}';
    }


}
