package com.hwq.company.score.dao;

/*import com.situ.company.score.model.ScoreModel;*/

import com.hwq.company.score.model.ScoreModel;

import java.util.List;

//制定标准+统一规范
//返回类型String int list map DepartmentModel
public interface IScoreDao {
    /**
     * 添加记录
     * @param model
     * @return
     */
    Integer insert(ScoreModel model);

    /**根据查询条件删除
     * @param model
     * @return
     */
    Integer delete(ScoreModel model);

    /**
     * 根据主键删除
     * @param model
     * @return
     */
    Integer deleteByCode(ScoreModel model);

    /**
     * 根据主键修改其他全部字段
     * @param model
     * @return
     */
    Integer updateAll(ScoreModel model);

    /**
     * 根据主键修改其他部分字段
     * @param model
     * @return
     */
    Integer updateActive(ScoreModel model);

    /**
     * 依据多条件模糊查询
     * @param model
     * @return
     */
    List<ScoreModel> selectList(ScoreModel model);

    /**
     * 根据主键查询一条记录
     * @param model
     * @return
     */
    ScoreModel selectModel(ScoreModel model);

    Integer selectCount(ScoreModel model);
}

