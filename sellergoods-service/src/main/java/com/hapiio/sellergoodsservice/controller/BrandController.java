package com.hapiio.sellergoodsservice.controller;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Brand;
import com.hapiio.sellergoodsservice.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    //查询品牌列表
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return brandService.selectOptionList();
    }

    //查询所有
    @RequestMapping("/findAll")
    public List<Brand> findAll(){
        return brandService.finAll();
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNo,Integer pageSize){
        return brandService.findPage(pageNo,pageSize);
    }

    //添加
    @PostMapping("/add")
    public Result addBrand(@RequestBody Brand brand){
        return brandService.addBrand(brand);
    }

    //修改
    @PostMapping("/update")
    public Result updateBrand(@RequestBody Brand brand) {
        return brandService.updateBrand(brand);
    }

    //查询
    @GetMapping("/findOne")
    public Brand findBrand(Long id){
        return brandService.findBrand(id);
    }

    //删除
    @RequestMapping("/delete")
    public Result deleteBrand(@RequestParam("ids") Long[] ids) {
        return brandService.deleteBrand(ids);
    }

    //模糊查询
    @PostMapping("/search")
    public PageResult searchBrand(Integer pageNo,Integer pageSize,@RequestBody Brand brand){
        System.out.println(brand.getName());
        return brandService.search(pageNo,pageSize,brand);
    }


}
