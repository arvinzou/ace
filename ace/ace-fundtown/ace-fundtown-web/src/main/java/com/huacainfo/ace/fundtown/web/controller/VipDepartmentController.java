package com.huacainfo.ace.fundtown.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.fundtown.model.VipDepartment;
import com.huacainfo.ace.fundtown.model.VipMemberRes;
import com.huacainfo.ace.fundtown.service.VipDepartmentService;
import com.huacainfo.ace.fundtown.service.VipMemberResService;
import com.huacainfo.ace.fundtown.vo.VipDepartmentQVo;
import com.huacainfo.ace.fundtown.vo.VipDepartmentVo;
import com.huacainfo.ace.portal.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/vipDepartment")
/**
 * @author: Arvin
 * @version: 2018-07-04
 * @Description: (入驻成员列表)
 */
public class VipDepartmentController extends BaseController {

    @Autowired
    private VipDepartmentService vipDepartmentService;
    @Autowired
    private VipMemberResService vipMemberResService;
    @Autowired
    private IFile fileSaver;
    @Autowired
    private FilesService filesService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(入驻成员列表分页查询)
     * @param: @param condition
     * @param: @param page
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult
     * <VipDepartmentVo>
     * @author: Arvin
     * @version: 2018-07-04
     */
    @RequestMapping(value = "/findVipDepartmentList")
    @ResponseBody
    public PageResult<VipDepartmentVo> findVipDepartmentList(VipDepartmentQVo condition,
                                                             PageParamNoChangeSord page) throws Exception {
        condition.setSyid(getCurUserProp().getActiveSyId());
        PageResult<VipDepartmentVo> rst =
                vipDepartmentService.findDepartmentList(condition, page.getStart(), page.getLimit(), page.getOrderBy());
        if (rst.getTotal() == 0) {
            rst.setTotal(page.getTotalRecord());
        }

        return rst;
    }

    /**
     * @throws
     * @Title:insertVipDepartment
     * @Description: TODO(添加入驻成员列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-04
     */
    @RequestMapping(value = "/insertVipDepartment")
    @ResponseBody
    public MessageResponse insertVipDepartment(String jsons) throws Exception {
        VipDepartment obj = JSON.parseObject(jsons, VipDepartment.class);
        return vipDepartmentService.insertDepartment(obj, getCurUserProp());
    }

    /**
     * @throws
     * @Title:updateVipDepartment
     * @Description: TODO(更新入驻成员列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-04
     */
    @RequestMapping(value = "/updateVipDepartment")
    @ResponseBody
    public MessageResponse updateVipDepartment(String jsons) throws Exception {
        VipDepartment obj = JSON.parseObject(jsons, VipDepartment.class);
        return vipDepartmentService.updateDepartment(obj, getCurUserProp());
    }

    /**
     * @throws
     * @Title:selectVipDepartmentByPrimaryKey
     * @Description: TODO(获取入驻成员列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<VipDepartment>
     * @author: Arvin
     * @version: 2018-07-04
     */
    @RequestMapping(value = "/selectVipDepartmentByPrimaryKey")
    @ResponseBody
    public SingleResult<VipDepartmentVo> selectVipDepartmentByPrimaryKey(String id) throws Exception {
        return vipDepartmentService.selectDepartmentByPrimaryKey(id);
    }

    /**
     * @throws
     * @Title:deleteVipDepartmentByVipDepartmentId
     * @Description: TODO(删除入驻成员列表)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-04
     */
    @RequestMapping(value = "/deleteVipDepartmentByVipDepartmentId")
    @ResponseBody
    public MessageResponse deleteVipDepartmentByVipDepartmentId(String jsons) throws Exception {
        JSONObject json = JSON.parseObject(jsons);
        String id = json.getString("departmentId");
        return vipDepartmentService.delDepartmentByPrimaryKey(id, getCurUserProp());
    }

    /**
     * 企业入驻流程审核
     *
     * @param deptId       企业ID
     * @param nodeId       流程节点ID
     * @param auditResult  审核结果 0-待审核 1-审核通过，2-审核拒绝
     * @param auditOpinion 审核意见
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/audit")
    public MessageResponse audit(String deptId, String nodeId,
                                 String auditResult, String auditOpinion) {
        if (!StringUtil.areNotEmpty(deptId, nodeId)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return vipDepartmentService.audit(deptId, nodeId, auditResult, auditOpinion, getCurUserProp());
    }


    /**
     * 信息公示
     *
     * @param deptId 企业ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/publicity")
    public MessageResponse publicity(String deptId) throws Exception {
        if (StringUtil.isEmpty(deptId)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return vipDepartmentService.publicity(deptId, getCurUserProp());
    }

    @ResponseBody
    @RequestMapping(value = "/findVipResList")
    public ResultResponse findVipResList(String deptId) throws Exception {
        if (StringUtil.isEmpty(deptId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        List<VipMemberRes> list = vipMemberResService.findByDeptId(deptId, new String[]{"0", "1"});
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", list);
    }

    /**
     * @param file
     * @param deptId
     * @param domain
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/uploadVipRes")
    public SingleResult<List<VipMemberRes>> uploadFile(@RequestParam MultipartFile[] file,
                                                       String deptId, String domain) throws Exception {
        String tempDir = getRequest().getSession().getServletContext().getRealPath(File.separator) + "tmp";
        File tmp = new File(tempDir);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }

        String url;
        List<VipMemberRes> resList = new ArrayList<>();
        VipMemberRes res;
        for (MultipartFile o : file) {
            File dest = new File(tempDir + File.separator + o.getName());
            o.transferTo(dest);
            url = domain + this.fileSaver.saveFile(dest,
                    o.getOriginalFilename());
            dest.delete();
            filesService.insertFiles(url, this.getCurUserProp());


            res = vipDepartmentService.insertVipMemberRes(deptId, "1",
                    o.getOriginalFilename(), o.getSize(), url);
            resList.add(res);
        }

        SingleResult<List<VipMemberRes>> rst = new SingleResult<>(0, "上传成功！");
        rst.setValue(resList);
        return rst;
    }


    @ResponseBody
    @RequestMapping(value = "/deleteAttach")
    public MessageResponse deleteAttach(String resId) throws Exception {
        if (StringUtil.isEmpty(resId)) {
            return new MessageResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return vipMemberResService.deleteVipMemberResByVipMemberResId(resId, getCurUserProp());
    }
}
