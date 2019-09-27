package com.lhy.consumer.util;

import com.ksyun.ks3.dto.CannedAccessControlList;
import com.ksyun.ks3.dto.PutObjectResult;
import com.ksyun.ks3.http.HttpClientConfig;
import com.ksyun.ks3.service.Ks3;
import com.ksyun.ks3.service.Ks3Client;
import com.ksyun.ks3.service.Ks3ClientConfig;
import com.ksyun.ks3.service.request.PutObjectRequest;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class Ks3Util {
    private static Ks3 client =null;

    private static final String bucket = "yijiaolian";
    private static final String endpoint = "ks3-cn-beijing.ksyun.com";
    private static final String accessKeyID = "AKLTMRvOHYbyR3Sie957Cr3nnw";
    private static final String accessKeySecret = "OOJc5yOC3NDUKACroySLHOzDOVYuIu1qxG4VxRdcXDkq/WVlrEnE60aDLJR9Ob5mtg==";
    public static final String audioK3sDir = "questionbank/aduio/";

    private static Ks3 getInterface(){
        if(client==null){
            Ks3ClientConfig config = new Ks3ClientConfig();
            /**
             * 设置服务地址</br>
             * 中国（北京）| ks3-cn-beijing.ksyun.com
             * 中国（上海）| ks3-cn-shanghai.ksyun.com
             * 中国（香港）| ks3-cn-hk-1.ksyun.com
             */
            config.setEndpoint(endpoint);   //此处以北京region为例
            config.setProtocol(Ks3ClientConfig.PROTOCOL.https);
            /**
             *true表示以   endpoint/{bucket}/{key}的方式访问</br>
             *false表示以  {bucket}.endpoint/{key}的方式访问
             */
            config.setPathStyleAccess(false);

            HttpClientConfig hconfig = new HttpClientConfig();

            //在HttpClientConfig中可以设置httpclient的相关属性，比如代理，超时，重试等。
            config.setHttpClientConfig(hconfig);
            client = new Ks3Client(accessKeyID,accessKeySecret,config);
            //或者：client.setKs3config(config);
        }
        return client;
    }

    /**
     *将new File("<filePath>")这个文件上传至<bucket名称>这个存储空间下，并命名为<key>
     */
    public static boolean putObject(File file,String saveFilePath){
        PutObjectRequest request = new PutObjectRequest(bucket,
                saveFilePath, file);
        //上传一个公开文件
        request.setCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult re = getInterface().putObject(request);
        System.out.println(re);
        return true;
    }

    public static void delObject(String keyPath){
        //3.输入指定的存储空间和文件名，执行删除文件操作；
        try{
            getInterface().deleteObject(bucket,keyPath);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void uploadFile(String uploadFilePath,String k3sDir){
        try {
            ArrayList<String> fileList = new ArrayList<String>();
            File files = new File(uploadFilePath);
            fileList = fileList(files,0,fileList);
            Iterator<String> it = fileList.iterator();
            System.out.println("上传文件开始————————————————————————");
            while (it.hasNext()) {
                String filepath = it.next();
                File startFile = new File(filepath);
                String saveFilePath = k3sDir+startFile.getName();
                boolean re = putObject(startFile,saveFilePath);
                System.out.println(((re)?"文件上传成功！":"文件山传失败！！！！")+"文件名：《"+startFile.getName()+"》 目标路径："+filepath);
                System.out.println();
            }
            System.out.println("上传文件结束————————————————————————");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> fileList(File file,int node,ArrayList<String> list) {
        node++;
        File[] files = file.listFiles();
        String filePath = "";
        if (files != null) {
            for (File f : files) {
                if(f.isFile()){
                    filePath=f.getPath();
                    list.add(filePath);
                }
                fileList(f,node,list);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        //Ks3Util.uploadFile("I:\\questionbank\\aduio","questionbank/aduio/");
        Ks3Util.uploadFile("I:\\questionbank\\newaduio",audioK3sDir);
    }

}
