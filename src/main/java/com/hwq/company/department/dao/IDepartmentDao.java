package com.hwq.company.department.dao;


import com.hwq.company.department.model.DepartmentModel;

import java.util.List;

//制定标准+统一规范
//返回类型String int list map DepartmentModel
public interface IDepartmentDao {
    /**
     * 添加记录
     * @param model
     * @return
     */
    Integer insert(DepartmentModel model);

    /**根据查询条件删除
     * @param model
     * @return
     */
    Integer delete(DepartmentModel model);

    /**
     * 根据主键删除
     * @param model
     * @return
     */
    Integer deleteByCode(DepartmentModel model);

    /**
     * 根据主键修改其他全部字段
     * @param model
     * @return
     */
    Integer updateAll(DepartmentModel model);

    /**
     * 根据主键修改其他部分字段
     * @param model
     * @return
     */
    Integer updateActive(DepartmentModel model);

    /**
     * 依据多条件模糊查询
     * @param model
     * @return
     */
    List<DepartmentModel> selectList(DepartmentModel model);

    /**
     * 根据主键查询一条记录
     * @param model
     * @return
     */
    DepartmentModel selectModel(DepartmentModel model);

    /**
     * 根据条件查询得到记录条数
     * @param model
     * @return
     */
    Integer selectCount(DepartmentModel model);

}

