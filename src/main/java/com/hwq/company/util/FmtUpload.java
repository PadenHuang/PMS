package com.hwq.company.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public final class FmtUpload {

    public final static String FILE_NAMES="filenames";

    /**
     * 实现服务器端保存上传多张图片
     * @param request
     * @throws FileUploadException
     * @throws IOException
     * @return Map key=非文件域参数名 val=非文件域参数值（key=filenames value=保存图片文件名集合）
     */
    // 实现服务器端保存上传图片的功能
    public static Map<String, Object> Upload(HttpServletRequest request) throws FileUploadException, IOException {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<FileItem> items = upload.parseRequest(request);
        Map<String,Object> result = new HashMap<>();
        List<String> names = new ArrayList<>();
        for (FileItem item : items) {
            if (item.isFormField()) {// 不是文件域表单元素
                String name = item.getFieldName();
//                System.out.println("name=" + name);
                String val = item.getString();
                val = new String(val.getBytes("ISO-8859-1"), "UTF-8");
                result.put(name,val);
//                System.out.println("value=" + val);
            } else {// 是文件域表单元素--保存文件
//                System.out.println(item.getFieldName() + " " + item.getName());
                String fn = getFName(item);
                FileOutputStream fos = new FileOutputStream(getFPath() + "/" + fn);
                InputStream is = item.getInputStream();
                int length = -1;
                byte[] b = new byte[1024 * 1024];
                while (-1 != (length = is.read(b)))
                    fos.write(b, 0, length);
                fos.flush();
                fos.close();
                is.close();
//                request.setAttribute("key"+(i++),"/temp/"+fn);
                names.add(fn);
            }
        }
        result.put(FILE_NAMES,names);
        return result;
    }

    private static String getFName(FileItem item) {// 获得唯一的文件名，加上上传文件的拓展名
        String fn = item.getName();
        String uuid = UUID.randomUUID().toString();
        fn = uuid + fn.substring(fn.indexOf("."));
        return fn;
    }

    private static File getFPath() {// 保存上传来的文件目录
        File f = new File("F:\\temp\\AAA");
        if (!f.exists())
            f.mkdir();
        return f;
    }

    public static void delFile(String filename){
        File f = new File(getFPath()+"/"+filename);
        f.delete();
    }
}
