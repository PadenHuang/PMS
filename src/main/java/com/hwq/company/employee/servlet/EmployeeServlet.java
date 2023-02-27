package com.hwq.company.employee.servlet;


import com.hwq.company.employee.model.EmployeeModel;
import com.hwq.company.employee.service.IEmployeeService;
import com.hwq.company.employee.service.impl.EmployeeServiceImpl;
import com.hwq.company.util.FmtRequest;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工servlet——处理请求
 */
@WebServlet(name = "EmployeeServlet", value = "/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IEmployeeService service = new EmployeeServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * post请求菜单——处理各种动作
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object res = null;
        switch (req.getParameter("action")) {
            case "reg":
                res = reg(req);
                break;
            case "login":
                res = login(req);
                break;
            case "logout":
                req.getSession().removeAttribute("user");
                resp.sendRedirect(req.getContextPath() + "/web/login.jsp");
                return;
            case "list":
                res = list(req);
                break;
            case "page":
                res = page(req);
                break;
            case "add":
                res = add(req);
                break;
            case "del":
                res = del(req);
                break;
            case "get":
                res = get(req);
                break;
            case "upd":
                res = upd(req);
                break;
            case "resPass":
                res = resPass(req);
                break;
            case "passUpd":
                res = passUpd(req);
                break;
            case "setDept":
                res = setDept(req);
                break;
            case "delPic":
                res = delPic(req);
                break;
        }
//        PrintWriter writer = resp.getWriter();
//        writer.write(res);
//        writer.flush();
//        writer.close();
//        writer = null;
        FmtRequest.write(resp.getWriter(),res);
    }




    /**
     * 登录
     * @param req
     * @return
     */
    private String login(HttpServletRequest req) {
        EmployeeModel model=FmtRequest.parseModel(req,EmployeeModel.class);
        String res=service.login(model);
        if("1".equals(res))
            req.getSession().setAttribute("user",service.selectModel(model));
        return res;
    }

    /**
     * 注册
     * @param req
     * @return
     */
    private String reg(HttpServletRequest req) {
//        String code = req.getParameter("code");
//        String name = req.getParameter("name");
//        String pass = req.getParameter("pass");
//        EmployeeModel model = new EmployeeModel(code, name, pass);
        EmployeeModel model=FmtRequest.parseModel(req,EmployeeModel.class);
        return service.insert(model);
    }
    /**
     * 分页功能
     * @param req
     * @return
     */
    private Object page(HttpServletRequest req) {
        String code = req.getParameter("code");
        String name = req.getParameter("name");
        String pageIndex = req.getParameter("pageIndex");
        String pageLimit = req.getParameter("pageLimit");
        EmployeeModel model =new EmployeeModel();
        model.setCode(code);
        model.setName(name);
        model.setPageIndex(Integer.parseInt(pageIndex));
        model.setPageLimit(Integer.parseInt(pageLimit));
        model.setPageOn(true);
        List<EmployeeModel> list= service.selectList(model);
        //带有查询条件和分页条件，得到的当前页面要显示的数据
        Integer count= service.selectCount(model);
        //带有查询条件，不带有分页条件，得到的记录条数
        Map<String,Object> map = new HashMap<>();
        map.put("list",list);
        map.put("count",count);
        return map;
    }

    /**
     * 修改
     * @param req
     * @return
     */
    private String upd(HttpServletRequest req) {
        return service.update(FmtRequest.parseModel(req,EmployeeModel.class));
    }

    /**
     * 修改密码
     * @param req
     * @return
     */
    private String passUpd(HttpServletRequest req) {
        return service.passUpd(FmtRequest.parseModel(req,EmployeeModel.class));
    }

    /**
     * 设置部门
     * @param req
     * @return
     */
    private String setDept(HttpServletRequest req) {
        return service.update(FmtRequest.parseModel(req,EmployeeModel.class));
    }

    /**
     * 回显
     * @param req
     * @return
     */
    private EmployeeModel get(HttpServletRequest req) {
        return service.selectModel(FmtRequest.parseModel(req,EmployeeModel.class));
    }

    /**
     * 重置密码
     * @param req
     * @return
     */
    private String resPass(HttpServletRequest req) {
        return service.resPass(FmtRequest.parseModel(req,EmployeeModel.class));
    }

    /**
     * 删除图片
     * @param req
     * @return
     */
    private String delPic(HttpServletRequest req) {
        return service.delPic(FmtRequest.parseModel(req, EmployeeModel.class));
    }

    /**
     * 删除
     * @param req
     * @return
     */
    private String del(HttpServletRequest req) {
//        String code=req.getParameter("code");
//        EmployeeModel model=new EmployeeModel(code);
//        return service.delete(model);
        return service.delete(FmtRequest.parseModel(req, EmployeeModel.class));
    }

    /**
     * 添加
     * @param req
     * @return
     */
    private String add(HttpServletRequest req) {
        EmployeeModel model = FmtRequest.parseModel(req, EmployeeModel.class);
        return service.insert(model);
    }

    /**
     * 查询
     * @param req
     * @return
     */
    private List<EmployeeModel> list(HttpServletRequest req) {
        EmployeeModel model = FmtRequest.parseModel(req, EmployeeModel.class);
        return service.selectList(model);
    }
}
