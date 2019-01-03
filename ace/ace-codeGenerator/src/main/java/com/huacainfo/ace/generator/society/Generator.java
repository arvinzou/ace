package com.huacainfo.ace.generator.society;

import com.huacainfo.ace.generator.base.CodeGenerator;

public class Generator {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator("society", "个人爱心币排行", "彭飞");
        Class<?> c =null;

        //*******************************************
        codeGenerator.init(c);
        codeGenerator.generatorJavaManager(c);
        //web形式-适用于新互联网架构
        codeGenerator.generatorWebClient1(c);


        // web形式-适用于多表格，多数据的表单结构
//        codeGenerator.generatorWebClient2(c);

    }

}
