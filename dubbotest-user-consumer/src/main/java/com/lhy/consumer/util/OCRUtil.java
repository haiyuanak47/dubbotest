package com.lhy.consumer.util;

import com.alibaba.dubbo.common.URL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhy.common.utils.HttpUtils;
import com.lhy.consumer.service.AuthService;

public class OCRUtil {

    public static void main(String[] args) {
        //ITesseract instance =new Tesseract();
        //String filePath = new OCRUtil().getClass().getClassLoader().getResource("chi_sim.traineddata").getPath();
        String filePath = "D:\\IdeaProjects\\dubbotest-basics\\dubbotest-user-consumer\\src\\main\\resources\\static";
        //如果未将tessdata放在根目录下需要指定绝对路径       
        //instance.setDatapath(filePath);
        // 如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
        //instance.setLanguage("chi_sim");
        String searchStr = "009,029,052,071,084,137,148,150,183,202,241,248,279,287,302,335,365,374,377,390,407,421,442,471,472,607,684,678,680,682";
        String[] imgNames = {"1.jpg", "2.jpg", "3.jpg", "4.jpg", "5.jpg", "6.jpg", "7.jpg", "8.jpg"};
        String accessToken = AuthService.getAuth();
        System.out.println("accessToken:" + accessToken);
        for (String imgName : imgNames) {
            //String imgPath = new OCRUtil().getClass().getClassLoader().getResource(imgName).getPath();
            String imgPath = "D:\\IdeaProjects\\dubbotest-basics\\dubbotest-user-consumer\\src\\main\\resources\\static\\img\\" + imgName;
            // 指定识别图片       
            //File imgDir =new File(imgPath);
            //long startTime = System.currentTimeMillis();
            //String ocrResult = instance.doOCR(imgDir);
            // 输出识别结果       
            //System.out.println("OCR Result: \n" + ocrResult +"\n 耗时：" + (System.currentTimeMillis() - startTime) +"ms");
            String imagBase64 = ImageToBase64.ImageToBase64(imgPath);
            String ocrUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/general?access_token=" + accessToken;
            String orcRe = HttpUtils.postURL(ocrUrl, "image=" + URL.encode(imagBase64));
            JSONObject orcJson = JSON.parseObject(orcRe);
            //System.out.println("orcRe:"+orcRe);
            if (!orcJson.containsKey("error_code")) {
                JSONArray wordsResult = JSONArray.parseArray(orcJson.get("words_result").toString());
                for (int a = 0; a < wordsResult.size(); a++) {
                    JSONObject wordsJson = wordsResult.getObject(a, JSONObject.class);
                    String words = wordsJson.get("words").toString();
                    //System.out.println(a+" "+wordsJson.get("words"));
                    for (int i = 0; i < searchStr.split(",").length; i++) {
                        String search = searchStr.split(",")[i];
                        if (words.contains(search)) {
                            System.out.println(words);
                        }
                    }

                }
            }

        }

    }

}
