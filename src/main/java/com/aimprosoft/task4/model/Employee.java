package com.aimprosoft.task4.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String email;
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthday;
    private int room;

    @JoinColumn(referencedColumnName = "name", table = "departments")
    private String department;


    public Employee() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDepartmentName() {
        return department;
    }

    public void setDepartmentName(String departmentName) {
        this.department = departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeDto{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", room=" + room +
                ", departmentName='" + department + '\'' +
                '}';
    }
}
