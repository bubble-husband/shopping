package com.hapiio.sellergoodsservice.service;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Brand;

import java.util.List;
import java.util.Map;

public interface BrandService {

    List<Map> selectOptionList();

    List<Brand> finAll();

    Result batch(List<Brand> brands);

    Result addBrand(Brand brand);

    Result updateBrand(Brand brand);

    PageResult findPage(Integer pageNo, Integer pageSize);

    Brand findBrand(Long id);

    Result deleteBrand(Long[] ids);

    PageResult search(Integer pageNo, Integer pageSize, Brand brand);
}
