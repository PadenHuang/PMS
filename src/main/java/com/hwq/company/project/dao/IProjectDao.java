package com.hwq.company.project.dao;

/*import com.situ.company.project.model.ProjectModel;*/

import com.hwq.company.project.model.ProjectModel;

import java.util.List;

//制定标准+统一规范
//返回类型String int list map DepartmentModel
public interface IProjectDao {
    /**
     * 添加记录
     * @param model
     * @return
     */
    Integer insert(ProjectModel model);

    /**根据查询条件删除
     * @param model
     * @return
     */
    Integer delete(ProjectModel model);

    /**
     * 根据主键删除
     * @param model
     * @return
     */
    Integer deleteByCode(ProjectModel model);

    /**
     * 根据主键修改其他全部字段
     * @param model
     * @return
     */
    Integer updateAll(ProjectModel model);

    /**
     * 根据主键修改其他部分字段
     * @param model
     * @return
     */
    Integer updateActive(ProjectModel model);

    /**
     * 依据多条件模糊查询
     * @param model
     * @return
     */
    List<ProjectModel> selectList(ProjectModel model);

    /**
     * 根据主键查询一条记录
     * @param model
     * @return
     */
    ProjectModel selectModel(ProjectModel model);

    /**
     * 根据条件查询得到记录条数
     * @param model
     * @return
     */
    Integer selectCount(ProjectModel model);

}

