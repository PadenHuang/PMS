package com.hwq.company.department.service.impl;

/*import com.situ.company.department.dao.IDepartmentDao;
import com.situ.company.department.dao.impl.Department2DaoImpl;
import com.situ.company.department.model.DepartmentModel;
import com.situ.company.department.service.IDepartmentService;
import com.situ.company.employee.dao.IEmployeeDao;
import com.situ.company.employee.dao.impl.EmployeeDaoImpl;
import com.situ.company.employee.model.EmployeeModel;*/

import com.hwq.company.department.dao.IDepartmentDao;
import com.hwq.company.department.dao.impl.Department2DaoImpl;
import com.hwq.company.department.model.DepartmentModel;
import com.hwq.company.department.service.IDepartmentService;
import com.hwq.company.employee.dao.IEmployeeDao;
import com.hwq.company.employee.dao.impl.EmployeeDaoImpl;
import com.hwq.company.employee.model.EmployeeModel;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
//    private IDepartmentDao dao1=new Department1DaoImpl();
    private IDepartmentDao dao=new Department2DaoImpl();
    private IEmployeeDao employeeDao=new EmployeeDaoImpl();

    /**
     * 添加（插入）
     * @param model
     * @return
     */
    @Override
    public String insert(DepartmentModel model) {
        if (selectModel(model) == null){
            Integer result = dao.insert(model);
            return result == null ? null : result.toString();
        }
        return "0";
    }

    /**
     * 删除
     * @param model
     * @return
     */
    @Override
    public String delete(DepartmentModel model) {
        String codeDept =model.getCode();
        EmployeeModel employeeModel =new EmployeeModel();
        employeeModel.setCodeDept(codeDept);
        Integer count= employeeDao.selectCount(employeeModel);
        if (count >0) {
            return "2";
        }
        Integer res = dao.delete(model);
        return res == null ? null : res.toString();
    }

    /**
     * 按照逻辑主键查询
     * @param model
     * @return
     */
    @Override
    public String deleteByCode(DepartmentModel model) {
        return dao.deleteByCode(model) == null ? null :dao.deleteByCode(model).toString();
    }

    /**
     * 更新（修改）
     * @param model
     * @return
     */
    @Override
    public String update(DepartmentModel model) {
        return dao.updateActive(model) + "";
    }

    /**
     * 模糊查询
     * @param model
     * @return
     */
    @Override
    public List<DepartmentModel> selectList(DepartmentModel model) {
        String code=model.getCode();
        model.setCode(code == null ? "%" : "%" +code+ "%");
        String name=model.getName();
        model.setName(name == null ? "%" : "%" +name+ "%");
        return dao.selectList(model);
    }

    /**
     * 查询一条记录
     * @param model
     * @return
     */
    @Override
    public DepartmentModel selectModel(DepartmentModel model) {
        return dao.selectModel(new DepartmentModel(model.getCode()));
    }

    /**
     * 分页条数
     * @param model
     * @return
     */
    @Override
    public Integer selectCount(DepartmentModel model) {
        DepartmentModel m1 = new DepartmentModel();
        String code=model.getCode();
        m1.setCode(code == null ? "%" : "%" +code+ "%");
        String name=model.getName();
        m1.setName(name == null ? "%" : "%" +name+ "%");
        return dao.selectCount(m1);
    }
}
