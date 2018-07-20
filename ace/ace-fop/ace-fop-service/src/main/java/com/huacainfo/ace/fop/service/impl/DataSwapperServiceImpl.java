package com.huacainfo.ace.fop.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.fop.common.api.DataSwapperApi;
import com.huacainfo.ace.fop.service.DataSwapperService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/6/21 08:52
 * @Description:
 */
@Service("dataSwapperService")
public class DataSwapperServiceImpl implements DataSwapperService {

    /**
     * 数据交换统一查询
     *
     * @param keyword 查询关键字
     * @param type    查询类别 0-主体身份代码、1-统一社会信用代码、2-纳税人识别号、3-组织机构代码
     * @return map
     */
    @Override
    public ResultResponse search(String keyword, int type) {
        Map<String, Object> rtnMap = new HashMap<>();
        //市商务局
        Map<String, List<Map<String, Object>>> sswj = sswj(keyword, type);
        if (!CollectionUtils.isEmpty(sswj)) {
            rtnMap.put("市商务局", sswj);
        }
        //市质监局
        Map<String, List<Map<String, Object>>> szjj = szjj(keyword, type);
        if (!CollectionUtils.isEmpty(szjj)) {
            rtnMap.put("市质监局", szjj);
        }
        //市司法局
        Map<String, List<Map<String, Object>>> ssfj = ssfj(keyword, type);
        if (!CollectionUtils.isEmpty(ssfj)) {
            rtnMap.put("市司法局", ssfj);
        }
        //市国税局
        Map<String, List<Map<String, Object>>> sgsj = sgsj(keyword, type);
        if (!CollectionUtils.isEmpty(sgsj)) {
            rtnMap.put("市国税局", sgsj);
        }
        //市经信委
        Map<String, List<Map<String, Object>>> sjxw = sjxw(keyword, type);
        if (!CollectionUtils.isEmpty(sjxw)) {
            rtnMap.put("市经信委", sjxw);
        }
        //市人社局
        Map<String, List<Map<String, Object>>> srsj = srsj(keyword, type);
        if (!CollectionUtils.isEmpty(srsj)) {
            rtnMap.put("市人社局", srsj);
        }
        //市科技局
        Map<String, List<Map<String, Object>>> skjj = skjj(keyword, type);
        if (!CollectionUtils.isEmpty(skjj)) {
            rtnMap.put("市科技局", skjj);
        }
        //市安监局
        Map<String, List<Map<String, Object>>> sajj = sajj(keyword, type);
        if (!CollectionUtils.isEmpty(sajj)) {
            rtnMap.put("市安监局", sajj);
        }


        return new ResultResponse(ResultCode.SUCCESS, "查询成功", rtnMap);
    }

    /**
     * 市商务局
     *
     * @param keyword
     * @param type
     */
    private Map<String, List<Map<String, Object>>> sswj(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();

        //市商务局_商务领域诚信经营示范企业
        List<Map<String, Object>> swlycxjysfqy = DataSwapperApi.sswj_swlycxjysfqy(keyword);//;"57658102-0");
        if (!CollectionUtils.isEmpty(swlycxjysfqy)) {
            data.put("商务领域诚信经营示范企业(" + swlycxjysfqy.size() + "条)", swlycxjysfqy);
        }
        //市商务局_招商引资投产项目基本情况信息
        List<Map<String, Object>> zsyztcxmjbqkxx = DataSwapperApi.sswj_zsyztcxmjbqkxx(keyword);//"钟祖连");
        if (!CollectionUtils.isEmpty(zsyztcxmjbqkxx)) {
            data.put("招商引资投产项目基本情况信息(" + zsyztcxmjbqkxx.size() + "条)", zsyztcxmjbqkxx);
        }

        return data;
    }


    /**
     * 市质监局
     *
     * @param keyword
     * @param type
     */
    private Map<String, List<Map<String, Object>>> szjj(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();

        //市质监局_名牌产品信息
        List<Map<String, Object>> mpcpxx = DataSwapperApi.szjj_mpcpxx(keyword);//"常德市先玉网具有限责任公司");
        if (!CollectionUtils.isEmpty(mpcpxx)) {
            data.put("名牌产品信息(" + mpcpxx.size() + "条)", mpcpxx);
        }

        return data;
    }

    /**
     * 市司法局
     *
     * @param keyword
     * @param type
     */
    private Map<String, List<Map<String, Object>>> ssfj(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();

        //市司法局_考核合格的律师事务所信息
        List<Map<String, Object>> khhgdlsswsxx = DataSwapperApi.ssfj_khhgdlsswsxx(keyword);//"湖南朝阳律师事务所");
        if (!CollectionUtils.isEmpty(khhgdlsswsxx)) {
            data.put("考核合格的律师事务所信息(" + khhgdlsswsxx.size() + "条)", khhgdlsswsxx);
        }

        return data;
    }


    /**
     * 市国税局
     *
     * @param keyword
     * @param type
     * @return
     */
    private Map<String, List<Map<String, Object>>> sgsj(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();
        //市国税局_税收违法违章公告信息   -- 无数据
        List<Map<String, Object>> sswfwzggxx = DataSwapperApi.sgsj_sswfwzggxx(keyword);//"1233456");
        if (!CollectionUtils.isEmpty(sswfwzggxx)) {
            data.put("税收违法违章公告信息(" + sswfwzggxx.size() + "条)", sswfwzggxx);
        }
        //市国税局_税务登记信息
        List<Map<String, Object>> swdjxx = DataSwapperApi.sgsj_swdjxx(keyword);//"430702198704016527");
        if (!CollectionUtils.isEmpty(swdjxx)) {
            data.put("税务登记信息(" + swdjxx.size() + "条)", swdjxx);
        }
        //市国税局_纳税人信用等级信息
        List<Map<String, Object>> nsrxydjxx = DataSwapperApi.sgsj_nsrxydjxx(keyword);//"430724597570347");
        if (!CollectionUtils.isEmpty(nsrxydjxx)) {
            data.put("纳税人信用等级信息(" + nsrxydjxx.size() + "条)", nsrxydjxx);
        }


        return data;
    }

    /**
     * 市经信委
     *
     * @param keyword
     * @param type
     * @return
     */
    private Map<String, List<Map<String, Object>>> sjxw(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();
        //市经信委_常德市园区攻坚主要经济指标完成情况
        List<Map<String, Object>> cdsyqgjzyjjzbwcqk = DataSwapperApi.sjxw_cdsyqgjzyjjzbwcqk(keyword);//"临澧经济开发区");
        if (!CollectionUtils.isEmpty(cdsyqgjzyjjzbwcqk)) {
            data.put("常德市园区攻坚主要经济指标完成情况(" + cdsyqgjzyjjzbwcqk.size() + "条)", cdsyqgjzyjjzbwcqk);
        }
        return data;
    }

    /**
     * 市人社局
     *
     * @param keyword
     * @param type
     * @return
     */
    private Map<String, List<Map<String, Object>>> srsj(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();
        //市人社局_企业养老保险单位参保信息
        List<Map<String, Object>> qyylbxdwsbxx = DataSwapperApi.srsj_qyylbxdwsbxx(keyword);//"709696");
        if (!CollectionUtils.isEmpty(qyylbxdwsbxx)) {
            data.put("企业养老保险单位参保信息(" + qyylbxdwsbxx.size() + "条)", qyylbxdwsbxx);
        }
        return data;
    }

    /**
     * 市科技局
     *
     * @param keyword
     * @param type
     * @return
     */
    private Map<String, List<Map<String, Object>>> skjj(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();

        //市科技局_有效发明专利信息
        List<Map<String, Object>> yxfmzlxx = DataSwapperApi.skjj_yxfmzlxx(keyword);//"锌抗氧变质剂");
        if (!CollectionUtils.isEmpty(yxfmzlxx)) {
            data.put("有效发明专利信息(" + yxfmzlxx.size() + "条)", yxfmzlxx);
        }
        //市科技局_科技项目申报单位产品基本信息
        List<Map<String, Object>> kjxmsbdwcpjbxx = DataSwapperApi.skjj_kjxmsbdwcpjbxx(keyword);//"72795084-0");
        if (!CollectionUtils.isEmpty(kjxmsbdwcpjbxx)) {
            data.put("科技项目申报单位产品基本信息(" + kjxmsbdwcpjbxx.size() + "条)", kjxmsbdwcpjbxx);
        }
        //市科技局_科技进步奖知识产权证明信息
        List<Map<String, Object>> kjjbjzscqzmxx = DataSwapperApi.skjj_kjjbjzscqzmxx(keyword);//"69624274-4");
        if (!CollectionUtils.isEmpty(kjjbjzscqzmxx)) {
            data.put("科技进步奖知识产权证明信息(" + kjjbjzscqzmxx.size() + "条)", kjjbjzscqzmxx);
        }
        return data;
    }

    /**
     * 市安监局
     *
     * @param keyword
     * @param type
     * @return
     */
    private Map<String, List<Map<String, Object>>> sajj(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();

        //        市安监局_行政处罚信息（单位）
        List<Map<String, Object>> xzcfxx_dw = DataSwapperApi.sajj_xzcfxx_dw(keyword);//"430724000002562");
        if (!CollectionUtils.isEmpty(xzcfxx_dw)) {
            data.put("行政处罚信息（单位）(" + xzcfxx_dw.size() + "条)", xzcfxx_dw);
        }
        return data;
    }


    private Map<String, List<Map<String, Object>>> a(String keyword, int type) {
        Map<String, List<Map<String, Object>>> data = new HashMap<>();

//        if (!CollectionUtils.isEmpty()) {
//            data.put("", );
//        }
        return data;
    }
}

