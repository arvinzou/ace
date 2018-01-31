package com.huacainfo.ace.autocode.live;

import com.huacainfo.ace.autocode.base.AutoCodeUtils;

public class AutoCode {
    public static void main(String[] args) throws Exception {
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils("live", "图片", "王恩");
        Class<?> c = com.huacainfo.ace.live.model.Img.class;
        autoCodeUtils.init(c);
        autoCodeUtils.createVO(c);
        autoCodeUtils.createQVO(c);
        autoCodeUtils.createDAO(c);
        autoCodeUtils.createService(c);
        autoCodeUtils.createServiceImpl(c);
        autoCodeUtils.createAction(c);
        //autoCodeUtils.createJsp(c);
        //autoCodeUtils.createJsConfig(c);
        //autoCodeUtils.createJsView(c);
        //autoCodeUtils.createJsController(c);
        autoCodeUtils.createModel(c);

    }
}
