package com.hwq.company.project.dao.impl;

/*import com.situ.company.project.dao.IProjectDao;
import com.situ.company.project.model.ProjectModel;
import com.situ.company.util.FmtEmpty;
import com.situ.company.util.JDBCUtil;*/

import com.hwq.company.project.dao.IProjectDao;
import com.hwq.company.project.model.ProjectModel;
import com.hwq.company.util.FmtEmpty;
import com.hwq.company.util.JDBCUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//数据层=SQL+参数+映射
public class ProjectDaoImpl implements IProjectDao {
    private static String table="project";
    private static String clos="code,name,time1";
    @Override
    public Integer insert(ProjectModel model) {
        StringBuffer sql=new StringBuffer(" insert into ");
        sql.append(table).append('(').append(clos).append(")values(?,?,?)");
        return JDBCUtil.update(sql.toString(), model.getCode(),model.getName(),model.getTime1());
    }

    @Override
    public Integer delete(ProjectModel model) {
        StringBuffer sql=new StringBuffer(" delete from ").append(table);
        List<Object> values=appendWhere(sql,model);
        return JDBCUtil.update(sql.toString(), values);
    }

    @Override
    public Integer deleteByCode(ProjectModel model) {//spring-jdbc
        StringBuffer sql=new StringBuffer(" delete from ");
        sql.append(table).append(" where code = ? ");
//        List<Object> values= Arrays.asList(model.getCode());
        return JDBCUtil.update(sql.toString(), model.getCode());
    }

    @Override
    public Integer updateAll(ProjectModel model) {
        StringBuffer sql=new StringBuffer(" update ");
        sql.append(table).append(" set name = ?,time1 = ? where code = ?");
        return JDBCUtil.update(sql.toString(), model.getName(),model.getTime1(),model.getCode());
    }

    @Override
    public Integer updateActive(ProjectModel model) {
        StringBuffer sql =new StringBuffer(" update ");
        sql.append(table).append(" set id = id ");
        List<Object>values=appendSet(sql,model);
        return JDBCUtil.update(sql.toString(), values);
    }

    @Override
    public List<ProjectModel> selectList(ProjectModel model) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(clos).append(" from ").append(table);
        List<Object>values= appendWhere(sql,model);
        return JDBCUtil.queryList(sql.toString(),values, ProjectModel.class);
    }

    @Override
    public ProjectModel selectModel(ProjectModel model) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(clos).append(" from ").append(table).append(" where code = ? ");
        List<Object>values= Arrays.asList(model.getCode());
        return JDBCUtil.queryModel(sql.toString(), values, ProjectModel.class);
    }

    @Override
    public Integer selectCount(ProjectModel model) {
        StringBuffer sql =new StringBuffer("select count(1) ").append(" from ").append(table);
        List<Object>values=appendWhere(sql,model);
        return JDBCUtil.queryInt(sql.toString(),values);
    }

    private List<Object> appendWhere(StringBuffer sql, ProjectModel model) {
        sql.append(" where 1 = 1 ");
        List<Object> values=new ArrayList<>();
        String code=model.getCode();
        if (!FmtEmpty.isEmpty(code)){
            sql.append(" and code like ? ");
            values.add(code);
        }
        String name=model.getName();
        if (!FmtEmpty.isEmpty(name)){
            sql.append(" and name like ? ");
            values.add(name);
        }
        String time1=model.getTime1();
        if (!FmtEmpty.isEmpty(time1)){
            sql.append(" and time1 like ? ");
            values.add(time1);
        }
        if (model.isPageOn()){
            sql.append("limit ?,? ");
            //第1页10条=0，10
            //第2页10条=10，10
            //pageIndex+pageLimit=(pageIndex-1)*pageLimit,pageLimit
            values.add(model.getRowStart());
            values.add(model.getRowCount());
        }
        return values;
    }

    private List<Object> appendSet(StringBuffer sql, ProjectModel model){
        List<Object>values = new ArrayList<Object>();
        String name = model.getName();
        if (name != null){
            sql.append(" ,name = ? ");
            values.add(name);
        }
        String time1 = model.getTime1();
        if (time1 != null){
            sql.append(" ,time1 = ? ");
            values.add(time1);
        }
        sql.append(" where code = ? ");
        values.add(model.getCode());
        return values;
    }
}
