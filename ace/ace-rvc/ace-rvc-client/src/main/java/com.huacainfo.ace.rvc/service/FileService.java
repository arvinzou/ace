package com.huacainfo.ace.rvc.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Arvin on 2018/1/2.
 */
public interface FileService {

    /**
     * 文件上传
     *
     * @param files        文件数组
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param userId       用户ID -- 浪潮ID
     * @return 上传结果
     */
    Map<String, Object> upload(MultipartFile[] files, String conferenceId, String userId);

    /***
     * 文件上传
     * @param fileName 文件名 --  不带后缀
     * @param suffix  文件后缀
     * @param base64Str base64字符串
     * @param conferenceId 会议ID -- rvc_conference.id
     * @param userId 用户ID -- 浪潮ID
     * @return 上传结果
     */
    String upload(String base64Str, String fileName, String suffix,
                  String conferenceId, String userId);
}
