package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.jxb.model.Reg;

public interface RegService {

    MessageResponse insertReg(Reg o) throws Exception;

}
