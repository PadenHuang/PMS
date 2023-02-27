package com.hwq.company.score.model;

/*import com.situ.company.util.PageModel;*/

import com.hwq.company.util.PageModel;

public class ScoreModel extends PageModel {
    private  Integer id;
    private String codeEmp;
    private String codePro;
    private String score;
    private String empName;
    private String proName;

    public ScoreModel() {
    }

    public ScoreModel(String codeEmp, String codePro) {
        this.codeEmp = codeEmp;
        this.codePro = codePro;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodeEmp() {
        return codeEmp;
    }

    public void setCodeEmp(String codeEmp) {
        this.codeEmp = codeEmp;
    }

    public String getCodePro() {
        return codePro;
    }

    public void setCodePro(String codePro) {
        this.codePro = codePro;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ScoreModel{" +
                "id=" + id +
                ", codeEmp='" + codeEmp + '\'' +
                ", codePro='" + codePro + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
