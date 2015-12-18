package cn.hackingwu;

import cn.hackingwu.utils.StringUtil;
import com.sun.org.apache.bcel.internal.classfile.Code;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * @author hackingwu.
 * @since 2015/11/3
 */
public class Scaffolding {

    static Scanner scanner = new Scanner(System.in);

    public static String tip = "命令支持：\ngenerate-all\ngenerate-service\ngenerate-controller\ngenerate-repository。请在命令后输入全类名，以空格分隔........";

    public static void main(String[] args){
        String command = "";
        String className = "";
        if (args.length>0)command=args[0];
        if (args.length>1)className=args[1];
        generateByCommand(command,className);
    }


    public static void generateByCommand(String command,String className){
        String[] types = new String[]{"controller","repository","service","serviceImpl"};

        if (StringUtil.isEmpty(command)||StringUtil.isEmpty(className)){
            System.out.println(tip);
            processInput();
        }
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            System.out.println("不存在该类");
            processInput();
        }
        int lastDotIndex = className.lastIndexOf(".");

        if (command.equals("generate-all")){
            for (String type : types){
                CodeGenerator.newInstance().generate(type,className.substring(0,lastDotIndex),className.substring(lastDotIndex+1));
            }
            return;
        }
        else if (command.equals("generate-service")){
            for (int i = 2;i<4;i++){
                CodeGenerator.newInstance().generate(types[i],className.substring(0,lastDotIndex),className.substring(lastDotIndex+1));
            }
            return;
        }
        else if(command.equals("generate-controller")){
            CodeGenerator.newInstance().generate(types[0],className.substring(0,lastDotIndex),className.substring(lastDotIndex+1));
            return;
        }
        else if(command.equals("generate-repository")){
            CodeGenerator.newInstance().generate(types[1],className.substring(0,lastDotIndex),className.substring(lastDotIndex+1));
            return;
        }
        else {
            System.out.println(tip);
            processInput();
        }
    }

    public static void processInput(){
        String userInput = scanner.nextLine();
        String[] ss = new String[]{"",""};
        if (!StringUtil.isEmpty(userInput)) {
            String[] temp = userInput.split("\\s+");
            if (temp.length>0) ss[0] = temp[0];
            if (temp.length>1) ss[1] = temp[1];
        }
        generateByCommand(ss[0],ss[1]);
    }

}
