package com.hwq.company.score.service.impl;

/*import com.situ.company.score.dao.IScoreDao;
import com.situ.company.score.dao.impl.ScoreDaoImpl;
import com.situ.company.score.model.ScoreModel;
import com.situ.company.score.service.IScoreService;*/

import com.hwq.company.score.dao.IScoreDao;
import com.hwq.company.score.dao.impl.ScoreDaoImpl;
import com.hwq.company.score.model.ScoreModel;
import com.hwq.company.score.service.IScoreService;

import java.util.List;

public class ScoreServiceImpl implements IScoreService {
    private IScoreDao dao=new ScoreDaoImpl();

    /**
     * 添加（插入）
     * @param model
     * @return
     */
    @Override
    public String insert(ScoreModel model) {
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
    public String delete(ScoreModel model) {
        Integer res = dao.delete(model);
        return res == null ? null : res.toString();
    }

    /**
     * 按照逻辑主键查询
     * @param model
     * @return
     */
    @Override
    public String deleteByCode(ScoreModel model) {
        return dao.deleteByCode(model) == null ? null :dao.deleteByCode(model).toString();
    }

    /**
     * 更新（修改）
     * @param model
     * @return
     */
    @Override
    public String update(ScoreModel model) {
        return dao.updateActive(model) + "";
    }

    /**
     * 模糊查询
     * @param model
     * @return
     */
    @Override
    public List<ScoreModel> selectList(ScoreModel model) {
        String empName=model.getEmpName();
        model.setEmpName(empName == null ? "%" : "%" +empName+ "%");
        String proName=model.getProName();
        model.setProName(proName == null ? "%" : "%" +proName+ "%");
        return dao.selectList(model);
    }

    /**
     * 查询一条记录
     * @param model
     * @return
     */
    @Override
    public ScoreModel selectModel(ScoreModel model) {
        return dao.selectModel(new ScoreModel(model.getCodeEmp(), model.getCodePro()));
    }

    /**
     * 分页条数
     * @param model
     * @return
     */
    @Override
    public Integer selectCount(ScoreModel model) {
        ScoreModel m1 = new ScoreModel();
        String codeEmp=model.getCodeEmp();
        m1.setCodeEmp(codeEmp == null ? "%" : "%" +codeEmp+ "%");
        String codePro=model.getCodePro();
        m1.setCodePro(codePro == null ? "%" : "%" +codePro+ "%");
        return dao.selectCount(m1);
    }
}
