package com.objcat.servicea.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TeacherEntity implements Serializable {
    private static final long serialVersionUID = 1820837073616123246L;


    @NotNull(message = "教师id不能为空")
    private String id;
    @NotNull(message = "教师姓名不能为空")
    private String name;

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
}
