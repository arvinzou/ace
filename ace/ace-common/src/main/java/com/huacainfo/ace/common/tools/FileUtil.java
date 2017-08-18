package com.huacainfo.ace.common.tools;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chenxiaoke on 2017/8/17.
 */
public class FileUtil {
    public static String ReadFile(String Path){
        BufferedReader reader = null;
        String laststr = "";
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while((tempString = reader.readLine()) != null){
                laststr += tempString;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }
}
