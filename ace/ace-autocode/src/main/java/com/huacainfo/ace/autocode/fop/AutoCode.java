package com.huacainfo.ace.autocode.fop;

import com.huacainfo.ace.autocode.base.AutoCodeUtils;

public class AutoCode {

    public static void main(String[] args) throws Exception {
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils("fop", "流程记录", "Arvin");
//        Class<?> c = com.huacainfo.ace.fop.model.FopFinanceProject.class;
        Class<?> c = null;//com.huacainfo.ace.fop.model.FopCompany.class;
        autoCodeUtils.init(c);

//===================================
//        autoCodeUtils.createVO(c);
//        autoCodeUtils.createQVO(c);
//        autoCodeUtils.createDAO(c);
//        autoCodeUtils.createService(c);
//        autoCodeUtils.createServiceImpl(c);
//        autoCodeUtils.createAction(c);
//        autoCodeUtils.createJsp(c);
//        autoCodeUtils.createJsConfig(c);
//        autoCodeUtils.createJsView(c);
//        autoCodeUtils.createJsController(c);
//        autoCodeUtils.createModel(c);


//===================================
        autoCodeUtils.createDAO(c);
        autoCodeUtils.createService(c);
        autoCodeUtils.createServiceImpl(c);

    }

}
