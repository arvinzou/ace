package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * @author Dill.Huang
 *
 * @date 2019年5月14日 下午5:43:20
 *
 * @Description:日行一善服务
 *
 */
public interface CuDayDonateService {
	ResultResponse initDayDonateData(String openId, String projectId);
	
	ResultResponse personalDonateDataDetails(String openId, String projectId);
	
	ResultResponse pointsRank(String openId,String projectId);
}
