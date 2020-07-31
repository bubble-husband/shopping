package com.hapiio.managerweb.feign;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Brand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("sellergoods-service")
public interface BrandFeignClient {

    @GetMapping("/brand/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/brand/add")
    public Result addBrand(@RequestBody Brand brand);

    @PostMapping("/brand/update")
    public Result updateBrand(@RequestBody Brand brand);

    @GetMapping("/brand/findOne")
    public Brand findBrand(@RequestParam("id") Long id);

    @RequestMapping("/brand/delete")
    public Result deleteBrand(@RequestParam("ids") Long[] ids);

    @PostMapping("/brand/search")
    public PageResult searchBrand(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,Brand brand);

    @RequestMapping("/brand/selectOptionList")
    public List<Map> selectOptionList();
}
