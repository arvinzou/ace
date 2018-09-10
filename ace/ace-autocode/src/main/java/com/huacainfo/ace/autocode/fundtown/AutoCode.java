package com.huacainfo.ace.generator.fundtown;

import com.huacainfo.ace.generator.base.AutoCodeUtils;

public class AutoCode {

    public static void main(String[] args) throws Exception {
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils("fundtown", "入驻流程节点-资源/附件", "Arvin");
//        Class<?> c = com.huacainfo.ace.fundtown.model.ProcessNodeRes.class;
        Class<?> c = null;
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
        autoCodeUtils.createVO(c);
        autoCodeUtils.createQVO(c);
        autoCodeUtils.createDAO(c);
        autoCodeUtils.createService(c);
        autoCodeUtils.createServiceImpl(c);

    }

}
