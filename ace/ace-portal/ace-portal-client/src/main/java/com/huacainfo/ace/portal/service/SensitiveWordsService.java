package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.model.SensitiveWords;
import com.huacainfo.ace.portal.vo.SensitiveWordsVo;


public interface SensitiveWordsService {


	public abstract PageResult<SensitiveWordsVo> findSensitiveWordsList(SensitiveWords condition) throws Exception;

	public abstract MessageResponse insertSensitiveWords(SensitiveWords condition,UserProp userProp) throws Exception;

	public abstract MessageResponse deleteSensitiveWords(String id, UserProp userProp);

}
