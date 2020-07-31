package com.hapiio.managerweb.feign;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;

import com.hapiio.pojo.model.Specification;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("sellergoods-service")
public interface SpecificationFeignClient {

    @GetMapping("/specification/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/specification/add")
    public Result add(@RequestBody Specification specification);

    @PostMapping("/specification/update")
    public Result update(@RequestBody Specification specification);

    @GetMapping("/specification/findOne")
    public Specification findOne(@RequestParam("id") Long id);

    @GetMapping("/specification/findAll")
    public List<Specification> findAll();

    @RequestMapping("/specification/delete")
    public Result delete(@RequestParam("ids") Long[] ids);

    @PostMapping("/specification/search")
    public PageResult search(Specification specification,@RequestParam("page") Integer pageNo, @RequestParam("rows") Integer pageSize);

    @RequestMapping("/specification/selectOptionList")
    public List<Map> selectOptionList();


}
