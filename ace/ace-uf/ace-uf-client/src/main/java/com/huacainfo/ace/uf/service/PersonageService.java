package com.huacainfo.ace.uf.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.uf.model.Personage;
import com.huacainfo.ace.uf.vo.PersonageVo;
import com.huacainfo.ace.uf.vo.PersonageQVo;

import java.util.List;
import java.util.Map;

public interface PersonageService {
	
	public abstract PageResult<PersonageVo> findPersonageList(PersonageQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertPersonage(Personage obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updatePersonage(Personage obj,UserProp userProp) throws Exception;
	public abstract SingleResult<PersonageVo> selectPersonageByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deletePersonageByPersonageId(String id,UserProp userProp) throws Exception;
	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp)
			throws Exception;
	public List<Map<String,Object>> selectPersonageListMap(WxUser user,String longitude,String latitude,String q);

	public  List<Map<String,Object>> selectPersonageList(String q, WxUser user) throws Exception;

	Map<String,Object> selectPersonageById(String id);

	public Map<String, Object> selectPersonage(String q);

	public  List<Tree>  selectPersonageTreeList(String q,WxUser user) throws Exception;
	public  List<CheckTree>  selectPersonageCheckTreeList() throws Exception;

	public int isExitPersonageByMobile(String mobile);
}
