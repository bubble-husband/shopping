package com.hapiio.messageservice.service;

import com.hapiio.messageservice.model.Message;
import com.hapiio.messageservice.model.MessageResult;

public interface MessageSerivce {

    public MessageResult sendMessage(Message message);
}
