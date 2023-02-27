package com.hwq.company.employee.servlet;



import com.hwq.company.employee.model.EmployeeModel;
import com.hwq.company.employee.service.IEmployeeService;
import com.hwq.company.employee.service.impl.EmployeeServiceImpl;
import com.hwq.company.util.FmtEmpty;
import com.hwq.company.util.FmtRequest;
import com.hwq.company.util.FmtUpload;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "EmployeeUploadServlet", value = "/EmployeeUploadServlet")
public class EmployeeUploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    private IEmployeeService service = new EmployeeServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String,Object> map =null;
        try {
            map= FmtUpload.Upload(request);
        } catch (FileUploadException |IOException e) {
            e.printStackTrace();
        }
        String code=map.get("code").toString();
        List<String>filenames = (List<String>)map.get(FmtUpload.FILE_NAMES);
        String image=filenames.get(0);

        EmployeeModel model = new EmployeeModel(code);

        EmployeeModel mdb =service.selectModel(model);
        if(!FmtEmpty.isEmpty(mdb.getImage()))//判断是否存在头像，存在直接替换
            FmtUpload.delFile(mdb.getImage());

        model.setImage(image);
        String res= service.update(model);

        Map<String,String> result = new HashMap<String, String>();
        result.put("code",res);
        result.put("image",image);
        FmtRequest.write(response.getWriter(),result);
    }
}
