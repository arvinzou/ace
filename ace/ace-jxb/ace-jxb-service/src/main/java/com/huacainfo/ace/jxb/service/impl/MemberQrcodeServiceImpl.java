package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.exception.CustomException;
import com.huacainfo.ace.common.fastdfs.IFile;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.ApiResult;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.QrcodeApi;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.canvas.CanvasKit;
import com.huacainfo.ace.common.tools.canvas.DrawItem;
import com.huacainfo.ace.common.tools.canvas.ImageKit;
import com.huacainfo.ace.jxb.dao.MemberQrcodeDao;
import com.huacainfo.ace.jxb.model.MemberQrcode;
import com.huacainfo.ace.jxb.service.MemberQrcodeService;
import com.huacainfo.ace.jxb.service.StudioService;
import com.huacainfo.ace.jxb.vo.MemberQrcodeQVo;
import com.huacainfo.ace.jxb.vo.MemberQrcodeVo;
import com.huacainfo.ace.jxb.vo.StudioVo;
import com.huacainfo.ace.portal.model.CanvasTmpl;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.service.CanvasTmplService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.WxCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("memberQrcodeService")
/**
 * @author: Arvin
 * @version: 2018-07-23
 * @Description: TODO(会员二维码表)
 */
public class MemberQrcodeServiceImpl implements MemberQrcodeService {
    private static final String NORMAL = "0";
    private static final String FORCE_REFRESH = "1";
    private static final String TYPE_TEMPORARY = "0";
    private static final String TYPE_PERMANENT = "1";
    private static final String TEMP_PATH = "/temp/qrcode/jxb/";

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MemberQrcodeDao memberQrcodeDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private StudioService studioService;
    @Autowired
    private WxCfgService wxCfgService;
    @Autowired
    private IFile fileSaver;
    @Autowired
    private CanvasTmplService canvasTmplService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员二维码表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <MemberQrcodeVo>
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public PageResult<MemberQrcodeVo> findMemberQrcodeList(MemberQrcodeQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<MemberQrcodeVo> rst = new PageResult<>();
        List<MemberQrcodeVo> list = this.memberQrcodeDao.findList(condition,
                start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.memberQrcodeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMemberQrcode
     * @Description: TODO(添加会员二维码表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse insertMemberQrcode(MemberQrcode o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getStudioId())) {
            return new MessageResponse(1, "用户id不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcodeType())) {
            return new MessageResponse(1, "二维码类型（0-临时二维码 1-永久二维码）不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcodeUrl())) {
            return new MessageResponse(1, "二维码地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.memberQrcodeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "会员二维码表名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
//        o.setCreateUserName(userProp.getName());
//        o.setCreateUserId(userProp.getStudioId());
        this.memberQrcodeDao.insertSelective(o);
        this.dataBaseLogService.log("添加会员二维码表", "会员二维码表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加会员二维码表完成！");
    }

    /**
     * @throws
     * @Title:updateMemberQrcode
     * @Description: TODO(更新会员二维码表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse updateMemberQrcode(MemberQrcode o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getStudioId())) {
            return new MessageResponse(1, "用户id不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcodeType())) {
            return new MessageResponse(1, "二维码类型（0-临时二维码 1-永久二维码）不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcodeUrl())) {
            return new MessageResponse(1, "二维码地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


//        o.setLastModifyDate(new Date());
//        o.setLastModifyUserName(userProp.getName());
//        o.setLastModifyUserId(userProp.getStudioId());
        this.memberQrcodeDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更会员二维码表", "会员二维码表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更会员二维码表完成！");
    }

    /**
     * @throws
     * @Title:selectMemberQrcodeByPrimaryKey
     * @Description: TODO(获取会员二维码表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MemberQrcode>
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public SingleResult<MemberQrcodeVo> selectMemberQrcodeByPrimaryKey(String id) throws Exception {
        SingleResult
                <MemberQrcodeVo> rst = new SingleResult<>();
        rst.setValue(this.memberQrcodeDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteMemberQrcodeByMemberQrcodeId
     * @Description: TODO(删除会员二维码表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse deleteMemberQrcodeByMemberQrcodeId(String id, UserProp userProp) throws
            Exception {
        this.memberQrcodeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除会员二维码表", "会员二维码表",
                String.valueOf(id),
                String.valueOf(id), "会员二维码表", userProp);
        return new MessageResponse(0, "会员二维码表删除完成！");
    }

    /**
     * 获取工作室推广二维码
     *
     * @param studioId 咨询师ID
     * @param type     二维码时效类型 0-临时 1-永久
     * @param refresh  强制刷新条件 0-正常获取 1 - 强制刷新
     * @return ResultResponse
     */
    @Override
    public ResultResponse getQRCode(String studioId, String type, String refresh) throws Exception {
        //核验工作室资料
        StudioVo studio = studioService.selectStudioByPrimaryKey(studioId).getValue();
        if (null == studio || !"1".equals(studio.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "非法工作室");
        }
        //获取历史二维码资料
        MemberQrcode qrcode = memberQrcodeDao.findByStudioId(studioId);
        if (null != qrcode) {
            if (FORCE_REFRESH.equals(refresh)
                    || StringUtil.isEmpty(qrcode.getQrcodeUrl())) {
                //生成新微信二维码
                MemberQrcode newQRCode = getQRCodeURL(studio, qrcode);
                qrcode.setQrcodeType(newQRCode.getQrcodeType());
                qrcode.setQrcodeUrl(newQRCode.getQrcodeUrl());
                qrcode.setExpireDate(newQRCode.getExpireDate());
                qrcode.setUpdateDate(DateUtil.getNowDate());
                memberQrcodeDao.updateByPrimaryKeySelective(qrcode);
            }
        } else {
            qrcode = new MemberQrcode();
            qrcode.setId(GUIDUtil.getGUID());
            qrcode.setStudioId(studioId);
            qrcode.setSceneStr(studioId);
            qrcode.setQrcodeType(type);
            qrcode.setStatus("1");
            qrcode.setCreateDate(DateUtil.getNowDate());
            qrcode.setQrcodeType(type);
            //生成新微信二维码
            MemberQrcode newQRCode = getQRCodeURL(studio, qrcode);
            qrcode.setQrcodeUrl(newQRCode.getQrcodeUrl());
            qrcode.setExpireDate(newQRCode.getExpireDate());
            memberQrcodeDao.insertSelective(qrcode);
        }

        return new ResultResponse(ResultCode.SUCCESS, "获取成功", qrcode);
    }

    private MemberQrcode getQRCodeURL(StudioVo studioVo, MemberQrcode orgCfg) throws Exception {
        String qrCodeType = orgCfg.getQrcodeType();
        WxCfg wxCfg = wxCfgService.findBySysId("jxb");//testWxCfg()
        if (null == wxCfg || StringUtil.isEmpty(wxCfg.getAccessToken())) {
            throw new CustomException("微信配置获取失败");
        }
        //二维码网络地址
        String codeUri = getWxQRCodeUri(orgCfg.getSceneStr(), qrCodeType, wxCfg.getAccessToken());
        if (codeUri.startsWith("http") || codeUri.startsWith("https")) {
            MemberQrcode newCode = new MemberQrcode();
            newCode.setQrcodeUrl(codeUri);
            newCode.setQrcodeType(qrCodeType);
            newCode.setRemark("纯微信二维码图");
            if (TYPE_TEMPORARY.equals(qrCodeType)) {
                newCode.setExpireDate(DateUtil.getNowAfterSecTime(QrcodeApi.EXPIRE_SECONDS_30_DAY));
            } else {
                newCode.setExpireDate("2099-12-31 23:59:59");
            }
            return newCode;
        } else {
            throw new CustomException(codeUri);
        }
    }

    /**
     * 获取工作室推广二维码 -- 绘制方式
     *
     * @param studioId 工作室ID
     * @param type     二维码时效类型 0-临时 1-永久
     * @param refresh  强制刷新条件 0-正常获取 1 - 强制刷新
     * @return ResultResponse
     */
    @Override
    public ResultResponse drawQRCode(String studioId, String type, String refresh) throws Exception {
        //核验工作室资料
        StudioVo studio = studioService.selectStudioByPrimaryKey(studioId).getValue();
        if (null == studio || !"1".equals(studio.getStatus())) {
            return new ResultResponse(ResultCode.FAIL, "非法工作室");
        }
        //获取历史二维码资料
        MemberQrcode qrcode = memberQrcodeDao.findByStudioId(studioId);
        if (null != qrcode) {
            if (FORCE_REFRESH.equals(refresh)
                    || StringUtil.isEmpty(qrcode.getQrcodeUrl())) {
//                    || 1 != 1  //二维码即将超时s
                //生成新微信二维码
                MemberQrcode newQRCode = drawQRCodeURL(studio, qrcode);
                qrcode.setQrcodeType(newQRCode.getQrcodeType());
                qrcode.setQrcodeUrl(newQRCode.getQrcodeUrl());
                qrcode.setExpireDate(newQRCode.getExpireDate());
                qrcode.setUpdateDate(DateUtil.getNowDate());
                memberQrcodeDao.updateByPrimaryKeySelective(qrcode);
            }
        } else {
            qrcode = new MemberQrcode();
            qrcode.setId(GUIDUtil.getGUID());
            qrcode.setStudioId(studioId);
            qrcode.setSceneStr(studioId);
            qrcode.setQrcodeType(type);
            qrcode.setStatus("1");
            qrcode.setCreateDate(DateUtil.getNowDate());
            qrcode.setQrcodeType(type);
            //生成新微信二维码
            MemberQrcode newQRCode = drawQRCodeURL(studio, qrcode);
            qrcode.setQrcodeUrl(newQRCode.getQrcodeUrl());
            qrcode.setExpireDate(newQRCode.getExpireDate());
            memberQrcodeDao.insertSelective(qrcode);
        }

        return new ResultResponse(ResultCode.SUCCESS, "获取成功", qrcode);
    }

    /***
     * 获取推广二维码 -- 微信接口产生二维码
     * @param studioVo  工作室资料
     * @param orgCfg 源二维码配置
     * @return MemberQrcode
     */
    private MemberQrcode drawQRCodeURL(StudioVo studioVo, MemberQrcode orgCfg) throws Exception {
        String qrCodeType = orgCfg.getQrcodeType();
        WxCfg wxCfg = wxCfgService.findBySysId("jxb");//testWxCfg();//
        if (null == wxCfg || StringUtil.isEmpty(wxCfg.getAccessToken())) {
            throw new CustomException("微信配置获取失败");
        }
        //二维码网络地址
        String codeUri = synthesisCodeUri(studioVo.getName(), orgCfg.getSceneStr(), qrCodeType, wxCfg.getAccessToken());
        //getWxQRCodeUri(orgCfg.getSceneStr(), qrCodeType, wxCfg.getAccessToken());//纯二维码图
        if (codeUri.startsWith("http") || codeUri.startsWith("https")) {
            MemberQrcode newCode = new MemberQrcode();
            newCode.setQrcodeUrl(codeUri);
            newCode.setQrcodeType(qrCodeType);
            if (TYPE_TEMPORARY.equals(qrCodeType)) {
                newCode.setExpireDate(DateUtil.getNowAfterSecTime(QrcodeApi.EXPIRE_SECONDS_30_DAY));
            } else {
                newCode.setExpireDate("2099-12-31 23:59:59");
            }
            return newCode;
        } else {
            throw new CustomException(codeUri);
        }
    }

    private WxCfg testWxCfg() {
        WxCfg c = new WxCfg();
        c.setAccessToken("13_MkRRvNI8ZQwKNqxh1AeSZfDfIj8-EU2qXhHciTE67oWRlNP20ZGHa-g-LBVahShoCCLrWyOFp-O0I_tFnpoc69YNni782OVzirkQcn79FEesYJ1iUkna1k2k27QGPo4pKYyQWP63C-iaBt3SKHMcAHAOLQ");

        return c;
    }

    /**
     * 按照绘制模板，合成二维码宣传海报
     *
     * @param studioName  工作室名称
     * @param sceneStr    场景值支付穿
     * @param qrCodeType  获取微信二位类型
     * @param accessToken accessToken
     * @return uri
     */
    private String synthesisCodeUri(String studioName, String sceneStr, String qrCodeType, String accessToken) throws Exception {
        //获取绘制模板
        List<CanvasTmpl> tmplList = canvasTmplService.findBySysId("jxb");
        if (CollectionUtils.isEmpty(tmplList)) {
            return "未配置宣传海报模板";
        }
        //获取绘制项目
        CanvasTmpl tmpl = tmplList.get(0);
        Map<String, DrawItem> itemMap = canvasTmplService.getDrawItem(tmpl.getId());
        if (null == itemMap) {
            return "未配置宣传海报模板子项";
        }
        if (!itemMap.containsKey("QRCode") || !itemMap.containsKey("StudioName")) {
            return "绘制内容缺失";
        }
        //获取绘制填充内容 StudioName        QRCode
        //提取微信二维码
        String wxQRCodeUri = getWxQRCodeUri(sceneStr, qrCodeType, accessToken);
        if (null == wxQRCodeUri) {
            return "微信二维码获取失败";
        }
        if (!wxQRCodeUri.startsWith("http") && !wxQRCodeUri.startsWith("https")) {
            return wxQRCodeUri;
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("StudioName", studioName);
        dataMap.put("QRCode", ImageKit.getImageURL(wxQRCodeUri));

        File tmp = new File(TEMP_PATH);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        //tempPath+sceneStr+".jpeg"
        boolean canvasRs = CanvasKit.drawImage(tmpl.getBaseImageUrl(), TEMP_PATH, sceneStr, itemMap, dataMap);
        if (canvasRs) {
            String fileName = sceneStr + ".png";
            File codeFile = new File(TEMP_PATH + fileName);
            String codeUri = PropertyUtil.getProperty("fastdfs_server") + fileSaver.saveFile(codeFile, fileName);
            if (codeFile.exists()) {
                codeFile.delete();
            }

            return codeUri;
        }

        return "绘制二维码海报失败";
    }


    /**
     * 流地址，转存到目标文件路径
     *
     * @param uri         图片流网络地址
     * @param outFilePath 转存物理路径
     */
    private void saveToDisk(String uri, String outFilePath) {
        try {
            InputStream inputStream = HttpKit.getUrlInputStream(uri);
            byte[] data = new byte[1024];
            int len;
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(outFilePath);
                while ((len = inputStream.read(data)) != -1) {
                    fileOutputStream.write(data, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        logger.error("MemberQrcodeService.saveToDisk.error:{}", e);
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e) {
                        logger.error("MemberQrcodeService.saveToDisk.error:{}", e);
                    }
                }
            }
        } catch (IOException e) {
            logger.error("MemberQrcodeService.saveToDisk.error:{}", e);
        }
    }

    /**
     * 微信接口创建临时二维码 -- 默认30天以后失效
     *
     * @param sceneStr    场景值字符串 --userId
     * @param accessToken 微信accessToken
     * @return String 微信提供的二维码下载地址
     */
    private String temporary(String sceneStr, String accessToken) {
        ApiResult as = QrcodeApi.createTemporary(QrcodeApi.EXPIRE_SECONDS_30_DAY, sceneStr, accessToken);
        return getQRCodeUrl(as.getStr("ticket"));
    }

    /**
     * 微信接口创建永久二维码
     *
     * @param sceneStr    场景值字符串 --userId
     * @param accessToken 微信accessToken
     * @return String 微信提供的二维码下载地址
     */
    private String permanent(String sceneStr, String accessToken) {
        ApiResult as = QrcodeApi.createPermanent(sceneStr, "");
        return getQRCodeUrl(as.getStr("ticket"));
    }

    /**
     * 获取微信提供的二维码下载地址
     *
     * @param ticket 下载票据
     * @return String 下载地址
     */
    private String getQRCodeUrl(String ticket) {
        try {
            ticket = URLEncoder.encode(ticket, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return QrcodeApi.getShowQrcodeUrl(ticket);
    }

    /**
     * 提取微信二维码
     *
     * @param sceneStr    场景值支付穿
     * @param qrCodeType  获取微信二位类型
     * @param accessToken accessToken
     * @return String uri
     */
    private String getWxQRCodeUri(String sceneStr, String qrCodeType, String accessToken) throws Exception {
        String tempPath = TEMP_PATH;
        File tmp = new File(tempPath);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        String getUri;//微信二维码提取地址
        String codeUri;//最终存储二维码网络地址
        String fileName = sceneStr + ".png";
        //临时二维码
        if (TYPE_TEMPORARY.equals(qrCodeType)) {
            getUri = temporary(sceneStr, accessToken);
        }
        //永久二维码
        else if (TYPE_PERMANENT.equals(qrCodeType)) {
            getUri = permanent(sceneStr, accessToken);
        } else {
            return "未知二维码类型";
        }
        //转存至本地，临时存放
        saveToDisk(getUri, tempPath + fileName);//
        //上传至服务器,获取服务器地址
        File codeFile = new File(tempPath + fileName);
        if (!codeFile.exists()) {
            return "转存微信二维码失败";
        }
        codeUri = PropertyUtil.getProperty("fastdfs_server") + fileSaver.saveFile(codeFile, fileName);
        codeFile.delete();
        return codeUri;
    }

    /**
     * 提取微信二维码 文件流
     *
     * @param sceneStr    场景值支付穿
     * @param qrCodeType  获取微信二位类型
     * @param accessToken accessToken
     * @return String uri
     */
    private InputStream getWxQRCodeIS(String sceneStr, String qrCodeType, String accessToken) throws Exception {
        String getUri;//微信二维码提取地址
        //临时二维码
        if (TYPE_TEMPORARY.equals(qrCodeType)) {
            getUri = temporary(sceneStr, accessToken);
        }
        //永久二维码
        else if (TYPE_PERMANENT.equals(qrCodeType)) {
            getUri = permanent(sceneStr, accessToken);
        } else {
            return null;
        }

        return HttpKit.getUrlInputStream(getUri);
    }
}
