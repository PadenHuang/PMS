package com.hwq.company.project.model;

/*
import com.situ.company.util.PageModel;
*/

import com.hwq.company.util.PageModel;

public class ProjectModel extends PageModel {
    private  Integer id;
    private String code;
    private String name;
    private String time1;

    public ProjectModel() {

    }

    public ProjectModel(String code){
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }


    @Override
    public String toString() {
        return "ProjectModel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", time1='" + time1 + '\'' +
                '}';
    }
}
