package com.hwq.company.project.service.impl;


/*import com.situ.company.project.dao.IProjectDao;
import com.situ.company.project.dao.impl.ProjectDaoImpl;
import com.situ.company.project.model.ProjectModel;
import com.situ.company.project.service.IProjectService;*/

import com.hwq.company.project.dao.IProjectDao;
import com.hwq.company.project.dao.impl.ProjectDaoImpl;
import com.hwq.company.project.model.ProjectModel;
import com.hwq.company.project.service.IProjectService;

import java.util.List;

public class ProjectServiceImpl implements IProjectService {
    private IProjectDao dao=new ProjectDaoImpl();

    /**
     * 添加（插入）
     * @param model
     * @return
     */
    @Override
    public String insert(ProjectModel model) {
        if (SelectModel(model) == null){
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
    public String delete(ProjectModel model) {
        Integer res = dao.delete(model);
        return res == null ? null : res.toString();
    }

    /**
     * 按照逻辑主键查询
     * @param model
     * @return
     */
    @Override
    public String deleteByCode(ProjectModel model) {
        return dao.deleteByCode(model) == null ? null :dao.deleteByCode(model).toString();
    }

    /**
     * 更新（修改）
     * @param model
     * @return
     */
    @Override
    public String update(ProjectModel model) {
        return dao.updateActive(model) + "";
    }

    /**
     * 模糊查询
     * @param model
     * @return
     */
    @Override
    public List<ProjectModel> SelectList(ProjectModel model) {
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
    public ProjectModel SelectModel(ProjectModel model) {
        return dao.selectModel(new ProjectModel(model.getCode()));
    }

    /**
     * 分页条数
     * @param model
     * @return
     */
    @Override
    public Integer selectCount(ProjectModel model) {
        ProjectModel m1 = new ProjectModel();
        String code=model.getCode();
        m1.setCode(code == null ? "%" : "%" +code+ "%");
        String name=model.getName();
        m1.setName(name == null ? "%" : "%" +name+ "%");
        return dao.selectCount(m1);
    }
}
