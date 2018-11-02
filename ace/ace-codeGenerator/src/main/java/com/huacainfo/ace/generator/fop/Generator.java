package com.huacainfo.ace.generator.fop;

import com.huacainfo.ace.generator.base.CodeGenerator;

public class Generator {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator("fop", "企业走访", "陈晓克");
        Class<?> c = com.huacainfo.ace.fop.model.FopVisit.class;
        //*******************************************
        codeGenerator.init(c);
        //java
        codeGenerator.generatorJavaManager(c);
        //web形式-适用于新互联网架构
        codeGenerator.generatorWebClient1(c);
        // web形式-适用于多表格，多数据的表单结构
        // codeGenerator.generatorWebClient2(c);
    }

}
