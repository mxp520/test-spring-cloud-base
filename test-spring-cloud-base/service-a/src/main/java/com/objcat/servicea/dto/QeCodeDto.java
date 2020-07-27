package com.objcat.servicea.dto;

public class QeCodeDto {


    private String orCodeUrl;

    private String orCodeName;

    public String getOrCodeUrl() {
        return orCodeUrl;
    }

    public void setOrCodeUrl(String orCodeUrl) {
        this.orCodeUrl = orCodeUrl;
    }

    public String getOrCodeName() {
        return orCodeName;
    }

    public void setOrCodeName(String orCodeName) {
        this.orCodeName = orCodeName;
    }

    public QeCodeDto() {
    }

    public QeCodeDto(String orCodeUrl, String orCodeName) {
        this.orCodeUrl = orCodeUrl;
        this.orCodeName = orCodeName;
    }
}
