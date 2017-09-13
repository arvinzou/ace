package com.huacainfo.ace.uf.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.WxUser;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.Personage;
import com.huacainfo.ace.uf.vo.PersonageQVo;
import com.huacainfo.ace.uf.vo.PersonageVo;

public interface PersonageDao {
    int deleteByPrimaryKey(String PersonageId);

    int insert(Personage record);


    PersonageVo selectByPrimaryKey(String PersonageId);


    int updateByPrimaryKey(Personage record);
    
    List<PersonageVo> findList(@Param("condition") PersonageQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PersonageQVo condition);

	int isExit(Personage record);



	List<Map<String,Object>> selectPersonageList(@Param("longitude") String longitude,@Param("latitude") String latitude,@Param("q") String q, @Param("user")WxUser user);


    List<Map<String,Object>> selectPersonageListByText(@Param("q") String q, @Param("user")WxUser user);

    List<Map<String,Object>> selectPersonageCategoryList(@Param("user")WxUser user);

    List<Map<String,Object>>  selectPersonagePartyList(@Param("user")WxUser user);

    Map<String,Object> selectPersonageById(String id);

    List<Map<String,Object>> selectPersonage(@Param("q")String q);

    List<Map<String,Object>> selectPersonageTreeList(@Param("q")String q,@Param("user")WxUser user);
    List<Map<String,Object>> selectPersonageCheckTreeList();
    Map<String,String> selectDictCodeBYId(String id);
    int isExitPersonageByMobile(@Param("mobile")String mobile);
}