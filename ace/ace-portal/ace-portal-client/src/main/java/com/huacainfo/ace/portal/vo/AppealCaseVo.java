
package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.AppealCase;
import com.huacainfo.ace.portal.model.AppealCaseFile;

import java.util.List;


public class AppealCaseVo extends AppealCase {
	private static final long serialVersionUID = 1L;

	private List<AppealCaseFile> list;

	public List<AppealCaseFile> getList() {
		return list;
	}

	public void setList(List<AppealCaseFile> list) {
		this.list = list;
	}
}
