package com.hapiio.messageservice.mapper;

import com.hapiio.messageservice.model.MessageResult;
import com.hapiio.messageservice.model.MessageResultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageResultMapper {
    int countByExample(MessageResultExample example);

    int deleteByExample(MessageResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MessageResult record);

    int insertSelective(MessageResult record);

    List<MessageResult> selectByExample(MessageResultExample example);

    MessageResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MessageResult record, @Param("example") MessageResultExample example);

    int updateByExample(@Param("record") MessageResult record, @Param("example") MessageResultExample example);

    int updateByPrimaryKeySelective(MessageResult record);

    int updateByPrimaryKey(MessageResult record);
}