package com.huacainfo.ace.jxb.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.Member;
import com.huacainfo.ace.jxb.service.MemberService;
import com.huacainfo.ace.jxb.vo.MemberVo;
import com.huacainfo.ace.jxb.vo.MemberQVo;

@Controller
@RequestMapping("/member")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(会员)
 */
public class MemberController extends LiveBaseController {


	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MemberService memberService;
     /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(会员分页查询)
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception
	 		* @return:       PageResult<MemberVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/findMemberList")
	@ResponseBody
	public PageResult<MemberVo> findMemberList(MemberQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<MemberVo> rst = this.memberService
				.findMemberList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}
    /**
	 *
	    * @Title:insertMember
	    * @Description:  TODO(添加会员)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/insertMember")
	@ResponseBody
	public MessageResponse insertMember(String jsons) throws Exception {
		Member obj = JSON.parseObject(jsons, Member.class);
		return this.memberService
				.insertMember(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:updateMember
	    * @Description:  TODO(更新会员)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/updateMember")
	@ResponseBody
	public MessageResponse updateMember(String jsons) throws Exception {
		Member obj = JSON.parseObject(jsons, Member.class);
		return this.memberService
				.updateMember(obj, this.getCurUserProp());
	}
    /**
	 *
	    * @Title:selectMemberByPrimaryKey
	    * @Description:  TODO(获取会员)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Member>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/selectMemberByPrimaryKey")
	@ResponseBody
	public SingleResult<MemberVo> selectMemberByPrimaryKey(String id)
			throws Exception {
		return this.memberService.selectMemberByPrimaryKey(id);
	}
    /**
	 *
	    * @Title:deleteMemberByMemberId
	    * @Description:  TODO(删除会员)
	 		* @param:        @param jsons
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
	@RequestMapping(value = "/deleteMemberByMemberId")
	@ResponseBody
	public MessageResponse deleteMemberByMemberId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.memberService.deleteMemberByMemberId(id,
				this.getCurUserProp());
	}
}
