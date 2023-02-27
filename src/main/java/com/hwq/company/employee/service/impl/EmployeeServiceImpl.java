package com.hwq.company.employee.service.impl;


import com.hwq.company.employee.dao.IEmployeeDao;
import com.hwq.company.employee.dao.impl.EmployeeDaoImpl;
import com.hwq.company.employee.model.EmployeeModel;
import com.hwq.company.employee.service.IEmployeeService;
import com.hwq.company.util.FmtEmpty;
import com.hwq.company.util.FmtUpload;
import com.hwq.company.util.MD5;

import java.util.List;

/**
 * service层——处理动作
 */
public class EmployeeServiceImpl implements IEmployeeService {

    private IEmployeeDao dao = new EmployeeDaoImpl();

    /**
     * 添加员工
     * @param model
     * @return
     */
    @Override
    public String insert(EmployeeModel model) {
        //先查询是否已经存在改员工
        EmployeeModel mdb = selectModel(model);
        if (mdb != null) {
            return "0";
        }else if (FmtEmpty.isEmpty(model.getPass())) {
            model.setPass(MD5.encode(model.getPassDefault()));
        }else {
        model.setPass(MD5.encode(model.getPass()));
        }
        //不存在则添加
        return dao.insert(model) + "";
    }

    @Override
    public String delete(EmployeeModel model) {
        return dao.delete(model) + "";
    }

    @Override
    public String update(EmployeeModel model) {
        return dao.updateActive(model) + "";
    }

    @Override
    public String resPass(EmployeeModel model) {
        model.setPass(MD5.encode(model.getPassDefault()));
        return dao.updateActive(model) + "";
    }

    @Override
    public String passUpd(EmployeeModel model) {
        EmployeeModel mdb = selectModel(model);
        if (mdb.getPass().equals(MD5.encode(model.getPass()))) {
            return "0";
        }else {
            model.setPass(MD5.encode(model.getPass()));
        }
        return dao.updateActive(model) + "";
    }

    @Override
    public String delPic(EmployeeModel model) {
        EmployeeModel mdb = selectModel(model);
        String image = mdb.getImage();
        FmtUpload.delFile(image);
        model.setImage("");
        return update(model);
    }

    @Override
    public List<EmployeeModel> selectList(EmployeeModel model) {
        String code=model.getCode();
        model.setCode(code == null ? "%" : "%" +code+ "%");
        String name=model.getName();
        model.setName(name == null ? "%" : "%" +name+ "%");
        return dao.selectList(model);
    }

    /**
     * 查询员工
     * @param model
     * @return
     */
    @Override
    public EmployeeModel selectModel(EmployeeModel model) {
        return dao.selectModel(new EmployeeModel(model.getCode()));
    }

    @Override
    public String login(EmployeeModel model) {
        EmployeeModel mdb= selectModel(model);
        if (mdb == null) {
            return "0";
        }
        String pass =MD5.encode(model.getPass());
        return mdb.getPass().equals(pass)?"1":"2";
    }

    @Override
    public Integer selectCount(EmployeeModel model) {
        EmployeeModel m1 = new EmployeeModel();
        String code=model.getCode();
        m1.setCode(code == null ? "%" : "%" +code+ "%");
        String name=model.getName();
        m1.setName(name == null ? "%" : "%" +name+ "%");
        return dao.selectCount(m1);
    }


}
