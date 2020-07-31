package com.hapiio.shopweb.feign;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Seller;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("business-service")
public interface SellerFeignClient {

    @GetMapping("/seller/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/seller/add")
    public Result add(@RequestBody Seller seller);

    @PostMapping("/seller/update")
    public Result update(@RequestBody Seller seller);

    @GetMapping("/seller/findOne")
    public Seller findOne(@RequestParam("id") Long id);

    @GetMapping("/seller/findAll")
    public List<Seller> findAll();

//    @RequestMapping("/seller/delete")
//    public Result delete(@RequestParam("ids") Long[] ids);

    @PostMapping("/seller/search")
    public PageResult search(Seller seller,@RequestParam("page") Integer pageNo, @RequestParam("rows") Integer pageSize);

    @RequestMapping("/seller/selectOptionList")
    public List<Map> selectOptionList();


}
