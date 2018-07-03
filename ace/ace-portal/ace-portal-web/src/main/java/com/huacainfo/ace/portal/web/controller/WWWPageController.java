package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.model.*;
import com.huacainfo.ace.portal.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/www")
public class WWWPageController extends PortalBaseController {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IFile fileSaver;
	@Autowired
	private FilesService filesService;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RedisOperations<String, Object> redisTemplate;

	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private KafkaProducerService kafkaProducerService;

	@Autowired
	private TplPageService tplPageService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ArticleCategoryService articleCategoryService;

	@Autowired
	private ArticleCmtService articleCmtService;
	/**
	 *
	 * @Title:getTplPageById
	 * @Description:  TODO(根据页面ID获取页面所有数据)
	 * @param:        @param id
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-07
	 */
	@RequestMapping(value = "/getTplPageById.do")
	@ResponseBody
	public  Map<String, java.lang.Object> getTplPageById(String id,String type) throws Exception{
		return tplPageService.getTplPageById(id,type);
	}


	@RequestMapping(value = "/getById.do")
	@ResponseBody
	public  Map<String, java.lang.Object> getById(String id) throws Exception{
		return tplPageService.getById(id);
	}
}
