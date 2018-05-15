package com.huacainfo.ace.autocode.portal;

import com.huacainfo.ace.autocode.base.AutoCodeUtils;

public class AutoCode {
	public static void main(String[] args) throws Exception{
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils("portal", "诉求", "陈晓克");
        Class<?> c = com.huacainfo.ace.portal.model.AppealCase.class;
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
