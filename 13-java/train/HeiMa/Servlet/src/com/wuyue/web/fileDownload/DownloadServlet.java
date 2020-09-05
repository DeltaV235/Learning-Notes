package com.wuyue.web.fileDownload;

import com.wuyue.web.utils.DownLoadUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author DeltaV235
 * @version 1.0
 * @className DownloadServlet
 * @description 通过传入的请求参数，响应对应的文件
 * @date 2020/2/16 12:41
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String fileName = request.getParameter("file");
        ServletOutputStream outputStream = response.getOutputStream();

        if (fileName == null)
            return;
        String realFilePath = getServletContext().getRealPath(fileName);
        File file = new File(realFilePath);

        String agent = request.getHeader("user-agent");
        response.setHeader("content-disposition", "attachment;filename=" + DownLoadUtils.getFileName(agent, file.getName()));
        response.setContentType(getServletContext().getMimeType(fileName));
//        System.out.println("file.getPath() = " + file.getPath());
        BufferedInputStream bi = new BufferedInputStream(new FileInputStream(file));
        byte[] flush = new byte[1024 * 4];
        int length = -1;

//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((length = bi.read(flush)) != -1) {
            // 读取4个字节
            outputStream.write(flush, 0, length);
            // 将增长图片的字节读入到内存中后，一次性响应给客户端
//            baos.write(flush, 0, length);
        }
//        outputStream.write(baos.toByteArray());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
