package com.lhy.consumer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lhy.common.entity.Customer;
import com.lhy.common.entity.SmallQuestionBank;
import com.lhy.common.entity.SmallQuestionItem;
import com.lhy.common.response.CommonResponse;
import com.lhy.common.service.CustomerService;
import com.lhy.common.service.SmallQuestionBankService;
import com.lhy.common.service.SmallQuestionItemService;
import com.lhy.common.utils.HttpUtils;
import com.lhy.consumer.request.UserRequest;
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
import java.util.ArrayList;
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
    @RequestMapping(value = "/smallQuestionBank", method = RequestMethod.GET)
    public CommonResponse updateSmallQuestionBank() {
        String url = "https://weixins.jx885.com/jxapi/JK/Get_JK_tiku";
        //http://img.jx885.com/lrjk/content/explanationgif/km1/7850.gif"
        //http://img.jx885.com/lrjk/content/audio/vixf/km1/7850.mp3
        String imgUrl = "http://img.jx885.com/lrjk";
        String outPath = "I:\\questionbank";
        String imgSavePath = outPath+"\\images\\";
        String aduioSavePath = outPath+"\\aduio\\";
        List<String> list = new ArrayList<String>();
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
                try{
                    Thread.sleep(3*1000);
                }catch (InterruptedException e){}
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
                    SmallQuestionItem mSmallQuestionItem = JSONObject.parseObject(mObject.toString(), SmallQuestionItem.class);
                    mSmallQuestionItem.setCreateAt(System.currentTimeMillis());
                    mSmallQuestionItem.setUpdateAt(System.currentTimeMillis());
                    mSmallQuestionItem.setServiceId(0L);
                    mSmallQuestionItem.setMd5( DigestUtils.md5Hex(mObject.toString()));
                    mSmallQuestionItem.setBankId(bankId);
                    mSmallQuestionItem.setTypes(Integer.parseInt(types));

                    if(StringUtils.isNotEmpty(mSmallQuestionItem.getAudio())){
                        String fileName = mSmallQuestionItem.getAudio().substring(mSmallQuestionItem.getAudio().lastIndexOf('/')+1);
                        //下载mp3
                        String downFileUrl = imgUrl + mSmallQuestionItem.getAudio();
                        log.info("down:"+downFileUrl);
                        boolean downRe = HttpUtils.downLoadFile(downFileUrl,aduioSavePath+fileName);
                        if(!downRe){
                            downFailedNum++;
                            downFailedUrl += ","+downFileUrl;
                        }
                        mSmallQuestionItem.setAudio("/r/questionbank/aduio/"+fileName);
                    }
                    //下载讲解gif
                    if(StringUtils.isNotEmpty(mSmallQuestionItem.getExplanationgif())){
                        String fileName = mSmallQuestionItem.getExplanationgif().substring(mSmallQuestionItem.getExplanationgif().lastIndexOf('/')+1);
                        String downFileUrl = imgUrl + mSmallQuestionItem.getExplanationgif();
                        log.info("down:"+downFileUrl);
                        boolean downRe = HttpUtils.downLoadFile(downFileUrl,imgSavePath+fileName);
                        if(!downRe){
                            downFailedNum++;
                            downFailedUrl += ","+downFileUrl;
                        }
                        mSmallQuestionItem.setExplanationgif("/r/questionbank/images/"+fileName);
                    }
                    //下载题目中图片
                    if(StringUtils.isNotEmpty(mSmallQuestionItem.getContentImg())){
                        String fileName = mSmallQuestionItem.getContentImg().substring(mSmallQuestionItem.getContentImg().lastIndexOf('/')+1);
                        String downFileUrl = imgUrl + mSmallQuestionItem.getContentImg();
                        log.info("down:"+downFileUrl);
                        boolean downRe = HttpUtils.downLoadFile(downFileUrl,imgSavePath+fileName);
                        if(!downRe){
                            downFailedNum++;
                            downFailedUrl += ","+downFileUrl;
                        }
                        mSmallQuestionItem.setContentImg("/r/questionbank/images/"+fileName);
                    }
                    smallQuestionItemService.save(mSmallQuestionItem);
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

}
