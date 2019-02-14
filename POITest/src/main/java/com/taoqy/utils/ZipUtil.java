package com.taoqy.utils;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author Taoqy
 * @version 1.0, 2019/2/14
 * @see [相关类/方法]
 * @since bapfopm-sp-ua-service 1.0
 */
@Component
public class ZipUtil {


    public void folderToZip(String sourceFilePath,String zipFilePath) throws IOException {
        File sourceFile = new File(sourceFilePath);
        if(!sourceFile.exists()){
            System.out.println("待压缩的文件夹目录不存在");
        } else {
            File zipFile = new File(zipFilePath);
            if(zipFile.exists()){
                zipFile.createNewFile();
            }
            File[] sourceFiles = sourceFile.listFiles();
            if(sourceFiles == null || sourceFiles.length < 1){
                System.out.println("待压缩的文件夹下没有文件");
            }else {
                FileOutputStream fileOutputStream = new FileOutputStream(zipFile);
                ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
                byte[] bufs = new byte[1024*10];
                for(int i = 0; i < sourceFiles.length; i++){
                    ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                    zipOutputStream.putNextEntry(zipEntry);

                    FileInputStream fileInputStream = new FileInputStream(sourceFiles[i]);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream, 1024 * 10);
                    int read = 0;
                    while ((read = bufferedInputStream.read(bufs,0,1024*10)) != -1){
                        zipOutputStream.write(bufs, 0, read);
                    }
                    bufferedInputStream.close();
                    fileInputStream.close();
                }
                zipOutputStream.close();
                fileOutputStream.close();

            }
        }
    }
}
