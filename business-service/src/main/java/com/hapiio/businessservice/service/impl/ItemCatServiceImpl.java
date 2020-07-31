package com.hapiio.businessservice.service.impl;

import com.hapiio.businessservice.mapper.ItemCatMapper;
import com.hapiio.businessservice.service.ItemCatService;
import com.hapiio.pojo.model.ItemCat;
import com.hapiio.pojo.model.ItemCatExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Resource
    private ItemCatMapper itemCatMapper;

    @Override
    public List<ItemCat> searchItemCatByParentId(Long parentId){

        ItemCatExample example = new ItemCatExample();
        ItemCatExample.Criteria criteria= example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        return itemCatMapper.selectByExample(example);
    }
}
