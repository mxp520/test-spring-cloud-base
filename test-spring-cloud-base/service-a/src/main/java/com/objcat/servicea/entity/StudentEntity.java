package com.objcat.servicea.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StudentEntity implements Serializable {
    private static final long serialVersionUID = 1820837073616123246L;


    private String id;

    @NotNull(message = "学生姓名不能为空")
    private String name;
    @NotNull(message = "学生地址不能为空")
    private String address;

    private String checkProject;


    @Valid
    @NotNull(message = "教师不能为空")
    @Size(min =1,message = "至少有一个老师")
    private List<TeacherEntity> teacherList;

    public List<TeacherEntity> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherEntity> teacherList) {
        this.teacherList = teacherList;
    }

    private List<String> belowTextList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCheckProject() {
        return checkProject;
    }

    public void setCheckProject(String checkProject) {
        this.checkProject = checkProject;
    }

    public List<String> getBelowTextList() {
        return belowTextList;
    }

    public void setBelowTextList(List<String> belowTextList) {
        this.belowTextList = belowTextList;
    }

    public StudentEntity() {
    }

    public StudentEntity(String id, String name, String address, String checkProject) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.checkProject = checkProject;
        this.belowTextList = new ArrayList<>();
        belowTextList.add(id);
        belowTextList.add(name);
        belowTextList.add(address);
        belowTextList.add(checkProject);
    }

    @Override
    public String toString() {
        return id+","+name+","+address;
    }

}
