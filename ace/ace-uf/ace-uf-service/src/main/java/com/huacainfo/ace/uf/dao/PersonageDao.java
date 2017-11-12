package com.huacainfo.ace.uf.dao;

import java.util.List;
import java.util.Map;

import com.huacainfo.ace.common.model.WxUser;
import com.huacainfo.ace.uf.model.*;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.vo.PersonageQVo;
import com.huacainfo.ace.uf.vo.PersonageVo;

public interface PersonageDao {

    /**
     * 【奖惩信息】添加统战人士的奖惩信息
     * @param obj
     * @return
     */
    int insertPerJc(PerJiangCheng obj);

    /**
     * 【奖惩信息】删除统战人士的一条奖惩信息。
     * @param id
     * @return
     */
    int deletePerJCById(String id);

    /**
     * 【奖惩信息】更新统战人士的奖惩信息
     * @param obj
     * @return
     */
    int  updatePerJC(PerJiangCheng obj);

    /**
     * 【奖惩信息】获取统战人士的奖惩信息
     * @param PersonageId
     * @return
     */
    List<PerJiangCheng> selectJcByPrimaryKey(String PersonageId);



    /**
     * 【社会职务】添加统战人士的社会职务
     * @param obj
     * @return
     */
    int insertPerZw(PerZhiWu obj);

    /**
     * 【社会职务】删除统战人士的一条社会职务。
     * @param id
     * @return
     */
    int deletePerZwById(String id);

    /**
     * 【社会职务】更新统战人士的社会职务
     * @param obj
     * @return
     */
    int  updatePerZw(PerZhiWu obj);

    /**
     * 【社会职务】获取统战人士的社会职务
     * @param PersonageId
     * @return
     */
    List<PerZhiWu> selectZwByPrimaryKey(String PersonageId);



    /**
     * 【人士类型】添加统战人士的人士类型
     * @param obj
     * @return
     */
    int insertPerCategory(PerCategory obj);

    /**
     * 【人士类型】删除统战人士的一条人士类型。
     * @param id
     * @return
     */
    int deletePerCategoryById(String id);

    /**
     * 【人士类型】更新统战人士的人士类型
     * @param obj
     * @return
     */
    int  updatePerCategory(PerCategory obj);

    /**
     * 【人士类型】获取统战人士的人士类型
     * @param PersonageId
     * @return
     */
    List<PerCategory> selectPerCategoryByPrimaryKey(String PersonageId);


    /**
     * 【兴趣爱好】添加统战人士的兴趣爱好
     * @param obj
     * @return
     */
    int insertInterest(interest obj);

    /**
     * 【兴趣爱好】删除统战人士的一条兴趣爱好。
     * @param id
     * @return
     */
    int deleteInterestById(String id);

    /**
     * 【兴趣爱好】更新统战人士的兴趣爱好
     * @param obj
     * @return
     */
    int  updateInterest(interest obj);

    /**
     * 【兴趣爱好】获取统战人士的兴趣爱好
     * @param PersonageId
     * @return
     */
    List<interest> selectInterestByPrimaryKey(String PersonageId);

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

    Map<String,Object> selectPersonageCfgById(String id);
}