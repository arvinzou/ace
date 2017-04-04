package com.huacainfo.ace.kernel.web.controller;

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
import com.huacainfo.ace.kernel.model.ActivityPhotos;
import com.huacainfo.ace.kernel.service.ActivityPhotosService;
import com.huacainfo.ace.kernel.vo.ActivityPhotosVo;
import com.huacainfo.ace.kernel.vo.ActivityPhotosQVo;

@Controller
@RequestMapping("/activityPhotos")
public class ActivityPhotosController extends KernelBaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ActivityPhotosService activityPhotosService;

	@RequestMapping(value = "/findActivityPhotosList.do")
	@ResponseBody
	public PageResult<ActivityPhotosVo> findActivityPhotosList(ActivityPhotosQVo condition,
			PageParamNoChangeSord page) throws Exception {
		PageResult<ActivityPhotosVo> rst = this.activityPhotosService
				.findActivityPhotosList(condition, page.getStart(), page.getLimit(),
						page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
	}

	@RequestMapping(value = "/insertActivityPhotos.do")
	@ResponseBody
	public MessageResponse insertActivityPhotos(String jsons) throws Exception {
		ActivityPhotos obj = JSON.parseObject(jsons, ActivityPhotos.class);
		return this.activityPhotosService
				.insertActivityPhotos(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/updateActivityPhotos.do")
	@ResponseBody
	public MessageResponse updateActivityPhotos(String jsons) throws Exception {
		ActivityPhotos obj = JSON.parseObject(jsons, ActivityPhotos.class);
		return this.activityPhotosService
				.updateActivityPhotos(obj, this.getCurUserProp());
	}

	@RequestMapping(value = "/selectActivityPhotosByPrimaryKey.do")
	@ResponseBody
	public SingleResult<ActivityPhotos> selectActivityPhotosByPrimaryKey(String id)
			throws Exception {
		return this.activityPhotosService.selectActivityPhotosByPrimaryKey(id);
	}

	@RequestMapping(value = "/deleteActivityPhotosByActivityPhotosId.do")
	@ResponseBody
	public MessageResponse deleteActivityPhotosByActivityPhotosId(String jsons)
			throws Exception {
		JSONObject json = JSON.parseObject(jsons);
		String id = json.getString("id");
		return this.activityPhotosService.deleteActivityPhotosByActivityPhotosId(id,
				this.getCurUserProp());
	}
}
