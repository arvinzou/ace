package com.huacainfo.ace.generator.portal;

import com.huacainfo.ace.generator.base.AutoCodeUtils;

public class AutoCode {
    public static void main(String[] args) throws Exception {
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils("portal", "绘图模板-子项", "Arvin");
        Class<?> c = com.huacainfo.ace.portal.model.CanvasTmplItem.class;
        autoCodeUtils.init(c);
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
    }
}
