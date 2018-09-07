package com.huacainfo.ace.generator.society;

import com.huacainfo.ace.generator.base.CodeGenerator;

public class Generator {

    public static void main(String[] args) throws Exception {
        CodeGenerator codeGenerator = new CodeGenerator("society", "课程", "Arvin");
        Class<?> c = com.huacainfo.ace.portal.model.Article.class;
        codeGenerator.init(c);
       // codeGenerator.generatorWebClient(c);
        codeGenerator.generatorWebManager(c);

    }

}
