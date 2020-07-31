package com.hapiio.businessservice.service;

import com.hapiio.pojo.model.ItemCat;

import java.util.List;

public interface ItemCatService {

    List<ItemCat> searchItemCatByParentId(Long parentId);
}
