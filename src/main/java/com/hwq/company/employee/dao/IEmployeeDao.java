package com.hwq.company.employee.dao;



import com.hwq.company.employee.model.EmployeeModel;
import java.util.List;

/**
 * dao层——处理数据，与数据库交互
 */
public interface IEmployeeDao {
    /**
     * 添加记录
     * @param model
     * @return
     */
    Integer insert(EmployeeModel model);

    /**根据查询条件删除
     * @param model
     * @return
     */
    Integer delete(EmployeeModel model);

    /**
     * 根据主键删除
     * @param model
     * @return
     */
    Integer deleteByCode(EmployeeModel model);

    /**
     * 根据主键修改其他全部字段
     * @param model
     * @return
     */
    Integer updateAll(EmployeeModel model);

    /**
     * 根据主键修改其他部分字段
     * @param model
     * @return
     */
    Integer updateActive(EmployeeModel model);

    /**
     * 依据多条件模糊查询
     * @param model
     * @return
     */
    List<EmployeeModel> selectList(EmployeeModel model);

    /**
     * 根据主键查询一条记录
     * @param model
     * @return
     */
    EmployeeModel selectModel(EmployeeModel model);

    /**
     * 根据条件查询得到记录条数
     * @param model
     * @return
     */
    Integer selectCount(EmployeeModel model);
}
