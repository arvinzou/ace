package com.huacainfo.ace.glink.api;

/**
 * @ClassName FApiToolKit
 * @Description 强电接口--调用工具
 * @Author Arvin Zou
 * @Date 2019/4/15 9:57
 */
public class FApiToolKit {

    private FApiToolKit instance;

    /**
     * 单例模式
     */
    private FApiToolKit() {

    }

    public FApiToolKit newInstance() {
        if (instance == null) {
            return new FApiToolKit();
        }

        return instance;
    }
}
