package com.lhy.consumer.util;

import com.alibaba.dubbo.common.URL;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhy.common.utils.HttpUtils;
import com.lhy.consumer.service.AuthService;

public class TTSUtil {

    public static boolean text2audio(String text,String outPath){
//        per（基础音库）	选填	度小宇=1，度小美=0，度逍遥=3，度丫丫=4
//        per（精品音库）	选填	度博文=106，度小童=110，度小萌=111，度米朵=103，度小娇=5
        String per = "4";
        String accessToken = AuthService.getOpenAuth();
        String cuid = "yijiaolian";//用户唯一标识，用来计算UV值
        String ctp = "1";//客户端类型选择，web端填写固定值1
        String aue = "3";//mp3格式(默认)
        String pit = "5";//音调，取值0-15，默认为5中语调
        String spd = "5";//语速，取值0-15，默认为5中语速
        String vol = "9";//音量，取值0-15，默认为5中音量
        //System.out.println("accessToken:" + accessToken);
        String ttsUrl = "http://tsn.baidu.com/text2audio";
        String params = "lan=zh&ctp="+ctp+"&cuid="+cuid+"&tok="+accessToken+"&vol="+vol+"&per="+per+"&spd="+spd+"&pit="+pit+"&aue="+aue+"&tex=" + URL.encode(URL.encode(text));
        boolean ttsRe = HttpUtils.postURL(ttsUrl, params,outPath);
        return ttsRe;
    }

    public static void main(String[] args) {
        /*
        per（基础音库）	选填	度小宇=1，度小美=0，度逍遥=3，度丫丫=4
        per（精品音库）	选填	度博文=106，度小童=110，度小萌=111，度米朵=103，度小娇=5
        */
        String per = "4";
        String text = "《公安部令第123号》第三十二条：驾驶技能准考证明的有效期为三年，申请人应当在有效期内完成科目二和科目三考试。未在有效期内完成考试的，已考试合格的科目成绩作废。";//合成的文本，使用UTF-8编码。小于2048个中文字或者英文数字
        String accessToken = AuthService.getOpenAuth();
        String cuid = "lhylhy123";//用户唯一标识，用来计算UV值
        String ctp = "1";//客户端类型选择，web端填写固定值1
        String aue = "3";//mp3格式(默认)
        String pit = "5";//音调，取值0-15，默认为5中语调
        String spd = "5";//语速，取值0-15，默认为5中语速
        String vol = "9";//音量，取值0-15，默认为5中音量
        System.out.println("accessToken:" + accessToken);
        String ttsUrl = "http://tsn.baidu.com/text2audio";
        String params = "lan=zh&ctp="+ctp+"&cuid="+cuid+"&tok="+accessToken+"&vol="+vol+"&per="+per+"&spd="+spd+"&pit="+pit+"&aue="+aue+"&tex=" + URL.encode(URL.encode(text));
        String outPath = "I:\\公安部令第123号("+per+").mp3";
        boolean ttsRe = HttpUtils.postURL(ttsUrl, params,outPath);
        System.out.println(ttsRe);
    }

}
