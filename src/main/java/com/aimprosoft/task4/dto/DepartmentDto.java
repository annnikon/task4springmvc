package com.aimprosoft.task4.dto;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.guard.Guarded;

@Guarded
public class DepartmentDto {

    private long id;

    @NotEmpty(errorCode = "Name", message = "Cannot be empty")
    private String name;
    private String info;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;

    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "DepartmentDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '}';
    }
}
