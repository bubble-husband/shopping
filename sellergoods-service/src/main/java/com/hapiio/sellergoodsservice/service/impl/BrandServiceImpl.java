package com.hapiio.sellergoodsservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Brand;
import com.hapiio.pojo.model.BrandExample;
import com.hapiio.sellergoodsservice.mapper.BrandMapper;
import com.hapiio.sellergoodsservice.service.BrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BrandServiceImpl implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Override
    public List<Map> selectOptionList() {
        return brandMapper.selectOptionList();
    }

    @Override
    public List<Brand> finAll() {
        return brandMapper.selectByExample(null);
    }

    @Override
    public Result batch(List<Brand> brands) {

        Result result=null;
        try {
            //1.插入优化：   原因：1.数据库接是需要时间的，2.数据库在操作同一个代码时，所需的时间要大大的减：代码转议也是要时间
            //1. 数据量的大小： 一次性存多少条
            int index = 0;
            List<Brand> tempBrands = new ArrayList<>();
            for (int i = 0; i < brands.size(); i++) {
                //小于数量时，添加到临时
                if (index < 100) {
                    tempBrands.add(brands.get(i));
                    index++;
                }
                //等于时，则进行保存
                if (index == 100) {
                    brandMapper.batch(tempBrands);
                    tempBrands = new ArrayList<>();
                    index = 0;
                }
            }
            //补刀
            if (index > 0) {
                brandMapper.batch(tempBrands);
            }
            result=new Result(true,"插入成功："+brands.size()+"条");
        }catch (Exception e){
            e.printStackTrace();
            result=new Result(false,"插入失败");
        }

        return result;
    }

    @Override
    public Result addBrand(Brand brand){
       Integer ret =  brandMapper.insert(brand);
       if (ret>0){
           return new Result(true,"添加成功");
       }else {
           return new Result(false,"添加失败");
       }
    }

    @Override
    public Result updateBrand(Brand brand){
        Integer ret =  brandMapper.updateByPrimaryKey(brand);
        if (ret>0){
            return new Result(true,"修改成功");
        }else {
            return new Result(false,"修改失败");
        }
    }

    @Override
    public PageResult findPage(Integer pageNo,Integer pageSize) {
        //分页查询的控件
        PageHelper.startPage(pageNo,pageSize);
        //分页查询
        Page<Brand> page = (Page<Brand>) brandMapper.selectByExample(null);
        //返回结果转换
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public Brand findBrand(Long id){
        return brandMapper.selectByPrimaryKey(id);
    }

    @Override
    public Result deleteBrand(Long[] ids){
        try {
            for (Long id :
                    ids) {
                brandMapper.deleteByPrimaryKey(id);
            }
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }

    }

    @Override
    public PageResult search(Integer pageNo, Integer pageSize, Brand brand){
        System.out.println(brand.getName());
        if (pageNo<=0){
            pageNo=1;
        }
        if (pageSize==0){
            pageSize=10;
        }
        if (brand.getName()==null||brand.getName()==null||brand.getName()==""){
            return findPage(pageNo,pageSize);
        }
        //
        BrandExample example = new BrandExample();
        //
        BrandExample.Criteria  criteria= example.createCriteria();
        criteria.andNameLike("%"+ brand.getName()+"%");

        PageHelper.startPage(pageNo,pageSize);

        Page<Brand> page = (Page<Brand>) brandMapper.selectByExample(example);

        return new PageResult(page.getTotal(),page.getResult());
    }

}
