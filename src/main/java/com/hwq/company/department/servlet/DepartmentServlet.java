package com.hwq.company.department.servlet;

import com.hwq.company.department.model.DepartmentModel;
import com.hwq.company.department.service.IDepartmentService;
import com.hwq.company.department.service.impl.DepartmentServiceImpl;
import com.hwq.company.util.FmtRequest;
/*import com.situ.company.department.model.DepartmentModel;
import com.situ.company.department.service.IDepartmentService;
import com.situ.company.department.service.impl.DepartmentServiceImpl;
import com.situ.company.util.FmtRequest;*/

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IDepartmentService service = new DepartmentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object res = null;
        switch (req.getParameter("action")) {
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
        }
        FmtRequest.write(resp.getWriter(), res);
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
        DepartmentModel model =new DepartmentModel();
        model.setCode(code);
        model.setName(name);
        model.setPageIndex(Integer.parseInt(pageIndex));
        model.setPageLimit(Integer.parseInt(pageLimit));
        model.setPageOn(true);
        List<DepartmentModel>list= service.selectList(model);
        //带有查询条件和分页条件，得到的当前页面要显示的数据
        Integer count= service.selectCount(model);
        //带有查询条件，不带有分页条件，得到的记录条数
        Map<String,Object>map = new HashMap<>();
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
        return service.update(FmtRequest.parseModel(req,DepartmentModel.class));
    }

    /**
     * 回显
     * @param req
     * @return
     */
    private DepartmentModel get(HttpServletRequest req) {
        return service.selectModel(FmtRequest.parseModel(req,DepartmentModel.class));
    }

    /**
     * 删除
     * @param req
     * @return
     */
    private String del(HttpServletRequest req) {
        String code=req.getParameter("code");
        DepartmentModel model=new DepartmentModel(code);
        return service.delete(model);
//        return service.delete(FmtRequest.parseModel(req, DepartmentModel.class));
    }

    /**
     * 添加
     * @param req
     * @return
     */
    private String add(HttpServletRequest req) {
        DepartmentModel model = FmtRequest.parseModel(req, DepartmentModel.class);
        return service.insert(model);
    }

    /**
     * 查询
     * @param req
     * @return
     */
    private List<DepartmentModel> list(HttpServletRequest req) {
        DepartmentModel model = FmtRequest.parseModel(req, DepartmentModel.class);
        return service.selectList(model);
    }
}
