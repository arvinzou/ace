package com.huacainfo.ace.operana.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.operana.model.Meeting;
import com.huacainfo.ace.operana.model.NormData;
import com.huacainfo.ace.operana.service.MeetingService;
import com.huacainfo.ace.operana.service.NormDataService;
import com.huacainfo.ace.operana.vo.NormDataQVo;
import com.huacainfo.ace.operana.vo.NormDataVo;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.common.result.PageResult;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;

@Controller
@RequestMapping("/normData")
public class NormDataController extends OperanaBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NormDataService normDataService;

    @Autowired
    private MeetingService meetingService;




	private List<Integer> wk = new ArrayList<Integer>();




	public NormDataController(){
		for (int i = 1; i < 54; i++) {
			wk.add(i);
		}
	}

	@RequestMapping(value = "/deleteNormDataByNormDataId.do")
	@ResponseBody
	public MessageResponse deleteNormDataByNormDataId(String id)
			throws Exception {
		return this.normDataService.deleteNormDataByNormDataId(id,
				this.getCurUserProp());
	}

	@RequestMapping(value = "/importXls.do")
	@ResponseBody
	public MessageResponse importXls(@RequestParam MultipartFile[] file,
									 String meetingId) throws Exception {
	    Map<String,Object> params=new HashMap<String,Object>();
        params.put("meetingId",meetingId);
        this.logger.info("meetingId->",meetingId);
        Map<String,String> dict=this.normDataService.selectTopicDictByMeetingId(meetingId);
		ExcelUtils utils = new ExcelUtils();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		MongoFile[] files = new MongoFile[file.length];
		int i = 0;
		for (MultipartFile o : file) {
			MongoFile obj = new MongoFile();
			obj.setInputStream(o.getInputStream());
			obj.setFilename(o.getOriginalFilename());
			obj.setLength(o.getSize());
			files[i] = obj;
			i++;
			String ext = obj
					.getFilename()
					.toLowerCase()
					.substring(
							obj.getFilename().toLowerCase()
									.lastIndexOf("."));
			this.logger.info(ext);
			if (ext.equals(".xls")) {
                WorkbookSettings workbookSettings = new WorkbookSettings();
                workbookSettings.setEncoding("GBK"); //解决中文乱码，或GBK
                Workbook workbook = Workbook.getWorkbook(o.getInputStream(),workbookSettings);
                String [] sheetNames=workbook.getSheetNames();
                for(String sheetName:sheetNames){
                    params.put("topicId",dict.get(sheetName));
                    this.logger.info("sheetName->{}",sheetName);
                    list = utils.readExcelByJXL(o.getInputStream(), 2,sheetName);
                    MessageResponse rst=this.normDataService.importXls(list,params,sheetName, this.getCurUserProp());
                    if(rst.getStatus()==1){
                        return rst;
                    }
                }

			}
			if (ext.equals(".xlsx")) {
                org.apache.poi.ss.usermodel.Workbook workbook =  new HSSFWorkbook(o.getInputStream());;
                int nn=workbook.getNumberOfNames();
                for(int k=0;k<nn;k++){
                    String sheetName= workbook.getSheetName(k);
                    list = utils.readExcelByPOI(o.getInputStream(), 2,sheetName);
                    params.put("topicId",dict.get(sheetName));
                    MessageResponse rst=this.normDataService.importXls(list,params,sheetName, this.getCurUserProp());
                    if(rst.getStatus()==1){
                        return rst;
                    }
                }

			}
		}
		return new MessageResponse(0,"导入成功！");
	}

	@RequestMapping(value = "/exportXls.do")
	public void exportXls(String meetingId,HttpServletResponse response) throws Exception {
        Meeting meeting=meetingService.selectMeetingByPrimaryKey(meetingId).getValue();
		String fname = meeting.getName();
		OutputStream os = response.getOutputStream();//取得输出流
		response.reset();//清空输出流
		//下面是对中文文件名的处理
		response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
		fname = java.net.URLEncoder.encode(fname,"UTF-8");
		response.setHeader("Content-Disposition","attachment;filename="+new String(fname.getBytes("UTF-8"),"GBK")+".xls");
		response.setContentType("application/msexcel");//定义输出类型
		WritableWorkbook workbook = Workbook.createWorkbook(os);
        List <Map<String,Object>> list=normDataService.selectTopicByMeetingId(meetingId);
        int i=0;
        for(Map<String,Object> o:list){
            String topicId=(String) o.get("id");
            String sheetName=(String) o.get("name");
            this.createSheet(meetingId,topicId,sheetName,i,workbook);
            i++;
        }
		workbook.write();
		workbook.close();
		os.close();
	}

	private void createSheet(String meetingId,String topicId,String sheetName,int i,WritableWorkbook workbook) throws Exception{
        List<Map<String,Object>> list=this.normDataService.selectNormByMeetingAndTopicId(meetingId,topicId);

        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 14,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.WHITE); // 定义格式 字体 下划线 斜体 粗体 颜色

        WritableFont wf_title_hidden = new WritableFont(WritableFont.ARIAL, 0,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.WHITE); // 定义格式 字体 下划线 斜体 粗体 颜色

        WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 12,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色

        WritableFont wf_grid = new WritableFont(WritableFont.ARIAL, 12,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色

        WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
        wcf_title.setBackground(Colour.BLUE2); // 设置单元格的背景颜色
        wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
        wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

        WritableCellFormat wcf_title_hidden = new WritableCellFormat(wf_title_hidden); // 单元格定义
        wcf_title_hidden.setBackground(Colour.WHITE); // 设置单元格的背景颜色
        wcf_title_hidden.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
        wcf_title_hidden.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.WHITE);

        WritableCellFormat wcf_head = new WritableCellFormat(wf_head);
        wcf_head.setBackground(Colour.YELLOW);
        wcf_head.setAlignment(jxl.format.Alignment.CENTRE);
        wcf_head.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);


        WritableCellFormat wcf_grid = new WritableCellFormat(wf_grid);
        wcf_grid.setBackground(Colour.WHITE);
        wcf_grid.setAlignment(jxl.format.Alignment.CENTRE);
        wcf_grid.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);

        CellView view = new CellView();
        view.setHidden(true);
        //创建新的一页
        WritableSheet sheet = workbook.createSheet(sheetName,i);
        sheet.setColumnView(0, 25); // 设置列的宽度
        sheet.setColumnView(1, 20); // 设置列的宽度
        sheet.setColumnView(2, 20); // 设置列的宽度
        sheet.setColumnView(1, view);

        sheet.setRowView(0,0);
        int row=0,col=0;
        Label c00 = new Label(col,row,"Name",wcf_title_hidden);

        sheet.addCell(c00);
        col++;
        Label c10 = new Label(col,row,"normId",wcf_title_hidden);
        sheet.addCell(c10);
        col++;
		/*Label c20 = new Label(col,row,"productId",wcf_title_hidden);
		sheet.addCell(c20);
		col++;
		Label c30 = new Label(col,row,"year",wcf_title_hidden);
		sheet.addCell(c30);
		col++;*/
        Label c40 = new Label(col,row,"lastYear",wcf_title_hidden);
        sheet.addCell(c40);
        col++;
        for (int k : wk) {
            Label e = new Label(col,row,"wk"+k,wcf_title_hidden);
            sheet.addCell(e);
            col++;
        }
        row=1;
        col=0;
        Label c01 = new Label(col,row,"指标名称",wcf_title);

        sheet.addCell(c01);
        col++;
        Label c11 = new Label(col,row,"指标编码",wcf_title);
        sheet.addCell(c11);
        col++;
        /*Label c21 = new Label(col,row,"产品编码",wcf_title);
        sheet.addCell(c21);
        col++;
        Label c31 = new Label(col,row,"年度",wcf_title);
        sheet.addCell(c31);
        col++;*/
        Calendar a=Calendar.getInstance();
        int year=a.get(Calendar.YEAR);
        Label c41 = new Label(col,row,(year-1)+"年",wcf_title);
        sheet.addCell(c41);
        col++;
        for (int k : wk) {
            Label e = new Label(col,row,"wk"+k,wcf_title);
            sheet.addCell(e);
            col++;
        }
        row=2;
        col=0;
        for(Map<String,Object> o:list){
            this.logger.info("{}",o);
            col=0;
            String id=(String) o.get("normIds");
            String name=(String) o.get("name");
            String calType=(String) o.get("calType");
            Label e1 = new Label(col,row,name,wcf_head);
            sheet.addCell(e1);
            col++;
            Label e2 = new Label(col,row,id,wcf_head);
            sheet.addCell(e2);
            col++;
            BigDecimal lastYear=(BigDecimal) o.get("lastYear");
            if(CommonUtils.isBlank(lastYear)){
                Label e3 = new Label(col,row,"",wcf_grid);
                sheet.addCell(e3);
                col++;
            }else {
                Label e3 = new Label(col,row,lastYear.toString(),wcf_grid);
                sheet.addCell(e3);
                col++;
            }

            for (int k : wk) {
                BigDecimal t=(BigDecimal) o.get("wkt"+k);
                BigDecimal c=(BigDecimal) o.get("wkc"+k);
                String cont="";
                if(calType.equals("1")){
                    if(!CommonUtils.isBlank(t)){
                        cont=CommonUtils.getPrettyNumber(t.toString());
                    }
                }else{
                    if(CommonUtils.isBlank(t)||CommonUtils.isBlank(c)){
                        cont="";
                    }else{
                        cont=CommonUtils.getPrettyNumber(c.toString())+"/"+CommonUtils.getPrettyNumber(t.toString());
                    }
                }
                Label e = new Label(col,row,cont,wcf_grid);
                sheet.addCell(e);
                col++;
            }
            row++;
        }
    }
    @RequestMapping(value = "/findNormDataList.do")
    @ResponseBody
    public PageResult<NormDataVo> findNormDataList(NormDataQVo condition,
                                                   PageParamNoChangeSord page) throws Exception {
        PageResult<NormDataVo> rst = this.normDataService
                .findNormDataList(condition, page.getStart(), page.getLimit(),
                        page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    @RequestMapping(value = "/saveOrUpdateNormData.do")
    @ResponseBody
    public MessageResponse saveOrUpdateNormData( NormDataVo obj) throws Exception {
        return this.normDataService
                .saveOrUpdateNormData(obj, this.getCurUserProp());
    }
}
