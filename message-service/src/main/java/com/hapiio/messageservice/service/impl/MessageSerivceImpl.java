package com.hapiio.messageservice.service.impl;

import com.hapiio.messageservice.config.MessageUser;
import com.hapiio.messageservice.model.Message;
import com.hapiio.messageservice.model.MessageResult;
import com.hapiio.messageservice.service.MessageSerivce;
import com.hapiio.messageservice.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class MessageSerivceImpl implements MessageSerivce {

    // 写数据的信息
    // 写入redis信息
    // 配置参数读取类
    @Autowired
    private MessageUser messageUser;

    @Override
    public MessageResult sendMessage(Message message) {
        MessageResult messageResult = null;
        String code = createCode();
        //发送短信
        Map<String, String> headers = new HashMap<String, String>();
        //
        headers.put("Authorization", "APPCODE" + messageUser.getAppcode());
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("moblie", message.getPhone());
        querys.put("param", "code:" + code);
        querys.put("tpl_id", "TP1711063");
        Map<String, String> bodys = new HashMap<String, String>();
        try {
            HttpResponse response = HttpUtils.doPost(messageUser.getHost(), messageUser.getPath(), messageUser.getMethod(), headers, querys, bodys);
            messageResult = new MessageResult();
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = null;
        }
        return messageResult;
    }


    private String createCode() {
        Random random = new Random();
        String message = Integer.toString(random.nextInt(899999) + 100000);
        return message;

    }

}
