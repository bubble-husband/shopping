package com.hapiio.shopweb.feign;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.ExtendGoods;
import com.hapiio.pojo.model.Goods;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("business-service")
public interface GoodsFeignClient {

    @GetMapping("/goods/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/goods/add")
    public Result add(@RequestBody ExtendGoods goods);

    @PostMapping("/goods/update")
    public Result update(@RequestBody Goods goods);

    @GetMapping("/goods/findOne")
    public Goods findOne(@RequestParam("id") Long id);

    @GetMapping("/goods/findAll")
    public List<Goods> findAll();

    @RequestMapping("/goods/delete")
    public Result delete(@RequestParam("ids") Long[] ids);

    @PostMapping("/goods/search")
    public PageResult search(Goods goods,@RequestParam("page") Integer pageNo, @RequestParam("rows") Integer pageSize);

    @RequestMapping("/goods/selectOptionList")
    public List<Map> selectOptionList();


}
