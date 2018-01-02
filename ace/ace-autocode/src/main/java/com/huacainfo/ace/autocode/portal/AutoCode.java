package com.huacainfo.ace.autocode.portal;

import com.huacainfo.ace.autocode.base.AutoCodeUtils;

public class AutoCode {
	public static void main(String[] args) throws Exception{
        AutoCodeUtils autoCodeUtils = new AutoCodeUtils("portal", "微信用户", "陈晓克");
        Class<?> c = com.huacainfo.ace.portal.model.Userinfo.class;
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
