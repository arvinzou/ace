package com.huacainfo.ace.portal.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by chenxiaoke on 2017/6/29.
 */
public class FileUploadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);

	public FileUploadServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
                logger.info("fieldName:{}", item.getFieldName());
				if (item.isFormField()) {
					String value = item.getString("utf-8");
                    logger.info("FormField:{}", value);
				} else {
                    logger.info("---fieldName:{}", item.getFieldName());
				}
			}
		} catch (Exception e) {
            logger.error("{}",e);
		}
		PrintWriter out = response.getWriter();
		out.write("OK");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
