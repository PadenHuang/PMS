package com.hwq.company.employee.model;

/*import com.situ.company.util.PageModel;*/

import com.hwq.company.util.PageModel;

public class EmployeeModel extends PageModel {
    private Integer id;
    private String code;
    private String name;
    private String pass;
    private String codeDept;
    private String image;
    private String nameDept;
    private final String passDefault = "123456";

    public EmployeeModel() {
    }

    public EmployeeModel(String code, String name, String pass) {
        this.code = code;
        this.name = name;
        this.pass = pass;
    }

    public String getNameDept() {
        return nameDept;
    }

    public void setNameDept(String nameDept) {
        this.nameDept = nameDept;
    }

    public EmployeeModel(String code) {
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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCodeDept() {
        return codeDept;
    }

    public void setCodeDept(String codeDept) {
        this.codeDept = codeDept;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPassDefault() {
        return passDefault;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", codeDept='" + codeDept + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
