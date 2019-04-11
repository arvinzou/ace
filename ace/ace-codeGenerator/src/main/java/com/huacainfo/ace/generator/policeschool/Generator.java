package com.huacainfo.ace.generator.policeschool;

import com.huacainfo.ace.generator.base.CodeGenerator;

public class Generator {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator("policeschool", "群英考勤数据", "ArvinZou");
        Class<?> c = null;//com.huacainfo.ace.policeschool.model.QyAttRecord.class;

        //*******************************************
        codeGenerator.init(c);
        //后端java代码
        codeGenerator.generatorJavaManager(c);


        //web形式-适用于新互联网架构
//        codeGenerator.generatorWebClient1(c);


        // web形式-适用于多表格，多数据的表单结构
        codeGenerator.generatorWebClient2(c);

    }

}
