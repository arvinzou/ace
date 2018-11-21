package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * @Auther: Arvin
 * @Date: 2018/6/21 08:48
 * @Description:
 */
public interface DataSwapperService {

    /**
     * 数据交换统一查询
     *
     * @param keyword 查询关键字
     * @param type    查询类别 0-主体身份代码、1-统一社会信用代码、2-纳税人识别号、3-组织机构代码
     * @return map
     */
    ResultResponse search(String keyword, int type);

    /**
     * 接口api调用
     *
     * @param methodName 方法名称
     * @param keyWord    关键词
     * @return ResultResponse
     */
    ResultResponse invoke(String methodName, String keyWord);
}
