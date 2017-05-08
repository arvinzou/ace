package com.huacainfo.ace.operana.service.impl;

import com.huacainfo.ace.operana.service.ChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.huacainfo.ace.operana.dao.ChartDao;
import com.huacainfo.ace.operana.dao.NormDao;
import com.huacainfo.ace.operana.model.Norm;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxiaoke on 2017/5/8.
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService {
    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ChartDao chartDao;

    @Autowired
    private NormDao normDao;
	@Override
	public Map<String,Object> chart1(String meetingId,String topicId,String normId) throws Exception {
        Calendar a=Calendar.getInstance();
        int year=a.get(Calendar.YEAR);

        Norm o=normDao.selectByPrimaryKey(normId);
        Map<String,Object> row=new HashMap<String,Object>();
        if(o.getCalType().equals("1")){

        }else{

        }

		return null;
	}

	@Override
	public Map<String,Object> chart2(String meetingId,String topicId,String normId) throws Exception {
		return null;
	}

	@Override
	public Map<String,Object> chart3(String meetingId,String topicId,String normId) throws Exception {
		return null;
	}

	@Override
	public Map<String,Object> chart4(String meetingId,String topicId,String normId) throws Exception {
		return null;
	}
}
