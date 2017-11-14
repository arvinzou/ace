package com.huacainfo.ace.uf.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import com.alibaba.dubbo.common.json.JSON;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.portal.model.TaskCmcc;
import com.huacainfo.ace.portal.service.GroupService;
import com.huacainfo.ace.portal.service.TaskCmccService;
import com.huacainfo.ace.portal.vo.TaskCmccQVo;
import com.huacainfo.ace.portal.vo.TaskCmccVo;
import com.huacainfo.ace.uf.model.ActivityComment;
import com.huacainfo.ace.uf.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.uf.model.Feedback;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import javax.servlet.http.HttpServletResponse;
@Controller
@RequestMapping("/www/")
public class WWWController extends UfBaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrganizationService organizationService;

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private PersonageService personageService;


	@Autowired
	private AnalysisService analysisService;

	@Autowired
	private DeptService deptService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityUserService activityUserService;

	@Autowired
	private ActivityCommentService activityCommentService;

	@Autowired
	private RedisOperations<String, Object> redisTemplate;

	private String[] areas;

	@Autowired
	private TaskCmccService taskCmccService;

	@Autowired
	private GroupService groupService;

	@RequestMapping(value = "/findTaskCmccList.do")
	@ResponseBody
	public PageResult<TaskCmccVo> findTaskCmccList(TaskCmccQVo condition, PageParam page) throws Exception{
		PageResult<TaskCmccVo> rst = this.taskCmccService.findTaskCmccList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	@RequestMapping(value = "/insertTaskCmcc.do")
	@ResponseBody
	public  MessageResponse insertTaskCmcc(TaskCmcc o,String captcha) throws Exception{
		o.setCreateUserId(this.getCurWxUser().getUnionId());
		o.setMsg(o.getMsg()+"【武陵区委统战部】");
		this.logger.info("{}",o);
		String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
		String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session+"j_captcha_weui");
		this.logger.info("captcha->{}",captcha);
		this.logger.info("j_captcha_weui->{}",j_captcha_weui);
		if(CommonUtils.isBlank(captcha)){
			return new MessageResponse(1,"验证码不能为空！");
		}
		if(!captcha.equals(j_captcha_weui)){
			return new MessageResponse(1,"验证码错误！");
		}
		return this.taskCmccService.insertTaskCmcc(o);
	}
	@RequestMapping(value = "/selectTaskCmccById.do")
	@ResponseBody
	public SingleResult<TaskCmcc> selectTaskCmccById(String id) throws Exception{
		return taskCmccService.selectBYId(id);
	}
	/*统战服务*/
	@RequestMapping(value = "/selectOrganizationList.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationList(String q) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationList(q,this.getCurWxUser());
	}
	@RequestMapping(value = "/selectOrganizationListMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationListMap(String longitude,String latitude) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationListMap(this.getCurWxUser(),longitude,latitude);
	}
	@RequestMapping(value = "/selectOrganizationCategoryList.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationCategoryList() throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationCategoryList(this.getCurWxUser());
	}
	@RequestMapping(value = "/selectOrganization.do")
	@ResponseBody
	public Map<String,Object> selectOrganization(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganization(id,this.getCurWxUser());
	}
	@RequestMapping(value = "/selectOrganizationByCategory.do")
	@ResponseBody
	public List<Map<String,Object>> selectOrganizationByCategory(String category) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.organizationService.selectOrganizationByCategory(category,this.getCurWxUser());
	}
	@RequestMapping(value = "/selectAreaCodeList.do")
	@ResponseBody
	public List<Map<String,Object>> selectAreaCodeList(String areaCode) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		List<Map<String,Object>> list=this.organizationService.selectAreaCodeList(areaCode,this.getCurWxUser());
				areas=new String[list.size()];
		int i=0;
		for(Map<String,Object> e:list){
			areas[i]=(String) e.get("id");
			i++;
		}
		return list;
	}
	/*建言献策*/
	@RequestMapping(value = "/insertFeedback.do")
	@ResponseBody
	public MessageResponse insertFeedback(Feedback obj,String captcha) throws Exception {
		this.logger.info("{}",obj);
		String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
		String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session+"j_captcha_weui");
		this.logger.info("captcha->{}",captcha);
		this.logger.info("j_captcha_weui->{}",j_captcha_weui);
		//obj.setAreaCode(areas[Integer.valueOf(obj.getAreaCode())]);
		obj.setAreaCode(null);
		if(CommonUtils.isBlank(captcha)){
			return new MessageResponse(1,"验证码不能为空！");
		}
		if(!captcha.equals(j_captcha_weui)){
			return new MessageResponse(1,"验证码错误！");
		}
		return this.feedbackService
				.insertFeedback(obj, this.getCurUserProp());
	}
	@RequestMapping(value = "/query.do")
	@ResponseBody
	public ListResult<Map<String,Object>> query(
			String reportId, int start, int limit)
			throws Exception {
		Map<String,Object> condition=this.getParams();
		if (CommonUtils.isBlank(start)) {
			start = 0;
		}
		if (CommonUtils.isBlank(limit)) {
			limit = 10;
		}
		return analysisService.query(condition, reportId, start, limit);
	}

	/*统战机构*/
	@RequestMapping(value = "/selectDeptList.do")
	@ResponseBody
	public List<Map<String,Object>> selectDeptList(String q,String areaCode) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.deptService.selectDeptList(q,this.getCurWxUser(),areaCode);
	}
	@RequestMapping(value = "/selectDeptListMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectDeptListMap(String longitude,String latitude,String q) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.deptService.selectDeptListMap(this.getCurWxUser(),longitude,latitude,q);
	}
	@RequestMapping(value = "/selectDept.do")
	@ResponseBody
	public Map<String,Object> selectDept(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.deptService.selectDept(id,this.getCurWxUser());
	}

	/*统战人士*/
	@RequestMapping(value = "/selectPersonageListMap.do")
	@ResponseBody
	public List<Map<String,Object>> selectPersonageListMap(String longitude,String latitude,String q) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.personageService.selectPersonageListMap(this.getCurWxUser(),longitude,latitude,q);
	}
	@RequestMapping(value = "/selectPersonageList.do")
	@ResponseBody
	public List<Map<String,Object>> selectPersonageList(String q) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.personageService.selectPersonageList(q,this.getCurWxUser());
	}
	@RequestMapping(value = "/selectPersonage.do")
	@ResponseBody
	public Map<String,Object> selectPersonage(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.personageService.selectPersonageById(id);
	}
	@RequestMapping(value = "/search.do")
	public void search(HttpServletResponse response) throws Exception{
		String body=HttpUtils.httpsGet("https://api.map.baidu.com/place/v2/search?"+this.getUrlParamsByMap(this.getParams()));
		response.getOutputStream().write(body.getBytes());
	}
	@RequestMapping(value = "/selectActivityPageList.do")
	@ResponseBody
	public List<Map<String,Object>> selectActivityPageList(){
		return this.activityService.selectActivityPageList(this.getParams());
	}
	@RequestMapping(value = "/selectPhotoListById.do")
	@ResponseBody
	public List<Map<String,Object>> selectPhotoListById(String id){
		return this.activityService.selectPhotoListById(id);
	}
	@RequestMapping(value = "/selectActivityById.do")
	@ResponseBody
	public Map<String,Object> selectActivityById(String id){
		return this.activityService.selectActivityById(id);
	}

	@RequestMapping(value = "/selectUserListByActivityId.do")
	@ResponseBody
	public List<Map<String,Object>> selectUserListByActivityId(String id) throws Exception {
		return this.activityUserService.selectListByActivityId(id);
	}

	@RequestMapping(value = "/selectCommentListByActivityId.do")
	@ResponseBody
	public List<Map<String,Object>> selectCommentListByActivityId(String id) throws Exception {
		return this.activityCommentService.selectListByActivityId(id);
	}

	@RequestMapping(value = "/insertActivityComment.do")
	@ResponseBody
	public MessageResponse insertActivityComment(ActivityComment obj,String captcha) throws Exception {
		this.logger.info("{}",obj);
		String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
		String j_captcha_weui=(String) this.redisTemplate.opsForValue().get(_3rd_session+"j_captcha_weui");
		this.logger.info("captcha->{}",captcha);
		this.logger.info("j_captcha_weui->{}",j_captcha_weui);
		if(CommonUtils.isBlank(captcha)){
			return new MessageResponse(1,"验证码不能为空！");
		}
		if(!captcha.equals(j_captcha_weui)){
			return new MessageResponse(1,"验证码错误！");
		}
		return this.activityCommentService.insertActivityComment(obj, this.getCurWxUser());
	}
	@RequestMapping(value = "/updateActivity.do")
	@ResponseBody
	public MessageResponse updateActivity(String id,String type) throws Exception {
		return this.activityService.updateActivity(id,type,this.getCurWxUser());
	}

	public  String getUrlParamsByMap(Map<String, Object> map) {
		if (map == null) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			sb.append(entry.getKey() + "=" + entry.getValue());
			sb.append("&");
		}
		String s = sb.toString();
		if (s.endsWith("&")) {
			s = org.apache.commons.lang.StringUtils.substringBeforeLast(s, "&");
		}
		return s;
	}

	@RequestMapping(value = "/getPersonageTreeList.do")
	@ResponseBody
	public List<Tree> getPersonageTreeList(String q)throws Exception {
		List<Tree> list=this.personageService.selectPersonageTreeList(q,this.getCurWxUser());
		return list;
	}


	@RequestMapping(value = "/queryPersonage.do")
	@ResponseBody
	public Map<String, Object> queryPersonage(String q)throws Exception {
		return this.personageService.selectPersonage(q);
	}

	@RequestMapping(value = "/selectFreeGroupTree.do")
	@ResponseBody
	public List<Tree> selectFreeGroupTree() throws Exception {
		return this.groupService.selectFreeGroupTree("uf");
	}

	@RequestMapping(value = "/selectPersonageTree.do")
	@ResponseBody
	public List<Tree> selectPersonageTree(String q)throws Exception {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String,Object> p=this.personageService.selectPersonage(q);
		List<Map<String,Object>> items=(List<Map<String,Object>>)p.get("rows");
		Map<String,Object> o=new HashMap<String,Object>();
		o.put("ID","01");
		o.put("PID","0");
		o.put("TEXT","搜索");
		list.add(o);
		for(Map<String,Object> e:items){
			Map<String,Object> m=new HashMap<String,Object>();
			m.put("ID",e.get("id"));
			m.put("PID","01");
			m.put("TEXT",e.get("name"));
			m.put("HREF",e.get("name"));
			m.put("ICONCLS",e.get("mobile"));
			list.add(m);
		}
		CommonTreeUtils ctu=new CommonTreeUtils(list);
		return ctu.getTreeList("0");
	}
	@RequestMapping(value = "/sendCmccByMobile.do")
	@ResponseBody
	public MessageResponse sendCmccByMobile(String mobile) throws Exception {
		String _3rd_session=this.getRequest().getHeader("WX-SESSION-ID");
		String j_captcha_cmcc=this.getRandCode();
		TaskCmcc o=new TaskCmcc();
		if(CommonUtils.isBlank(mobile)){
			return new MessageResponse(1,"手机号不能为空");
		}
		if(!CommonUtils.isValidMobile(mobile)){
			return new MessageResponse(1,"手机号格式错误");
		}
		int t=this.personageService.isExitPersonageByMobile(mobile);
		if(t==0){
			return new MessageResponse(1,"非统战人士手机号或统战人士手机号信息错误。");
		}
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("taskName", "验证码" + mobile);
		msg.put("msg", "本次提交验证码为" + j_captcha_cmcc + "，请及时输入。【武陵区委统战部】");
		msg.put("tel", mobile + "," + mobile);
		CommonBeanUtils.copyMap2Bean(o,msg);
		redisTemplate.opsForValue().set(_3rd_session+"j_captcha_weui", j_captcha_cmcc);
		return this.taskCmccService.insertTaskCmcc(o);
	}
	private String getRandCode() {
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}
	@RequestMapping(value = "/insertWWWTaskCmcc.do")
	@ResponseBody
	public  MessageResponse insertWWWTaskCmcc(String mobile,String j_captcha_cmcc) throws Exception{
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("taskName", "验证码" + mobile);
		msg.put("msg", "本次投票验证码为" + j_captcha_cmcc + "，请及时输入。【武陵区教育局】");
		msg.put("tel", mobile + "," + mobile);
		TaskCmcc o=new TaskCmcc();
		CommonBeanUtils.copyMap2Bean(o,msg);
		return this.taskCmccService.insertTaskCmcc(o);
	}


	@RequestMapping(value = "/selectPersonageCfgById.do")
	@ResponseBody
	public Map<String,Object> selectPersonageCfgById(String id) throws Exception {
		this.logger.debug("{}",this.getCurWxUser());
		return this.personageService.selectPersonageCfgById(id);
	}

}
