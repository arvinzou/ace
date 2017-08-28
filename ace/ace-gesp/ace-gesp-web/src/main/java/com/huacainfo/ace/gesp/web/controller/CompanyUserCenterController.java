package com.huacainfo.ace.gesp.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.service.RegService;
import com.huacainfo.ace.portal.service.DictService;
import com.huacainfo.ace.portal.service.FilesService;
import com.huacainfo.ace.portal.service.SystemService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
@RequestMapping("/companyUserCenter")
public class CompanyUserCenterController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private RegService regService;
	
	@Autowired
	private SystemService systemService;

	@Autowired
	private DictService dictService;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@SuppressWarnings("deprecation")
	private static Configuration configuration = new Configuration();

	@RequestMapping(value = "/downloadWord.do")
	@ResponseBody
	private void downloadWord(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Department de = regService.selectDepartInfoByPrimaryKey(this.getCurUserProp().getCorpId()).getValue();
		createWord(request, response, de);
	}

	private void createWord(HttpServletRequest request, HttpServletResponse response, Department de)
			throws Exception {
		SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd");
		String regAddr = systemService.selectProvinceNameById(de.getRegPro())+systemService.selectProvinceNameById(de.getRegCity())+
				systemService.selectProvinceNameById(de.getRegAreaCode())+de.getRegAddr();
		String bussAddr = systemService.selectProvinceNameById(de.getBussPro())+systemService.selectProvinceNameById(de.getBussCity())+
				systemService.selectProvinceNameById(de.getBussAreaCode())+de.getBussAddr();
		
		Map<Object, Object> params = new HashMap<Object, Object>();
		params.put("departmentName", de.getDepartmentName());
		params.put("bussNum", de.getBussNum());
		params.put("regDate", sb.format(de.getRegDate()));
		params.put("orgCode", de.getOrgCode());
		params.put("regCapital", de.getRegCapital());
		params.put("assetTotal", de.getAssetTotal());
		params.put("netAsset", de.getNetAsset());
		params.put("yearSale", de.getYearSale());
		params.put("yearShall", de.getYearShall());
		params.put("employeesNum", de.getEmployeesNum());
		params.put("ownerVehicle", de.getOwnerVehicle());
		params.put("vehicleNum", de.getVehicleNum());
		params.put("departType", de.getDepartType());
		params.put("ownStorageArea", de.getOwnStorageArea());
		params.put("storageArea", de.getStorageArea());
		params.put("regAddr", regAddr);
		params.put("intnetNum", de.getIntnetNum());
		params.put("bussAddr", bussAddr);
		params.put("mainPeo", de.getMainPeo());
		//params.put("mainPost", dictService.selectByCaIdAndCode("05",de.getMainPost()).getValue().getName());
		params.put("mainPhone", de.getMainPhone());
		params.put("mainEmail", de.getMainEmail());
		params.put("mainTel", de.getMainTel());
		params.put("companyIp", de.getCompanyIp());
		params.put("ruote", de.getRuote());
		params.put("contactName", de.getContactName());
		//params.put("contactPost", dictService.selectByCaIdAndCode("05",de.getContactPost()).getValue().getName());
		params.put("contactTel", de.getContactTel());
		params.put("contactEmail", de.getContactEmail());
		params.put("contactMobile", de.getContactMobile());
		params.put("scopeBuss", de.getScopeBuss());

		String path = getRequest().getSession().getServletContext().getRealPath("/content/download/");
		exportMillCertificateWord(request, response, params, this.getCurUserProp().getParentCorpId(), path);
	}

	public static void exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, Map<Object, Object> map,
			String cId, String path) throws IOException {
		configuration.setDefaultEncoding("utf-8");
		configuration.setDirectoryForTemplateLoading(new File(path));

		String templFileName = cId + ".ftl";
		Template freemarkerTemplate = configuration.getTemplate(templFileName);
		File file = null;
		InputStream fin = null;
		ServletOutputStream out = null;
		try {
			// 调用工具类的createDoc方法生成Word文档
			file = createDoc(map, freemarkerTemplate, cId);
			fin = new FileInputStream(file);

			response.setCharacterEncoding("utf-8");
			response.setContentType("application/msword");
			// 设置浏览器以下载的方式处理该文件名
			String fileName = "企业入会申请表.doc";
			response.setHeader("Content-Disposition",
					"attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));

			out = response.getOutputStream();
			byte[] buffer = new byte[512]; // 缓冲区
			int bytesToRead = -1;
			// 通过循环将读入的Word文件的内容输出到浏览器中
			while ((bytesToRead = fin.read(buffer)) != -1) {
				out.write(buffer, 0, bytesToRead);
			}
		} finally {
			if (fin != null)
				fin.close();
			if (out != null)
				out.close();
			if (file != null)
				file.delete(); // 删除临时文件
		}
	}

	private static File createDoc(Map<?, ?> dataMap, Template template, String cId) {
		String name = cId + ".doc";
		File f = new File(name);
		Template t = template;
		try {
			Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
			t.process(dataMap, w);
			w.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
		return f;
	}

}
