package com.hwq.company.util;



import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

/**
 * JDBC工具类
 */
public final class JDBCUtil {

    /*private static ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
    private static String driver = resourceBundle.getString("driver");
    private static String url = resourceBundle.getString("url");
    private static String user = resourceBundle.getString("user");
    private static String pass = resourceBundle.getString("pass");*/

    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("jdbc");
    private static String driver = resourceBundle.getString("driver");
    private static String url = resourceBundle.getString("url");
    private static String user = resourceBundle.getString("user");
    private static String pass = resourceBundle.getString("pass");

    static {//加载驱动
        try {
            Class.forName(driver);
            System.out.println("驱动加载完成！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**获取链接
     * @return
     */
    public static Connection getConnection() {
        try {
            System.out.println("获取连接。。。");
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**执行DML
     * @param sql
     * @param values
     * @return Integer
     */
    public static Integer update(String sql,Object ... values){
        return update(sql, Arrays.asList(values));
    }
    //数组中元素类型是引用类型=》asLIst中返回的list元素就是数组中的元素
    //数组中元素类型是基本数据=》asLIst中返回的list元素只有一个，即传入进来的这个数组

    /**
     * 执行DML
     * @param sql
     * @param values
     * @return Integer
     */
    public static  Integer update(String sql, List<Object> values){
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn=getConnection();
            System.out.println("数据库连接成功："+conn);
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++)
                ps.setObject(i + 1, values.get(i));
            return ps.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,ps);
        }
        return null;
    }

    /**执行DQL
     *
     *@param <T> 返回集合中的元素类型(即表对应的实体类)
     * @param sql 即将执行的sql
     * @param values 执行sql中的数据(?)
     * @param clazz 即将反射得到的实体类的类的描述
     * @param fields 映射关系key=属性名 value=字段名
//     * @param List<T> 返回查询得到的记录对象的集合
     */
    public static  <T> List<T> queryList(String sql, List<Object> values, Class<T> clazz, Map<String,String> fields){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<T> result=new ArrayList<>();
        try {
            conn=getConnection();
            System.out.println("数据库连接成功："+conn);
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++){
                ps.setObject(i+1, values.get(i));
            }rs=ps.executeQuery();
            while (rs.next()) {
                T model = clazz.newInstance();
                for (Map.Entry<String,String> entry:fields.entrySet()){
                    Field field= clazz.getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    field.set(model,rs.getObject(entry.getValue()));
                }
                result.add(model);
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            close(conn,ps,rs);
        }
        return result;
    }

    /**
     * 执行sql，表的字段名与实体类属性名一样的情况
     * @param sql
     * @param values
     * @param clazz
     * @param props
     * @param <T>
     * @return
     */
    public static  <T> List<T> queryList(String sql, List<Object> values, Class<T> clazz, List<String>props){
        Map<String, String> fields = new HashMap<>();
        for(String p:props)
            fields.put(p,p);
        return queryList(sql,values, clazz, fields);
    }

    /**
     * 执行DQL，实体类的属性名与表的字段名一样的情况
     * @param sql
     * @param values
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> List<T> queryList(String sql, List<Object> values, Class<T> clazz){
        Map<String, String> fields = new HashMap<>();
        for(Field p:clazz.getDeclaredFields())
            fields.put(p.getName(),p.getName());
        return queryList(sql,values, clazz, fields);
    }

    /**
     * 执行DQL,返回一个结果
     * @param sql
     * @param values
     * @param clazz
     * @param fields
     * @param <T>
     * @return
     */
    public static<T> T queryModel(String sql, List<Object> values,Class<T> clazz, Map<String,String> fields){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn=getConnection();
            System.out.println("数据库连接成功："+conn);
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++){
                ps.setObject(i+1, values.get(i));
            }rs=ps.executeQuery();
            if (rs.next()) {
                T model = clazz.newInstance();
                for (Map.Entry<String,String> entry:fields.entrySet()){
                    Field field= clazz.getDeclaredField(entry.getKey());
                    field.setAccessible(true);
                    field.set(model,rs.getObject(entry.getValue()));
                }
                return model;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
            close(conn,ps,rs);
        }
        return null;
    }

    public static  <T> T queryModel(String sql, List<Object> values, Class<T> clazz, List<String>props){
        Map<String, String> fields = new HashMap<>();
        for(String p:props)
            fields.put(p,p);
        return queryModel(sql,values, clazz, fields);
    }

    public static  <T> T queryModel(String sql, List<Object> values, Class<T> clazz){
        Map<String, String> fields = new HashMap<>();
        for(Field p:clazz.getDeclaredFields())
            fields.put(p.getName(),p.getName());
        return queryModel(sql,values, clazz, fields);
    }

    /**
     * 根据条件查询得到记录条数
     * @param sql
     * @param values
     * @return
     */
    public static Integer queryInt(String sql,List<Object>values){
        Connection conn = null;
        PreparedStatement ps=null;
        try{
            conn=getConnection();
            System.out.println("数据库连接成功："+conn);
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < values.size(); i++)
                ps.setObject(i + 1, values.get(i));
            ResultSet rs= ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(conn,ps);
        }
        return null;
    }

    /**
     * 关闭连接
     * @param connection
     * @param statement
     */
    public static void close(Connection connection, Statement statement) {
        close(connection, statement, null);
    }

    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null) {
                connection.close();
                connection = null;
            }
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (resultSet != null) {
                resultSet = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
