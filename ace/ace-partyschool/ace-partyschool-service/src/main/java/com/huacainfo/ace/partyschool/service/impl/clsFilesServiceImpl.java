package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.FilesDao;
import com.huacainfo.ace.partyschool.dao.SignDao;
import com.huacainfo.ace.partyschool.model.Files;
import com.huacainfo.ace.partyschool.service.SignService;
import com.huacainfo.ace.partyschool.service.clsFilesService;
import com.huacainfo.ace.partyschool.vo.AccountVo;
import com.huacainfo.ace.partyschool.vo.FilesQVo;
import com.huacainfo.ace.partyschool.vo.FilesVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("clsFilesService")
/**
 * @author: Arvin
 * @version: 2019-01-04
 * @Description: TODO(班级文件)
 */
public class clsFilesServiceImpl implements clsFilesService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FilesDao filesDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private SignService signService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(班级文件分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FilesVo>
     * @author: Arvin
     * @version: 2019-01-04
     */
    @Override
    public PageResult<FilesVo> findFilesList(FilesQVo condition, int start,
                                             int limit, String orderBy) throws Exception {
        PageResult<FilesVo> rst = new PageResult<>();
        List<FilesVo> list = this.filesDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.filesDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    @Override
    public ResultResponse findFilesListVo(FilesQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception {
        AccountVo accountVo = (AccountVo) signService.getAcctInfo(userProp.getAccount()).getData();
        List<FilesVo> list;
        condition.setMobile(userProp.getAccount());
        if ("teacher".equals(accountVo.getRegType())) {
            list = this.filesDao.findTeacherFileList(condition, start, limit, orderBy);
        } else if ("student".equals(accountVo.getRegType())) {
            list = this.filesDao.findStudentFileList(condition, start, limit, orderBy);
        } else {
            return new ResultResponse();
        }
        return new ResultResponse(0, "文件获取成功", list);
    }
    /**
     * @throws
     * @Title:insertFiles
     * @Description: TODO(添加班级文件)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse insertFiles(Files o, UserProp userProp) throws Exception {


        int temp = this.filesDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "班级文件名称重复！");
        }

        o.setPushDate(new Date());
        o.setPublisher(userProp.getName());
        o.setId(GUIDUtil.getGUID());
        o.setStatus("1");
        this.filesDao.insert(o);
        this.dataBaseLogService.log("添加班级文件", "班级文件", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加班级文件完成！");
    }

    /**
     * @throws
     * @Title:updateFiles
     * @Description: TODO(更新班级文件)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse updateFiles(Files o, UserProp userProp) throws Exception {

        this.filesDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更班级文件", "班级文件", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更班级文件完成！");
    }

    /**
     * @throws
     * @Title:selectFilesByPrimaryKey
     * @Description: TODO(获取班级文件)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Files>
     * @author: Arvin
     * @version: 2019-01-04
     */
    @Override
    public SingleResult
            <FilesVo> selectFilesByPrimaryKey(String id) throws Exception {
        SingleResult
                <FilesVo> rst = new SingleResult<>();
        rst.setValue(this.filesDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFilesByFilesId
     * @Description: TODO(删除班级文件)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-04
     */
    @Override
    public MessageResponse deleteFilesByFilesId(String id, UserProp userProp) throws
            Exception {
        this.filesDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除班级文件", "班级文件", id, id,
                "班级文件", userProp);
        return new MessageResponse(0, "班级文件删除完成！");
    }


}
