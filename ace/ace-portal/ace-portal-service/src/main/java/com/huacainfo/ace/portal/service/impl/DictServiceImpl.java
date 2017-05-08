package com.huacainfo.ace.portal.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.ibatis.jdbc.SqlRunner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.service.WebContextDictService;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.DictCategoryMapper;
import com.huacainfo.ace.portal.dao.DictDao;
import com.huacainfo.ace.portal.dao.DictMapper;
import com.huacainfo.ace.portal.model.Dict;
import com.huacainfo.ace.portal.model.DictCategory;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.DictService;
import com.huacainfo.ace.portal.vo.DictVo;

@Service("dictService")
public class DictServiceImpl implements DictService,WebContextDictService{
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	@Autowired
	DictDao dictDao;
	@Autowired
	private DictMapper dictMapper;

	@Autowired
	private DictCategoryMapper dictCategoryMapper;

	@Autowired
	private DataBaseLogService dataBaseLogService;

	public PageResult<DictVo> findDictList(Dict condition, int start, int limit,
			String orderBy) throws Exception {
		PageResult<DictVo> rst = new PageResult<DictVo>();

		List<DictVo> list = this.dictMapper.findList(condition, start, start
				+ limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.dictMapper.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}

	public MessageResponse insertDict(Dict o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getName())) {

			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getPcode())) {

			o.setPcode("0");
		}

		int temp = this.dictMapper.isExitByNameAndCategoryId(
				o.getName(), o.getCategoryId());
		if (temp > 0) {
			return new MessageResponse(1, "名称已存在！");
		}
		this.dictMapper.insert(o);
		this.dataBaseLogService.log("添加字典", "字典", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加字典完成！");
	}

	public MessageResponse updateDict(Dict o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getCode())) {

			return new MessageResponse(1, "编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategoryId())) {

			return new MessageResponse(1, "类型不能为空！");
		}
		if (CommonUtils.isBlank(o.getName())) {

			return new MessageResponse(1, "名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getPcode())) {
			o.setPcode("0");
		}
		this.dictMapper.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更字典", "字典", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "字典变更完成！");
	}

	public SingleResult<Dict> selectDictByPrimaryKey(int id) throws Exception {
		SingleResult<Dict> rst = new SingleResult<Dict>();
		rst.setValue(this.dictMapper.selectByPrimaryKey(id));
		return rst;
	}

	public MessageResponse deleteDictByDictId(int id, UserProp userProp)
			throws Exception {
		MessageResponse rst = new MessageResponse();
		this.dictMapper.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除字典", "字典", String.valueOf(id),
				String.valueOf(id), "字典", userProp);
		return rst;
	}
	private String getContentByTemplate(String expression, String regex,
			Map<String, Object> valueMap) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(expression);
		String paramKey;
		while (m.find()) {
			paramKey = m.group(0);
			paramKey = paramKey.substring(2, paramKey.length() - 1);
			Object paramValue = valueMap.get(paramKey);
			expression = m.replaceFirst(String.valueOf(paramValue));
			m = p.matcher(expression);
		}
		return expression;
	}
	public List<Dict> findListByCategoryId(String categoryId,String selected,Map<String,Object> params) throws Exception {
		DictCategory dictCategory = dictCategoryMapper
				.selectByPrimaryKey(categoryId);
		List<Dict> list = new ArrayList<Dict>();
		;
		if (CommonUtils.isNotEmpty(dictCategory)&&CommonUtils.isNotEmpty(dictCategory.getRemark())) {
			SqlRunner sqlRunner = this.getSqlRunner();
			try {
				String sql=this.getContentByTemplate(dictCategory.getRemark(),"\\$\\{[^\\}]+\\}", params);
				List<Map<String,Object>> items=sqlRunner.selectAll(sql);
				for(Map<String,Object> p:items){
					Dict o=new Dict();
					o.setCategoryId(categoryId);
					o.setCode((String)p.get("CODE"));
					o.setName((String)p.get("NAME"));
					list.add(o);
				}
			} catch (Exception e) {
				this.logger.error("系统错误：", e);
			} finally {
				if (sqlRunner != null) {
					sqlRunner.closeConnection();
				}
			}

		} else {
			if(categoryId.equals("year")){
				Calendar a=Calendar.getInstance();
				int year=a.get(Calendar.YEAR);
				for(int i=year;i>=year-5;i--){
					Dict o=new Dict();
					o.setCode(String.valueOf(i));
					o.setName(String.valueOf(i));
					list.add(o);
				}
				
			}else if(categoryId.equals("month")){
				for(int i=1;i<=12;i++){
					Dict o=new Dict();
					o.setCode(CommonUtils.leftPad(String.valueOf(i), 2));
					o.setName(String.valueOf(i)+"月");
					list.add(o);
				}
			}else if(categoryId.equals("week")){
				for(int i=1;i<=53;i++){
					Dict o=new Dict();
					o.setCode(String.valueOf(i));
					o.setName(String.valueOf(i)+"周");
					list.add(o);
				}
			}else{
				list = this.dictMapper.findListByCategoryId(categoryId);
			}
			
		}
		if(CommonUtils.isBlank(selected)){
			for (Dict dict : list) {
				dict.setSelected(true);
				break;
			}
		}else{
			Dict e=new Dict();
			e.setCode("");
			e.setName("--请选择--");
			e.setSelected(true);
			list.add(0, e);
		}
		return list;
	}

	private Map<String, List<Map<String,Object>>> loadAutoLoadDicts(String syid) {
		Map<String, List<Map<String,Object>>> rst = new HashMap<String, List<Map<String,Object>>>();
		List<Map<String,String>> dict = this.dictDao.selectAllDictList( syid);
		SqlRunner sqlRunner = this.getSqlRunner();
		String category = "";
		String remark = "";
		String sql = "";
		try {
			if (dict != null && dict.size() > 0) {
				for (Map<String, String> row : dict) {
					category = row.get("CATEGORY_ID".toLowerCase());
					remark = row.get("REMARK".toLowerCase());
					if (CommonUtils.isBlank(remark)) {
						sql = "select * from dict where category_id= '"
								+ category + "'";
					} else {
						sql = remark;
					}

					if (!CommonUtils.isBlank(sql)) {
						Map<String,Object> params=new HashMap<String,Object>();
						params.put("gradeId", "1,2,3");
						
						List<Map<String,Object>> value = null;
						sql=this.getContentByTemplate(sql,"\\$\\{[^\\}]+\\}", params);
						this.logger.info(sql);
						try {
							value = sqlRunner.selectAll(sql);
						} catch (SQLException e) {

							e.printStackTrace();
						}
						rst.put(category, value);
						this.logger.info("dict loading " + category + " ["
								+ row.get("name") + "]");
					}
				}
			}
		} finally {
			if (sqlRunner != null) {
				sqlRunner.closeConnection();
			}
		}
		List<Map<String,Object>> years =new ArrayList<Map<String,Object>>();
		Calendar a=Calendar.getInstance();
		int year=a.get(Calendar.YEAR);
		for(int i=year;i>=year-5;i--){
			Map<String,Object> o=new HashMap<String,Object>();
			o.put("CODE",String.valueOf(i));
			o.put("NAME",String.valueOf(i));
			years.add(o);
		}
		rst.put("year",years);
		List<Map<String,Object>> months =new ArrayList<Map<String,Object>>();
		for(int i=1;i<=12;i++){
			Map<String,Object> o=new HashMap<String,Object>();
			o.put("CODE",String.valueOf(i));
			o.put("NAME",String.valueOf(i));
			months.add(o);
		}
		rst.put("month",months);
		List<Map<String,Object>> week =new ArrayList<Map<String,Object>>();
		for(int i=1;i<=53;i++){
			Map<String,Object> o=new HashMap<String,Object>();
			o.put("CODE",String.valueOf(i));
			o.put("NAME",String.valueOf(i)+"周");
			week.add(o);
		}
		rst.put("week",week);
		return rst;
	}
	public  List<Tree>  selectDictTreeList(String pid) throws Exception{
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				this.dictMapper.selectDictTreeList(pid));
		return commonTreeUtils.getTreeList(pid);
	}

	private SqlRunner getSqlRunner() {
		SqlRunner sqlRunner = null;
		Connection conn = null;
		try {
			conn = sqlSessionFactory.getConfiguration().getEnvironment()
					.getDataSource().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlRunner = new SqlRunner(conn);
		return sqlRunner;
	}

	
	
	public Map<String, List<Map<String,Object>>> flushJavaScriptFile(String syid) {
		Map<String, List<Map<String,Object>>> dictMap = this.loadAutoLoadDicts( syid);
		return dictMap;
	}
	
	public List<Map<String,String>> selectSyidBydc(){
		return this.dictDao.selectSyidBydc();
	}
}
