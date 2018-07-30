package com.huacainfo.ace.autocode.jxb;

import com.huacainfo.ace.autocode.base.AutoCodeUtils;

public class AutoCode {

    public static void main(String[] args) throws Exception {
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils("jxb", "咨询师", "Arvin");
//        Class<?> c = com.huacainfo.ace.jxb.model.Counselor.class;
        Class<?> c = null;
        autoCodeUtils.init(c);

//===================================
        autoCodeUtils.createVO(c);
        autoCodeUtils.createQVO(c);
        autoCodeUtils.createDAO(c);
        autoCodeUtils.createService(c);
        autoCodeUtils.createServiceImpl(c);
        autoCodeUtils.createAction(c);
        autoCodeUtils.createJsp(c);
        autoCodeUtils.createJsConfig(c);
        autoCodeUtils.createJsView(c);
        autoCodeUtils.createJsController(c);
        autoCodeUtils.createModel(c);


//===================================
//        autoCodeUtils.createVO(c);
//        autoCodeUtils.createQVO(c);
//        autoCodeUtils.createDAO(c);
//        autoCodeUtils.createService(c);
//        autoCodeUtils.createServiceImpl(c);

    }

}
