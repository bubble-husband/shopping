package com.hapiio.managerweb.feign;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.ItemCat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("sellergoods-service")
public interface ItemCatFeignClient {

    @GetMapping("/itemCat/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/itemCat/add")
    public Result add(@RequestBody ItemCat itemCat);

    @PostMapping("/itemCat/update")
    public Result update(@RequestBody ItemCat itemCat);

    @GetMapping("/itemCat/findOne")
    public ItemCat findOne(@RequestParam("id") Long id);

    @GetMapping("/itemCat/findAll")
    public List<ItemCat> findAll();

    @RequestMapping("/itemCat/delete")
    public Result delete(@RequestParam("ids") Long[] ids);

    @PostMapping("/itemCat/search")
    public PageResult search(ItemCat itemCat, @RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    @RequestMapping("/itemCat/selectOptionList")
    public List<Map> selectOptionList();


}
