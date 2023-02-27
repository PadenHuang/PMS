package com.hwq.company.department.dao.impl;



import com.hwq.company.department.dao.IDepartmentDao;
import com.hwq.company.department.model.DepartmentModel;
import com.hwq.company.util.FmtEmpty;
import com.hwq.company.util.JDBCUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//数据层=SQL+参数+映射
public class Department2DaoImpl implements IDepartmentDao {
    private static String table="department";
    private static String clos="code,name,tel";
    @Override
    public Integer insert(DepartmentModel model) {
        StringBuffer sql=new StringBuffer(" insert into ");
        sql.append(table).append('(').append(clos).append(")values(?,?,?)");
//        List<Object> values= Arrays.asList(model.getCode(),model.getName(),model.getTel());
        return JDBCUtil.update(sql.toString(), model.getCode(),model.getName(),model.getTel());
    }

    @Override
    public Integer delete(DepartmentModel model) {
        StringBuffer sql=new StringBuffer(" delete from ").append(table);
        List<Object> values=appendWhere(sql,model);
        return JDBCUtil.update(sql.toString(), values);
    }

    @Override
    public Integer deleteByCode(DepartmentModel model) {//spring-jdbc
        StringBuffer sql=new StringBuffer(" delete from ");
        sql.append(table).append(" where code = ? ");
//        List<Object> values= Arrays.asList(model.getCode());
        return JDBCUtil.update(sql.toString(), model.getCode());
    }

    @Override
    public Integer updateAll(DepartmentModel model) {
        StringBuffer sql=new StringBuffer(" update ");
        sql.append(table).append(" set name = ?,tel = ? where code = ? ");
        return JDBCUtil.update(sql.toString(), model.getName(),model.getTel(),model.getCode());
    }

    @Override
    public Integer updateActive(DepartmentModel model) {
        StringBuffer sql =new StringBuffer(" update ");
        sql.append(table).append(" set id = id ");
        List<Object>values=appendSet(sql,model);
        return JDBCUtil.update(sql.toString(), values);
    }

    @Override
    public List<DepartmentModel> selectList(DepartmentModel model) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(clos).append(",(select count(1) from employee where code_dept = department.code) count ")
                .append(" from ").append(table);
        List<Object>values= appendWhere(sql,model);
        return JDBCUtil.queryList(sql.toString(),values,DepartmentModel.class);
    }

    @Override
    public DepartmentModel selectModel(DepartmentModel model) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(clos).append(",(select count(1) from employee where code_dept = department.code) count ")
                .append(" from ").append(table).append(" where code = ? ");
        List<Object>values= Arrays.asList(model.getCode());
        return JDBCUtil.queryModel(sql.toString(),values,DepartmentModel.class);
    }

    @Override
    public Integer selectCount(DepartmentModel model) {
        StringBuffer sql =new StringBuffer("select count(1) ").append(" from ").append(table);
        List<Object>values=appendWhere(sql,model);
        return JDBCUtil.queryInt(sql.toString(),values);
    }

    private List<Object> appendWhere(StringBuffer sql, DepartmentModel model) {
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
        String tel=model.getTel();
        if (!FmtEmpty.isEmpty(tel)){
            sql.append(" and tel like ? ");
            values.add(tel);
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

    private List<Object> appendSet(StringBuffer sql,DepartmentModel model){
        List<Object>values = new ArrayList<Object>();
        String name = model.getName();
        if (name != null){
            sql.append(" ,name = ? ");
            values.add(name);
        }
        String tel = model.getTel();
        if (tel != null){
            sql.append(" ,tel = ? ");
            values.add(tel);
        }
        sql.append(" where code = ? ");
        values.add(model.getCode());
        return values;
    }
}
