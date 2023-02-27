package com.hwq.company.score.dao.impl;



import com.hwq.company.score.dao.IScoreDao;
import com.hwq.company.score.model.ScoreModel;
import com.hwq.company.util.FmtEmpty;
import com.hwq.company.util.JDBCUtil;

import java.util.*;

//数据层=SQL+参数+映射
public class ScoreDaoImpl implements IScoreDao {
    private static String table="score";
    private static String clos="code_emp,code_pro,score";
    @Override
    public Integer insert(ScoreModel model) {
        StringBuffer sql=new StringBuffer(" insert into ");
        sql.append(table).append('(').append(clos).append(")values(?,?,?)");
        return JDBCUtil.update(sql.toString(), model.getCodeEmp(),model.getCodePro(),model.getScore());
    }

    @Override
    public Integer delete(ScoreModel model) {
        StringBuffer sql=new StringBuffer(" delete from ").append(table);
        List<Object> values=appendWhere(sql,model);
        return JDBCUtil.update(sql.toString(), values);
    }

    @Override
    public Integer deleteByCode(ScoreModel model) {//spring-jdbc
        StringBuffer sql=new StringBuffer(" delete from ");
        sql.append(table).append(" where code_emp = ? and code_pro = ? ");
        return JDBCUtil.update(sql.toString(), model.getCodeEmp(),model.getCodePro());
    }

    @Override
    public Integer updateAll(ScoreModel model) {
        StringBuffer sql=new StringBuffer(" update ");
        sql.append(table).append(" set score = ? where code_emp = ? and code_pro = ? ");
        return JDBCUtil.update(sql.toString(), model.getScore(),model.getCodeEmp(),model.getCodePro());
    }

    @Override
    public Integer updateActive(ScoreModel model) {
        StringBuffer sql =new StringBuffer(" update ");
        sql.append(table).append(" set id = id ");
        List<Object>values=appendSet(sql,model);
        return JDBCUtil.update(sql.toString(), values);
    }

    @Override
    public List<ScoreModel> selectList(ScoreModel model) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(clos).append(",(select name from employee where code = score.code_emp) empName ")
                .append(",(select name from project where code = score.code_pro) proName ").append(" from ").append(table);
        List<Object>values= appendWhere(sql,model);
        Map<String, String> fields=new HashMap<>();
        fields.put("id","id");
        fields.put("codeEmp","code_emp");
        fields.put("empName","empName");
        fields.put("codePro","code_pro");
        fields.put("proName","proName");
        fields.put("score","score");
        return JDBCUtil.queryList(sql.toString(),values, ScoreModel.class,fields);
    }

    @Override
    public ScoreModel selectModel(ScoreModel model) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(clos).append(" from ").append(table).append(" where code_emp = ? and code_pro = ? ");
        List<Object>values= Arrays.asList(model.getCodeEmp(),model.getCodePro());
        Map<String, String> fields=new HashMap<>();
        fields.put("id","id");
        fields.put("codeEmp","code_emp");
        fields.put("codePro","code_pro");
        fields.put("score","score");
        return JDBCUtil.queryModel(sql.toString(), values, ScoreModel.class,fields);
    }

    @Override
    public Integer selectCount(ScoreModel model) {
        StringBuffer sql =new StringBuffer("select count(1) ").append(" from ").append(table);
        List<Object>values=appendWhere(sql,model);
        return JDBCUtil.queryInt(sql.toString(),values);
    }

    private List<Object> appendWhere(StringBuffer sql, ScoreModel model) {
        sql.append(" where 1 = 1 ");
        List<Object> values=new ArrayList<>();
        String code_emp=model.getCodeEmp();
        if (!FmtEmpty.isEmpty(code_emp)){
            sql.append(" and code_emp like ? ");
            values.add(code_emp);
        }
        String code_pro=model.getCodePro();
        if (!FmtEmpty.isEmpty(code_pro)){
            sql.append(" and code_pro like ? ");
            values.add(code_pro);
        }
        String score=model.getScore();
        if (!FmtEmpty.isEmpty(score)){
            sql.append(" and score like ? ");
            values.add(score);
        }
        return values;
    }

    private List<Object> appendSet(StringBuffer sql, ScoreModel model){
        List<Object>values = new ArrayList<Object>();
        String score = model.getScore();
        if (score != null){
            sql.append(" ,score = ? ");
            values.add(score);
        }
        sql.append(" where code_emp = ? and code_pro = ? ");
        values.add(model.getCodeEmp());
        values.add(model.getCodePro());
        return values;
    }
}
