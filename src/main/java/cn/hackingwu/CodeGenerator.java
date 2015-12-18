/* =============================================================
 * Created: [2015/6/11 11:43] by wuzj(971643)
 * =============================================================
 *
 * Copyright 2014-2015 NetDragon Websoft Inc. All Rights Reserved
 *
 * =============================================================
 */
package cn.hackingwu;

import cn.hackingwu.utils.StringUtil;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzj(971643).
 * @since 2015/06/11
 */
public class CodeGenerator {

    private String domainPackage = "domain";
    private Configuration cfg = null;
    private CodeGenerator() {
        cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(),"/template/code");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
    }

    private static CodeGenerator codeGenerator = new CodeGenerator();

    public static CodeGenerator newInstance(){
        return codeGenerator;
    }

    public void generateAll(DomainProperty domainProperty){
        String[] types = new String[]{"controller","repository","service","serviceImpl"};
        for (String type : types){
            generate(type,domainProperty.packageName,domainProperty.className);
        }
    }

    public void generate(String type,String entityPackage,String entityName){
        generate(type,entityPackage,entityName,null);
    }


    public void generate(String type,String entityPackage,String entityName,Map data){
        Template controllerTemplate = null;
        String className = entityName ;
        String packageBase = getPackageBase(entityPackage);
        String packageName = packageBase + (packageBase.length() > 0 && !packageBase.endsWith(".") ? "." : "") + toPackage(type);
        if (data == null) data = new HashMap();
        data.put("className",className);
        data.put("package",packageName);
        String path = getFilePathFormPackage(packageName);
        generateDirectory(path);
        try {
            controllerTemplate = cfg.getTemplate(type+".ftl");
            Writer out = new FileWriter(path + File.separator + className + StringUtil.upperCaseFirstCharacter(type) + ".java");
            controllerTemplate.process(data,out);
            out.flush();
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    /**
     * 类型转换成包名
     * 例如 controller-> controller;service->service;serviceImpl=>service.impl
     * @param type
     * @return
     */
    public String toPackage(String type){
        StringBuilder stringBuilder = new StringBuilder();
//        type = StringUtil.lowerCaseFirstCharacter(type);
        stringBuilder.append(type.charAt(0));
        for (int i = 1 ; i < type.length();i++){
            if (Character.isUpperCase(type.charAt(i))) stringBuilder.append(".").append(Character.toLowerCase(type.charAt(i)));
            else stringBuilder.append(type.charAt(i));
        }
        return stringBuilder.toString();
    }

    public String getPackageBase(Class clazz){
        String domainPackageName = clazz.getPackage().getName();
        return getPackageBase(domainPackageName);
    }

    public String getPackageBase(String domainPackageName){
        String basePackageName = "";
        int index = domainPackageName.lastIndexOf("domain");
        if (index > 0 && domainPackageName.charAt(index-1) == '.') basePackageName = domainPackageName.substring(0,index-1);//除去那个点
        return basePackageName;
    }

    public String getFilePathFormPackage(String packageName){
        return System.getProperty("user.dir") + File.separator
                +"src" + File.separator
                + "main" + File.separator
                + "java" + File.separator
                +packageName.replace(".",File.separator);
    }

    public boolean generateDirectory(String path){
        File file = new File(path);
        return file.mkdirs();
    }

}
