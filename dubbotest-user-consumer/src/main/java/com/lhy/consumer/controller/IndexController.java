package com.lhy.consumer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhy.common.entity.Customer;
import com.lhy.common.entity.SmallQuestion;
import com.lhy.common.entity.SmallQuestionBank;
import com.lhy.common.entity.SmallQuestionItem;
import com.lhy.common.response.CommonResponse;
import com.lhy.common.service.CustomerService;
import com.lhy.common.service.SmallQuestionBankService;
import com.lhy.common.service.SmallQuestionItemService;
import com.lhy.common.service.SmallQuestionService;
import com.lhy.common.utils.HttpUtils;
import com.lhy.consumer.request.UserRequest;
import com.lhy.consumer.util.Ks3Util;
import com.lhy.consumer.util.TTSUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON)
@Api(description = "用户信息")
public class IndexController {
    // logback
    private final static Logger log = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private CustomerService customerService;
    @Autowired
    private SmallQuestionBankService smallQuestionBankService;
    @Autowired
    private SmallQuestionItemService smallQuestionItemService;
    @Autowired
    private SmallQuestionService smallQuestionService;
    /***
     * 查询所有用户信息
     * @return
     */
    @ApiOperation(value = "查询所有用户的信息")
    @RequestMapping(value = "/geCustomerList", method = RequestMethod.GET)
    public CommonResponse geCustomerList() {
        return CommonResponse.success(customerService.getList());
    }

    /***
     * 查询单个用户的信息
     * @param userId 用户id
     * @return
     */
    @ApiOperation(value = "查询单个用户的信息")
    @RequestMapping(value = "/geCustomer/{userId}", method = RequestMethod.GET)
    public CommonResponse geCustomer(@PathVariable("userId") long userId) {
        return CommonResponse.success(customerService.getCustomer(userId));
    }

    /***
     * 添加一个用户
     * @param request
     * @return
     */
    @ApiOperation(value = "添加一个用户")
    @RequestMapping(value = "/saveUser", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON)
    public CommonResponse saveUser(@RequestBody UserRequest request) {
        Customer user = new Customer();
        user.seCustomerName(request.geCustomerName());
        return CommonResponse.success(customerService.saveCustomer(user));
    }

    //@ApiOperation(value = "更新小程序题库")
   /* @RequestMapping(value = "/smallQuestionBank", method = RequestMethod.GET)
    public CommonResponse updateSmallQuestionBank() {
        String url = "https://weixins.jx885.com/jxapi/JK/Get_JK_tiku";
        //http://img.jx885.com/lrjk/content/explanationgif/km1/7850.gif"
        //http://img.jx885.com/lrjk/content/audio/vixf/km1/7850.mp3
        String imgUrl = "http://img.jx885.com/lrjk";
        String outPath = "I:\\questionbank";
        String imgSavePath = outPath+"\\images\\";
        String aduioSavePath = outPath+"\\aduio\\";
        List<String> list = new ArrayList<String>();
        //是否下载文件
        boolean isDownFile = false;
        //科目一
        list.add("{ \"type\": \"1\", \"types\": \"11\", \"title\": \"精选题一\"}");//精选题一
        list.add("{ \"type\": \"1\", \"types\": \"12\", \"title\": \"精选题二\"}");//精选题二
        list.add("{ \"type\": \"1\", \"types\": \"13\", \"title\": \"精选题三\"}");//精选题三
        list.add("{ \"type\": \"1\", \"types\": \"21\", \"title\": \"精选题四\"}");//精选题四
        list.add("{ \"type\": \"1\", \"types\": \"71\", \"title\": \"精选题五\"}");//精选题五
        list.add("{ \"type\": \"1\", \"types\": \"41\", \"title\": \"精选题六\"}");//精选题六
        list.add("{ \"type\": \"1\", \"types\": \"51\", \"title\": \"交通标志\"}");//交通标志
        list.add("{ \"type\": \"1\", \"types\": \"61\", \"title\": \"指示仪表\"}");//指示仪表
        list.add("{ \"type\": \"1\", \"types\": \"81\", \"title\": \"地面标线\"}");//地面标线
        list.add("{ \"type\": \"1\", \"types\": \"91\", \"title\": \"看图讲解\"}");//看图讲解
        list.add("{ \"type\": \"1\", \"types\": \"31\", \"title\": \"常识题  \"}");//常识题
        list.add("{ \"type\": \"1\", \"types\": \"100\", \"title\": \"其他    \"}");//其他
        //地方题库
        list.add("{ \"type\": \"1\", \"types\": \"914\", \"title\": \"上海\"}");//上海
        list.add("{ \"type\": \"1\", \"types\": \"915\", \"title\": \"四川\"}");//四川
        list.add("{ \"type\": \"1\", \"types\": \"913\", \"title\": \"济南\"}");//济南
        list.add("{ \"type\": \"1\", \"types\": \"912\", \"title\": \"福州\"}");//福州
        list.add("{ \"type\": \"1\", \"types\": \"0\", \"title\": \"必做500题\"}");//必做500题
        //科目四
        list.add("{ \"type\": \"4\", \"types\": \"1\", \"title\": \"题目找字\"}");//题目找字
        list.add("{ \"type\": \"4\", \"types\": \"2\", \"title\": \"答案找字\"}");//答案找字
        list.add("{ \"type\": \"4\", \"types\": \"3\", \"title\": \"交通常识\"}");//交通常识
        list.add("{ \"type\": \"4\", \"types\": \"4\", \"title\": \"交通标志\"}");//交通标志
        list.add("{ \"type\": \"4\", \"types\": \"5\", \"title\": \"看图讲解\"}");//看图讲解
        list.add("{ \"type\": \"4\", \"types\": \"6\", \"title\": \"安全文明\"}");//安全文明
        list.add("{ \"type\": \"4\", \"types\": \"7\", \"title\": \"全靠记忆\"}");//全靠记忆
        list.add("{ \"type\": \"4\", \"types\": \"8\", \"title\": \"全选技巧\"}");//全选技巧
        list.add("{ \"type\": \"4\", \"types\": \"9\", \"title\": \"高速公路\"}");//高速公路
        list.add("{ \"type\": \"4\", \"types\": \"10\", \"title\": \"违法举例\"}");//违法举例
        list.add("{ \"type\": \"4\", \"types\": \"100\", \"title\": \"其他    \"}");//其他
        //地方题库
        list.add("{ \"type\": \"4\", \"types\": \"941\", \"title\": \"北京\"}");//北京
        list.add("{ \"type\": \"4\", \"types\": \"943\", \"title\": \"武汉\"}");//武汉
        list.add("{ \"type\": \"4\", \"types\": \"942\", \"title\": \"福州\"}");//福州
        list.add("{ \"type\": \"4\", \"types\": \"0\", \"title\": \"必做500题\"}");//必做500题
        String re = "";
        int downFailedNum = 0;
        String downFailedUrl = "";
        for(String paramStr:list){
            log.info("执行开始:"+paramStr);

            JSONObject paramJSONObject = (JSONObject)JSONObject.parse(paramStr);
            String type = paramJSONObject.getString("type");
            String types = paramJSONObject.getString("types");
            String title = paramJSONObject.getString("title");
            //通过接口查询
            String param = "userjson="+paramStr;
            //JSON
            String result = HttpUtils.postURL(url,param);
            JSONObject resultJSONObject = (JSONObject)JSONObject.parse(result);
            String dataStr = resultJSONObject.getString("data");
            String md5Str = DigestUtils.md5Hex(dataStr);
            //SmallQuestionBank mSmallQuestionBank = new SmallQuestionBank();
            SmallQuestionBank mSmallQuestionBank = smallQuestionBankService.getSmallQuestionBank(type,types);

            if(mSmallQuestionBank!=null){
                //对比数据是否更新
                if(!mSmallQuestionBank.getMd5().equals(md5Str)){
                    re+= paramStr+" 数据有更新！/n";
                    mSmallQuestionBank.setUpdateAt(System.currentTimeMillis());
                    mSmallQuestionBank.setMd5(md5Str);
                    mSmallQuestionBank.setDatas(dataStr);
                    mSmallQuestionBank.setDataVersion(mSmallQuestionBank.getDataVersion()+1);
                    smallQuestionBankService.update(mSmallQuestionBank);
                }
                //continue;
            }else{
                *//*try{
                    Thread.sleep(3*1000);
                }catch (InterruptedException e){}*//*
                mSmallQuestionBank = new SmallQuestionBank();
                mSmallQuestionBank.setCreateAt(System.currentTimeMillis());
                mSmallQuestionBank.setUpdateAt(System.currentTimeMillis());
                mSmallQuestionBank.setType(Integer.parseInt(type));
                mSmallQuestionBank.setTypes(Integer.parseInt(types));
                mSmallQuestionBank.setTitle(title);
                mSmallQuestionBank.setServiceId(0L);
                mSmallQuestionBank.setDatas(dataStr);
                mSmallQuestionBank.setMd5(md5Str);
                mSmallQuestionBank.setDataVersion(0L);
                int insterNum = smallQuestionBankService.save(mSmallQuestionBank);
                //
                Long bankId = smallQuestionBankService.getSmallQuestionBank(type,types).getId();
                JSONObject dataJSONObject = (JSONObject)JSONObject.parse(dataStr);
                JSONArray dataJSONArray = dataJSONObject.getJSONArray("datas");
                for(Object mObject:dataJSONArray){
                    //小程序驾考题库项目关联表
                    SmallQuestionItem mSmallQuestionItem = JSONObject.parseObject(mObject.toString(), SmallQuestionItem.class);
                    mSmallQuestionItem.setCreateAt(System.currentTimeMillis());
                    mSmallQuestionItem.setUpdateAt(System.currentTimeMillis());
                    mSmallQuestionItem.setServiceId(0L);
                    mSmallQuestionItem.setMd5( DigestUtils.md5Hex(mObject.toString()));
                    mSmallQuestionItem.setBankId(bankId);
                    mSmallQuestionItem.setTypes(Integer.parseInt(types));
                    mSmallQuestionItem.setType(Integer.parseInt(type));
                    smallQuestionItemService.save(mSmallQuestionItem);
                    //查询是否存在此pid 题库
                    List<SmallQuestion> questionList = smallQuestionService.getListByPid(mSmallQuestionItem.getPid());
                    if(questionList.size()>0){
                        continue;
                    }
                    //小程序驾考考题明细表
                    SmallQuestion mSmallQuestion = JSONObject.parseObject(mObject.toString(), SmallQuestion.class);
                    mSmallQuestion.setCreateAt(System.currentTimeMillis());
                    mSmallQuestion.setUpdateAt(System.currentTimeMillis());
                    mSmallQuestion.setServiceId(0L);

                    if(StringUtils.isNotEmpty(mSmallQuestion.getAudio())){
                        String fileName = mSmallQuestion.getAudio().substring(mSmallQuestion.getAudio().lastIndexOf('/')+1);
                        if(isDownFile){
                            //下载mp3
                            String downFileUrl = imgUrl + mSmallQuestion.getAudio();
                            log.info("down:"+downFileUrl);
                            boolean downRe = HttpUtils.downLoadFile(downFileUrl,aduioSavePath+fileName);
                            if(!downRe){
                                downFailedNum++;
                                downFailedUrl += ","+downFileUrl;
                            }
                        }
                        mSmallQuestion.setAudio("/r/questionbank/aduio/"+fileName);
                    }
                    //下载讲解gif
                    if(StringUtils.isNotEmpty(mSmallQuestion.getExplanationgif())){
                        String fileName = mSmallQuestion.getExplanationgif().substring(mSmallQuestion.getExplanationgif().lastIndexOf('/')+1);
                        if(isDownFile){
                            String downFileUrl = imgUrl + mSmallQuestion.getExplanationgif();
                            log.info("down:"+downFileUrl);
                            boolean downRe = HttpUtils.downLoadFile(downFileUrl,imgSavePath+fileName);
                            if(!downRe){
                                downFailedNum++;
                                downFailedUrl += ","+downFileUrl;
                            }
                        }
                        mSmallQuestion.setExplanationgif("/r/questionbank/images/"+fileName);
                    }
                    //下载题目中图片
                    if(StringUtils.isNotEmpty(mSmallQuestion.getContentImg())){
                        String fileName = mSmallQuestion.getContentImg().substring(mSmallQuestion.getContentImg().lastIndexOf('/')+1);
                        if(isDownFile){
                            String downFileUrl = imgUrl + mSmallQuestion.getContentImg();
                            log.info("down:"+downFileUrl);
                            boolean downRe = HttpUtils.downLoadFile(downFileUrl,imgSavePath+fileName);
                            if(!downRe){
                                downFailedNum++;
                                downFailedUrl += ","+downFileUrl;
                            }
                        }
                        mSmallQuestion.setContentImg("/r/questionbank/images/"+fileName);
                    }
                    //smallQuestionItemService.save(mSmallQuestionItem);
                    smallQuestionService.save(mSmallQuestion);
                }
                re+= paramStr+" 新增数据！"+dataJSONArray.size()+"\n";
            }
            System.out.println("result===="+result);
            log.info("=============================");
        }
        re += "下载文件失败数量："+downFailedNum+",";
        if(downFailedNum>0){
            re += "下载文件失败URL："+downFailedUrl;
        }
        log.info("===========执行结束==========="+re);
        return CommonResponse.success(re);
    }*/

    //新题库（懒人理论）
    @RequestMapping(value = "/smallQuestionBank1", method = RequestMethod.GET)
    public CommonResponse updateSmallQuestionBankNew() {
        String url = "https://newweixins.jx885.com/lrjk/questionBank/queryQuestions";
        String imgUrl = "http://img.jx885.com/lrjk";
        String outPath = "I:\\questionbank";
        String imgSavePath = outPath+"\\images\\";
        String aduioSavePath = outPath+"\\aduio\\";
        String dbImgUrlStr = "/r/questionbank/images/";
        dbImgUrlStr = "https://ks3-cn-beijing.ksyun.com/yijiaolian/questionbank/images/";
        List<String> list = new ArrayList<String>();
        //是否下载文件
        boolean isDownFile = true;
        //【小车】
        //科目一
        list.add("{ \"subject\": \"\",\"classify\": \"11\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"精选题一\"}");//精选题一
        list.add("{ \"subject\": \"\",\"classify\": \"12\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"精选题二\"}");//精选题二
        list.add("{ \"subject\": \"\",\"classify\": \"13\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"精选题三\"}");//精选题三
        list.add("{ \"subject\": \"\",\"classify\": \"21\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"精选题四\"}");//精选题四
        list.add("{ \"subject\": \"\",\"classify\": \"71\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"精选题五\"}");//精选题五
        list.add("{ \"subject\": \"\",\"classify\": \"41\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"精选题六\"}");//精选题六
        list.add("{ \"subject\": \"\",\"classify\": \"51\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"交通标志\"}");//交通标志
        list.add("{ \"subject\": \"\",\"classify\": \"61\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"指示仪表\"}");//指示仪表
        list.add("{ \"subject\": \"\",\"classify\": \"81\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"地面标线\"}");//地面标线
        list.add("{ \"subject\": \"\",\"classify\": \"91\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"看图讲解\"}");//看图讲解
        list.add("{ \"subject\": \"\",\"classify\": \"100\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"其他\"}");//其他
        //地方题库 科目一
        list.add("{ \"subject\": \"\",\"classify\": \"914\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"上海\"}");//上海
        list.add("{ \"subject\": \"\",\"classify\": \"915\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"四川\"}");//四川
        list.add("{ \"subject\": \"\",\"classify\": \"913\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"济南\"}");//济南
        list.add("{ \"subject\": \"\",\"classify\": \"912\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"福州\"}");//福州
        //科目四
        list.add("{ \"subject\": \"\",\"classify\": \"1\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"题目找字\"}");//题目找字
        list.add("{ \"subject\": \"\",\"classify\": \"2\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"答案找字\"}");//答案找字
        list.add("{ \"subject\": \"\",\"classify\": \"3\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"交通常识\"}");//交通常识
        list.add("{ \"subject\": \"\",\"classify\": \"4\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"交通标志\"}");//交通标志
        list.add("{ \"subject\": \"\",\"classify\": \"5\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"看图讲解\"}");//看图讲解
        list.add("{ \"subject\": \"\",\"classify\": \"6\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"安全文明\"}");//安全文明
        list.add("{ \"subject\": \"\",\"classify\": \"7\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"全靠记忆\"}");//全靠记忆
        list.add("{ \"subject\": \"\",\"classify\": \"8\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"全选技巧\"}");//全选技巧
        list.add("{ \"subject\": \"\",\"classify\": \"9\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"高速公路\"}");//高速公路
        list.add("{ \"subject\": \"\",\"classify\": \"10\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"违法举例\"}");//违法举例
        list.add("{ \"subject\": \"\",\"classify\": \"100\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"其他\"}");//其他
        //地方题库 科目四
        list.add("{ \"subject\": \"\",\"classify\": \"941\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"北京\"}");//北京
        list.add("{ \"subject\": \"\",\"classify\": \"943\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"武汉\"}");//武汉
        list.add("{ \"subject\": \"\",\"classify\": \"942\", \"questionType\": \"1\", \"carType\": \"1\", \"title\": \"福州\"}");//福州
        //【货车】
        //科目一
        list.add("{ \"subject\": \"\",\"classify\": \"1\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"题目找字\"}");//题目找字
        list.add("{ \"subject\": \"\",\"classify\": \"2\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"答案找字\"}");//答案找字
        list.add("{ \"subject\": \"\",\"classify\": \"3\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"交通常识\"}");//交通常识
        list.add("{ \"subject\": \"\",\"classify\": \"4\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"交通标志\"}");//交通标志
        list.add("{ \"subject\": \"\",\"classify\": \"5\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"看图讲解\"}");//看图讲解
        list.add("{ \"subject\": \"\",\"classify\": \"6\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"安全文明\"}");//安全文明
        list.add("{ \"subject\": \"\",\"classify\": \"7\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"全靠记忆\"}");//全靠记忆
        list.add("{ \"subject\": \"\",\"classify\": \"8\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"全选技巧\"}");//全选技巧
        list.add("{ \"subject\": \"\",\"classify\": \"9\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"高速公路\"}");//高速公路
        list.add("{ \"subject\": \"\",\"classify\": \"10\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"违法举例\"}");//违法举例
        list.add("{ \"subject\": \"\",\"classify\": \"100\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"其他\"}");//其他
        list.add("{ \"subject\": \"\",\"classify\": \"50\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"货车专用\"}");//货车专用
        //科目四
        list.add("{ \"subject\": \"\",\"classify\": \"111\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"题目找字1\"}");//题目找字1
        list.add("{ \"subject\": \"\",\"classify\": \"112\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"题目找字2\"}");//题目找字2
        list.add("{ \"subject\": \"\",\"classify\": \"113\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"答案找字\"}");//答案找字
        list.add("{ \"subject\": \"\",\"classify\": \"114\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"交通常识\"}");//交通常识
        list.add("{ \"subject\": \"\",\"classify\": \"115\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"交通标志\"}");//交通标志
        list.add("{ \"subject\": \"\",\"classify\": \"116\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"看图讲解\"}");//看图讲解
        list.add("{ \"subject\": \"\",\"classify\": \"117\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"安全文明\"}");//安全文明
        list.add("{ \"subject\": \"\",\"classify\": \"118\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"记忆题\"}");//记忆题
        list.add("{ \"subject\": \"\",\"classify\": \"119\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"全选技巧\"}");//全选技巧
        list.add("{ \"subject\": \"\",\"classify\": \"120\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"高速公路\"}");//高速公路
        list.add("{ \"subject\": \"\",\"classify\": \"121\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"违法举例\"}");//违法举例
        list.add("{ \"subject\": \"\",\"classify\": \"122\", \"questionType\": \"1\", \"carType\": \"2\", \"title\": \"其他\"}");//其他
        //【客车】
        //科目一
        list.add("{ \"subject\": \"\",\"classify\": \"1\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"题目找字\"}");//题目找字
        list.add("{ \"subject\": \"\",\"classify\": \"2\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"答案找字\"}");//答案找字
        list.add("{ \"subject\": \"\",\"classify\": \"3\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"交通常识\"}");//交通常识
        list.add("{ \"subject\": \"\",\"classify\": \"4\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"交通标志\"}");//交通标志
        list.add("{ \"subject\": \"\",\"classify\": \"5\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"看图讲解\"}");//看图讲解
        list.add("{ \"subject\": \"\",\"classify\": \"6\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"安全文明\"}");//安全文明
        list.add("{ \"subject\": \"\",\"classify\": \"7\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"全靠记忆\"}");//全靠记忆
        list.add("{ \"subject\": \"\",\"classify\": \"8\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"全选技巧\"}");//全选技巧
        list.add("{ \"subject\": \"\",\"classify\": \"9\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"高速公路\"}");//高速公路
        list.add("{ \"subject\": \"\",\"classify\": \"10\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"违法举例\"}");//违法举例
        list.add("{ \"subject\": \"\",\"classify\": \"100\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"其他\"}");//其他
        list.add("{ \"subject\": \"\",\"classify\": \"54\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"货车专用\"}");//货车专用

        //科目四
        list.add("{ \"subject\": \"\",\"classify\": \"111\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"题目找字1\"}");//题目找字1
        list.add("{ \"subject\": \"\",\"classify\": \"112\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"题目找字2\"}");//题目找字2
        list.add("{ \"subject\": \"\",\"classify\": \"113\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"答案找字\"}");//答案找字
        list.add("{ \"subject\": \"\",\"classify\": \"114\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"交通常识\"}");//交通常识
        list.add("{ \"subject\": \"\",\"classify\": \"115\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"交通标志\"}");//交通标志
        list.add("{ \"subject\": \"\",\"classify\": \"116\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"看图讲解\"}");//看图讲解
        list.add("{ \"subject\": \"\",\"classify\": \"117\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"安全文明\"}");//安全文明
        list.add("{ \"subject\": \"\",\"classify\": \"118\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"记忆题\"}");//记忆题
        list.add("{ \"subject\": \"\",\"classify\": \"119\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"全选技巧\"}");//全选技巧
        list.add("{ \"subject\": \"\",\"classify\": \"120\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"高速公路\"}");//高速公路
        list.add("{ \"subject\": \"\",\"classify\": \"121\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"违法举例\"}");//违法举例
        list.add("{ \"subject\": \"\",\"classify\": \"122\", \"questionType\": \"1\", \"carType\": \"3\", \"title\": \"其他\"}");//其他

        String re = "";
        int downFailedNum = 0;
        String downFailedUrl = "";
        for(String paramStr:list){
            log.info("执行开始:"+paramStr);

            JSONObject paramJSONObject = (JSONObject)JSONObject.parse(paramStr);
            String subject = paramJSONObject.getString("subject");
            String classify = paramJSONObject.getString("classify");
            String questionType = paramJSONObject.getString("questionType");
            String carType = paramJSONObject.getString("carType");
            String title = paramJSONObject.getString("title");
            //通过接口查询
            String param = "";
            if("1".equals(questionType)){
                param = "classify="+classify+"&carType="+carType+"&questionType="+questionType;
            }else if("2".equals(questionType)){
                param = "subject="+subject+"&carType="+carType+"&questionType="+questionType;
            }
            //JSON
            String result = HttpUtils.postURL(url,param);
            System.out.println("result===================:"+result);
            JSONObject resultJSONObject = (JSONObject)JSONObject.parse(result);
            String dataStr = resultJSONObject.getString("data");
            String md5Str = DigestUtils.md5Hex(dataStr);
            //SmallQuestionBank mSmallQuestionBank = new SmallQuestionBank();
            SmallQuestionBank mSmallQuestionBank = smallQuestionBankService.getSmallQuestionBank(param);

            if(mSmallQuestionBank!=null){
                //对比数据是否更新
                if(!mSmallQuestionBank.getMd5().equals(md5Str)){
                    re+= paramStr+" 数据有更新！/n";
                    mSmallQuestionBank.setUpdateAt(System.currentTimeMillis());
                    mSmallQuestionBank.setMd5(md5Str);
                    mSmallQuestionBank.setDatas(dataStr);
                    mSmallQuestionBank.setDataVersion(mSmallQuestionBank.getDataVersion()+1);
                    smallQuestionBankService.update(mSmallQuestionBank);
                }
                //continue;
            }else{
                /*try{
                    Thread.sleep(3*1000);
                }catch (InterruptedException e){}*/
                mSmallQuestionBank = new SmallQuestionBank();
                mSmallQuestionBank.setCreateAt(System.currentTimeMillis());
                mSmallQuestionBank.setUpdateAt(System.currentTimeMillis());
                mSmallQuestionBank.setParam(param);
                mSmallQuestionBank.setTitle(title);
                mSmallQuestionBank.setServiceId(0L);
                mSmallQuestionBank.setDatas(dataStr);
                mSmallQuestionBank.setMd5(md5Str);
                mSmallQuestionBank.setDataVersion(0L);
                int insterNum = smallQuestionBankService.save(mSmallQuestionBank);
                //
                Long bankId = smallQuestionBankService.getSmallQuestionBank(param).getId();
                /*JSONObject dataJSONObject = (JSONObject)JSONObject.parse(dataStr);
                JSONArray dataJSONArray = dataJSONObject.getJSONArray("datas");*/
                JSONArray dataJSONArray =(JSONArray)JSONArray.parse(dataStr);
                for(Object mObject:dataJSONArray){
                    //小程序驾考题库项目关联表
                    SmallQuestionItem mSmallQuestionItem = JSONObject.parseObject(mObject.toString(), SmallQuestionItem.class);
                    mSmallQuestionItem.setCreateAt(System.currentTimeMillis());
                    mSmallQuestionItem.setUpdateAt(System.currentTimeMillis());
                    mSmallQuestionItem.setServiceId(0L);
                    mSmallQuestionItem.setMd5( DigestUtils.md5Hex(mObject.toString()));
                    mSmallQuestionItem.setBankId(bankId);
                    if(StringUtils.isNotEmpty(subject)){
                        mSmallQuestionItem.setSubject(Integer.parseInt(subject));
                    }
                    if(StringUtils.isNotEmpty(classify)){
                        mSmallQuestionItem.setClassify(Integer.parseInt(classify));
                    }
                    smallQuestionItemService.save(mSmallQuestionItem);
                    //查询是否存在此pid 题库
                    List<SmallQuestion> questionList = smallQuestionService.getListByPid(mSmallQuestionItem.getPid());
                    if(questionList.size()>0){
                        continue;
                    }
                    //小程序驾考考题明细表
                    SmallQuestion mSmallQuestion = JSONObject.parseObject(mObject.toString(), SmallQuestion.class);
                    mSmallQuestion.setCreateAt(System.currentTimeMillis());
                    mSmallQuestion.setUpdateAt(System.currentTimeMillis());
                    mSmallQuestion.setServiceId(0L);
                    /*if(StringUtils.isNotEmpty(mSmallQuestion.getAudio())){
                        String fileName = mSmallQuestion.getAudio().substring(mSmallQuestion.getAudio().lastIndexOf('/')+1);
                        if(isDownFile){
                            //下载mp3
                            String downFileUrl = imgUrl + mSmallQuestion.getAudio();
                            log.info("down:"+downFileUrl);
                            boolean downRe = HttpUtils.downLoadFile(downFileUrl,aduioSavePath+fileName);
                            if(!downRe){
                                downFailedNum++;
                                downFailedUrl += ","+downFileUrl;
                            }
                        }
                        mSmallQuestion.setAudio("/r/questionbank/aduio/"+fileName);
                    }*/
                    //下载讲解gif
                    if(StringUtils.isNotEmpty(mSmallQuestion.getExplanationgif())){
                        String fileName = mSmallQuestion.getExplanationgif().substring(mSmallQuestion.getExplanationgif().lastIndexOf('/')+1);
                        if(isDownFile){
                            String downFileUrl = imgUrl + mSmallQuestion.getExplanationgif();
                            log.info("down:"+downFileUrl);
                            boolean downRe = HttpUtils.downLoadFile(downFileUrl,imgSavePath+fileName);
                            if(!downRe){
                                downFailedNum++;
                                downFailedUrl += ","+downFileUrl;
                            }
                        }
                        mSmallQuestion.setExplanationgif(dbImgUrlStr+fileName);
                    }
                    //下载题目中图片
                    if(StringUtils.isNotEmpty(mSmallQuestion.getContentImg())){
                        String fileName = mSmallQuestion.getContentImg().substring(mSmallQuestion.getContentImg().lastIndexOf('/')+1);
                        if(isDownFile){
                            String downFileUrl = imgUrl + mSmallQuestion.getContentImg();
                            log.info("down:"+downFileUrl);
                            boolean downRe = HttpUtils.downLoadFile(downFileUrl,imgSavePath+fileName);
                            if(!downRe){
                                downFailedNum++;
                                downFailedUrl += ","+downFileUrl;
                            }
                        }
                        mSmallQuestion.setContentImg(dbImgUrlStr+fileName);
                    }
                    //smallQuestionItemService.save(mSmallQuestionItem);
                    smallQuestionService.save(mSmallQuestion);
                }
                re+= paramStr+" 新增数据！"+dataJSONArray.size()+"\n";
            }
            System.out.println("result===="+result);
            log.info("=============================");
        }
        re += "下载文件失败数量："+downFailedNum+",";
        if(downFailedNum>0){
            re += "下载文件失败URL："+downFailedUrl;
        }
        log.info("===========执行结束==========="+re);
        return CommonResponse.success(re);
    }

    String outPath = "";
    String dbAudioUrlStr = "https://ks3-cn-beijing.ksyun.com/yijiaolian/questionbank/aduio/";
    int fileNum = 0;
    int failureFileNum = 0;
    final int ttsAudioRetry = 3;
    //题库合成语音文件
    @RequestMapping(value = "/smallQuestionTTS", method = RequestMethod.GET)
    public CommonResponse smallQuestionTTS() {
        StringBuilder re = new StringBuilder();
        List<SmallQuestion> questionList = smallQuestionService.getList();
        log.info("===========查询题库记录数==========="+questionList.size());
        re.append("查询题库记录数:"+questionList.size());
        outPath = "I:\\questionbank\\aduio\\";
        fileNum = 0;
        failureFileNum = 0;
        for(SmallQuestion mSmallQuestion:questionList){
            createSmallQuestionTTS(mSmallQuestion,true,true,true);
            //测试只执行一次
            //break;
        }
        re.append("成功合成语音文件数量:"+fileNum);
        re.append("，失败合成语音文件数量:"+failureFileNum);
        log.info("===========执行结束==========="+re);
        return CommonResponse.success(re);
    }

    //查询合成失败的文件，重新题库合成语音文件
    @RequestMapping(value = "/reBuildSmallQuestionTTS", method = RequestMethod.GET)
    public CommonResponse reBuildSmallQuestionTTS() {
        String searchOutPath = "I:\\questionbank\\nulladuio\\";
        outPath = "I:\\questionbank\\newaduio\\";
        StringBuilder re = new StringBuilder();
        fileNum = 0;
        failureFileNum = 0;
        try {
            ArrayList<String> fileList = new ArrayList<String>();
            File files = new File(searchOutPath);
            fileList = Ks3Util.fileList(files,0,fileList);
            Iterator<String> it = fileList.iterator();
            System.out.println("合成文件开始————————————————————————");
            while (it.hasNext()) {
                String filepath = it.next();
                File startFile = new File(filepath);
                String fileName = startFile.getName();
                Integer pid = null;
                String pidStr = "";
                boolean createContentAduio = false;
                boolean createExplanationAduio = false;
                boolean createExplainAduio = false;
                if(fileName.contains("content")){
                    createContentAduio = true;
                    pidStr = fileName.replaceAll("content","");
                }else if(fileName.contains("explanation")){
                    createExplanationAduio = true;
                    pidStr = fileName.replaceAll("explanation","");
                }else if(fileName.contains("explain")){
                    createExplainAduio = true;
                    pidStr = fileName.replaceAll("explain","");
                }else{
                    log.error("匹配文件名失败！！！！！！！！！！"+"fileName:"+fileName+" pidStr:"+pidStr);
                }
                pidStr = pidStr.replaceAll(".mp3","");
                log.info("fileName:"+fileName+" pidStr:"+pidStr);
                pid = Integer.parseInt(pidStr);
                //查询是否存在此pid 题库
                List<SmallQuestion> questionList = smallQuestionService.getListByPid(pid);
                if(questionList.size()>0){
                    createSmallQuestionTTS(questionList.get(0),createContentAduio,createExplanationAduio,createExplainAduio);
                }
                //测试只执行一次
                //break;
            }
            System.out.println("合成文件结束————————————————————————");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //re.append("查询题库记录数:"+questionList.size());
        re.append("成功合成语音文件数量:"+fileNum);
        re.append("，失败合成语音文件数量:"+failureFileNum);
        log.info("===========执行结束==========="+re);
        return CommonResponse.success(re);
    }


    //合成语音文件更新数据库
    private void createSmallQuestionTTS(SmallQuestion mSmallQuestion,boolean createContentAduio,boolean createExplanationAduio,boolean createExplainAduio){
        //题目内容
        String content = mSmallQuestion.getContent();
        //选项
        String item1 = mSmallQuestion.getItem1();
        String item2 = mSmallQuestion.getItem2();
        String item3 = mSmallQuestion.getItem3();
        String item4 = mSmallQuestion.getItem4();
        //题目类型，0选择题/1判断题/2多选题
        Integer type =  mSmallQuestion.getType();
        if(type==0||type==2){
            if(StringUtils.isNotEmpty(item1)){
                content+="选项A,"+item1+"。";
            }
            if(StringUtils.isNotEmpty(item2)){
                content+="选项B,"+item2+"。";
            }
            if(StringUtils.isNotEmpty(item3)){
                content+="选项C,"+item3+"。";
            }
            if(StringUtils.isNotEmpty(item4)){
                content+="选项D,"+item4+"。";
            }
        }else if(type==1){
            content+="选项A,正确。选项B,错误。";
        }
        String contentAduioName = "content"+mSmallQuestion.getPid()+".mp3";
        String explanationAduioName = "explanation"+mSmallQuestion.getPid()+".mp3";
        String explainAduioName = "explain"+mSmallQuestion.getPid()+".mp3";
        //技巧讲解内容
        String explanation = mSmallQuestion.getExplanation();
        //官方讲解内容
        String explain = mSmallQuestion.getExplain();
        if(StringUtils.isNotEmpty(explain)){
            explain = explain.replaceAll("<p>","");
            explain = explain.replaceAll("</p>","");
            explain = explain.replaceAll("<br/>","");
        }

        //合成题目内容语音
        if(createContentAduio){
            if(StringUtils.isNotEmpty(content)){
                boolean text2audiore1 = TTSUtil.text2audio(content,outPath+contentAduioName);
                int tryMaxNum = ttsAudioRetry;
                while(!text2audiore1 && tryMaxNum>0){
                    text2audiore1 = TTSUtil.text2audio(content,outPath+contentAduioName);
                    tryMaxNum--;
                }
                if(!text2audiore1){
                    failureFileNum++;
                    log.error(mSmallQuestion.getId()+"===========合成题目内容语音 失败==========="+content);
                }else{
                    mSmallQuestion.setContentAudio(dbAudioUrlStr+contentAduioName);
                    fileNum++;
                }
            }else{
                log.error(mSmallQuestion.getId()+"===========合成题目内容语音 失败 内容为空!!!==========="+content);
                mSmallQuestion.setContentAudio("");
                //删除金山云端空文件
                //Ks3Util.delObject(Ks3Util.audioK3sDir+contentAduioName);
            }
        }
        //合成技巧讲解内容语音
        if(createExplanationAduio){
            if(StringUtils.isNotEmpty(explanation)){
                boolean text2audiore2 = TTSUtil.text2audio(explanation,outPath+explanationAduioName);
                int tryMaxNum = ttsAudioRetry;
                while(!text2audiore2 && tryMaxNum>0){
                    text2audiore2 = TTSUtil.text2audio(explanation,outPath+explanationAduioName);
                    tryMaxNum--;
                }
                if(!text2audiore2){
                    failureFileNum++;
                    log.error(mSmallQuestion.getId()+"===========合成技巧讲解内容语音 失败 内容为空!!!==========="+explanation);
                }else{
                    mSmallQuestion.setAudio(dbAudioUrlStr+explanationAduioName);
                    fileNum++;
                }
            }else{
                mSmallQuestion.setAudio("");
                //删除金山云端空文件
                //Ks3Util.delObject(Ks3Util.audioK3sDir+explanationAduioName);
            }

        }
        //合成官方讲解内容语音
        if(createExplainAduio){
            if(StringUtils.isNotEmpty(explain)){
                boolean text2audiore3 = TTSUtil.text2audio(explain,outPath+explainAduioName);
                int tryMaxNum = ttsAudioRetry;
                while(!text2audiore3 && tryMaxNum>0){
                    text2audiore3 = TTSUtil.text2audio(explain,outPath+explainAduioName);
                    tryMaxNum--;
                }
                if(!text2audiore3){
                    failureFileNum++;
                    log.error(mSmallQuestion.getId()+"===========合成官方讲解内容语音 失败 内容为空!!!==========="+explain);
                }else if(StringUtils.isEmpty(explain)){
                    mSmallQuestion.setExplainAudio(dbAudioUrlStr+explainAduioName);
                    fileNum++;
                }
            }else{
                mSmallQuestion.setExplainAudio("");
                //删除金山云端空文件
                //Ks3Util.delObject(Ks3Util.audioK3sDir+explanationAduioName);
            }

        }
        smallQuestionService.update(mSmallQuestion);
    }
}
