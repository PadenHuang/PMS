package com.hwq.company.employee.dao.impl;

import com.hwq.company.employee.dao.IEmployeeDao;
import com.hwq.company.employee.model.EmployeeModel;
import com.hwq.company.util.FmtEmpty;
import com.hwq.company.util.JDBCUtil;
import java.util.*;


/**
 * dao层实现——处理数据，与数据库交互
 */
public class EmployeeDaoImpl implements IEmployeeDao {
    private static String table="employee";
    private static String clos="code,name,pass,code_dept,image";

    /**
     * 添加员工
     * @param model
     * @return
     */
    @Override
    public Integer insert(EmployeeModel model) {
        StringBuffer sql=new StringBuffer(" insert into ");
        sql.append(table).append('(').append(clos).append(")values(?,?,?,?,?)");
        return JDBCUtil.update(sql.toString(), model.getCode(),model.getName(),model.getPass(),model.getCodeDept(),model.getImage());
    }

    @Override
    public Integer delete(EmployeeModel model) {
        StringBuffer sql=new StringBuffer(" delete from ").append(table);
        List<Object> values=appendWhere(sql,model);
        return JDBCUtil.update(sql.toString(), values);
    }

    @Override
    public Integer deleteByCode(EmployeeModel model) {//spring-jdbc
        StringBuffer sql=new StringBuffer(" delete from ");
        sql.append(table).append(" where code = ? ");
//        List<Object> values= Arrays.asList(model.getCode());
        return JDBCUtil.update(sql.toString(), model.getCode());
    }

    @Override
    public Integer updateAll(EmployeeModel model) {
        StringBuffer sql=new StringBuffer(" update ");
        sql.append(table).append(" set name = ?,pass = ?,code_dept = ?,image = ? where code = ?");
        return JDBCUtil.update(sql.toString(), model.getName(),model.getPass(),model.getCodeDept(),model.getImage(),model.getCode());
    }

    @Override
    public Integer updateActive(EmployeeModel model) {
        StringBuffer sql =new StringBuffer(" update ");
        sql.append(table).append(" set id = id ");
        List<Object>values=appendSet(sql,model);
        return JDBCUtil.update(sql.toString(), values);
    }

    @Override
    public List<EmployeeModel> selectList(EmployeeModel model) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(clos).append(",(select name from department where code = code_dept) name_dept")
                .append(" from ").append(table);
        List<Object>values= appendWhere(sql,model);
        Map<String, String> fields=new HashMap<>();
        fields.put("id","id");
        fields.put("code","code");
        fields.put("name","name");
        fields.put("pass","pass");
        fields.put("codeDept","code_dept");
        fields.put("nameDept","name_dept");
        fields.put("image","image");
        return JDBCUtil.queryList(sql.toString(),values,EmployeeModel.class,fields);
    }

    @Override
    public EmployeeModel selectModel(EmployeeModel model) {
        StringBuffer sql = new StringBuffer("select id,");
        sql.append(clos).append(" from ").append(table).append(" where code = ? ");
        List<Object>values= Arrays.asList(model.getCode());
        Map<String, String> fields=new HashMap<>();
        fields.put("id","id");
        fields.put("code","code");
        fields.put("name","name");
        fields.put("pass","pass");
        fields.put("codeDept","code_dept");
        fields.put("image","image");
        return JDBCUtil.queryModel(sql.toString(), values,EmployeeModel.class,fields);
    }

    @Override
    public Integer selectCount(EmployeeModel model) {
        StringBuffer sql =new StringBuffer("select count(1) ").append(" from ").append(table);
        List<Object>values=appendWhere(sql,model);
        return JDBCUtil.queryInt(sql.toString(),values);
    }

    private List<Object> appendWhere(StringBuffer sql, EmployeeModel model) {
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
        String code_dept=model.getCodeDept();
        if (!FmtEmpty.isEmpty(code_dept)){
            sql.append(" and code_dept like ? ");
            values.add(code_dept);
        }
        String image=model.getImage();
        if (!FmtEmpty.isEmpty(image)){
            sql.append(" and image like ? ");
            values.add(image);
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

    private List<Object> appendSet(StringBuffer sql,EmployeeModel model){
        List<Object>values = new ArrayList<Object>();
        String name = model.getName();
        if (name != null){
            sql.append(",name = ? ");
            values.add(name);
        }
        String code_dept = model.getCodeDept();
        if (code_dept != null){
            sql.append(",code_dept = ? ");
            values.add(code_dept);
        }
        String pass = model.getPass();
        if (pass != null){
            sql.append(",pass = ? ");
            values.add(pass);
        }
        String image = model.getImage();
        if (image != null) {
            sql.append(",image = ? ");
            values.add(image);
        }
        sql.append(" where code = ? ");
        values.add(model.getCode());
        return values;
    }
}
