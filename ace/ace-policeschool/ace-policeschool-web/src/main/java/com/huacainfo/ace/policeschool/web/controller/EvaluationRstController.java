package com.huacainfo.ace.policeschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.policeschool.model.EvaluationRst;
import com.huacainfo.ace.policeschool.service.EvaluationRstService;
import com.huacainfo.ace.policeschool.vo.EvaluationRstQVo;
import com.huacainfo.ace.policeschool.vo.EvaluationRstVo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/evaluationRst")
/**
 * @author: 王恩
 * @version: 2019-01-08
 * @Description: TODO(测评结果管理)
 */
public class EvaluationRstController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EvaluationRstService evaluationRstService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(测评结果管理分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <EvaluationRstVo>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/findEvaluationRstList")
    @ResponseBody
    public PageResult<EvaluationRstVo> findEvaluationRstList(EvaluationRstQVo condition, PageParamNoChangeSord page) throws Exception {

        PageResult<EvaluationRstVo> rst = this.evaluationRstService
                .findEvaluationRstList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }

    @RequestMapping(value = "/findEvaluationRstListVo")
    @ResponseBody
    public PageResult<EvaluationRstVo> findEvaluationRstListVo(EvaluationRstQVo condition,
                                                               PageParamNoChangeSord page) throws Exception {
        PageResult<EvaluationRstVo> rst = this.evaluationRstService
                .findEvaluationRstListVo(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }
        return rst;
    }


    /**
     * @throws
     * @Title:insertEvaluationRst
     * @Description: TODO(添加测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/insertEvaluationRst")
    @ResponseBody
    public MessageResponse insertEvaluationRst(String jsons) throws Exception {
        EvaluationRst obj = JSON.parseObject(jsons, EvaluationRst.class);
        return this.evaluationRstService.insertEvaluationRst(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateEvaluationRst
     * @Description: TODO(更新测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/updateEvaluationRst")
    @ResponseBody
    public MessageResponse updateEvaluationRst(String jsons) throws Exception {
        EvaluationRst obj = JSON.parseObject(jsons, EvaluationRst.class);
        return this.evaluationRstService.updateEvaluationRst(obj, this.getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectEvaluationRstByPrimaryKey
     * @Description: TODO(获取测评结果管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<EvaluationRst>
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/selectEvaluationRstByPrimaryKey")
    @ResponseBody
    public SingleResult<EvaluationRstVo> selectEvaluationRstByPrimaryKey(String id) throws Exception {
        return this.evaluationRstService.selectEvaluationRstByPrimaryKey(id);
    }


    @RequestMapping(value = "/statistics")
    @ResponseBody
    public ResultResponse statistics(String classScheduleId) throws Exception {
        return this.evaluationRstService.statistics(classScheduleId);
    }

    /**
     * @throws
     * @Title:deleteEvaluationRstByEvaluationRstId
     * @Description: TODO(删除测评结果管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-08
     */
    @RequestMapping(value = "/deleteEvaluationRstByEvaluationRstId")
    @ResponseBody
    public MessageResponse deleteEvaluationRstByEvaluationRstId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("id");
        return this.evaluationRstService.deleteEvaluationRstByEvaluationRstId(id, this.getCurUserProp());
    }


    @RequestMapping(value = "/exportData")
    @ResponseBody
    public ResultResponse exportData(String id, HttpServletResponse response) throws Exception {
        SingleResult<List<Map<String, String>>> rr = evaluationRstService.exportData(id);
        if (CommonUtils.isBlank(rr.getValue())) {
            return null;
        }
        WriteExcel(rr.getValue(), response);
        return null;
    }

    public <T> void WriteExcel(List<Map<String, String>> data, HttpServletResponse response) throws Exception {
        /*创建co*/
        XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("测评内容");
        Field[] fields = null;
        Set<String> set = data.get(0).keySet();
        for (int i = 0; i < data.size(); i++) {
            int j = 0;
            if (i == 0) {
                Row row = sheet.createRow(i);
                for (String key : set) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(key);
                    j++;
                }
                j = 0;
            }
            Row row = sheet.createRow(i + 1);
            Map<String, String> temp = data.get(i);
            for (String key : set) {
                Cell cell = row.createCell(j);
                cell.setCellValue(temp.get(key));
                j++;
            }
        }
        try {
            response.setHeader("Content-Disposition", "attachment;Filename=" + System.currentTimeMillis() + ".xlsx");
            OutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            response.flushBuffer();
//JOptionPane.showMessageDialog(null, "导出成功");
        } catch (FileNotFoundException e) {
//JOptionPane.showMessageDialog(null, "导出失败");
            e.printStackTrace();
        } catch (IOException e) {
//JOptionPane.showMessageDialog(null, "导出失败");
            e.printStackTrace();
        }
    }


}
