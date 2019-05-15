package com.huacainfo.ace.glink.web.controller;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.canvas.ImageKit;
import com.huacainfo.ace.glink.model.BMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

/**
 * @Auther: Arvin
 * @Date: 2018/12/28 16:30
 * @Description:
 */
@RestController
@RequestMapping("/www/toolKit")
public class WToolKitController extends GLinkBaseController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello, it's me [g-Link smart light]";
    }

    @RequestMapping("/mapGather")
    public String mapGather(String json) {

        //
        String tempPath = "f:\\bmap\\";
        //
        List<BMap> list = JsonUtil.toList(json, BMap.class);
        if (CollectionUtils.isEmpty(list)) {
            return "获取数据失败！";
        }

        BufferedImage image;
        String path;//10/191/53.png
        File file;
        String fileName;
        String dir;
        for (BMap bMap : list) {
            path = bMap.getPath();
            fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
            dir = tempPath + path.substring(0, path.lastIndexOf("/")).replace("/", "\\");
            file = new File(dir);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                image = ImageKit.getImageURL(bMap.getImage());
            } catch (MalformedURLException e) {
                continue;
            }
            path = dir + "\\" + fileName;
            ImageKit.outputImage(image, path);
        }

        return "搞完了";
    }


}
