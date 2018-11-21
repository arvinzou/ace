package com.huacainfo.ace.generator.fop;

import com.huacainfo.ace.generator.base.AutoCodeUtils;

public class AutoCode {

    public static void main(String[] args) throws Exception {
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils("fop", "企业/协会活动", "Arvin");
//        Class<?> c = com.huacainfo.ace.fop.model.FopCallRecordDetail.class;
        Class<?> c = null;
        autoCodeUtils.init(c);

//===================================
        autoCodeUtils.createVO(c);
        autoCodeUtils.createJsp(c);
        autoCodeUtils.createQVO(c);
        autoCodeUtils.createDAO(c);
        autoCodeUtils.createService(c);
        autoCodeUtils.createServiceImpl(c);
        autoCodeUtils.createAction(c);
        autoCodeUtils.createJsConfig(c);
        autoCodeUtils.createJsView(c);
        autoCodeUtils.createJsController(c);
        autoCodeUtils.createModel(c);


//===================================
//        autoCodeUtils.createDAO(c);
//        autoCodeUtils.createService(c);
//        autoCodeUtils.createServiceImpl(c);

    }

}
