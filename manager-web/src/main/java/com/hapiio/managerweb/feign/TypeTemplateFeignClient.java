package com.hapiio.managerweb.feign;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.TypeTemplate;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("sellergoods-service")
public interface TypeTemplateFeignClient {

    @GetMapping("/typeTemplate/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/typeTemplate/add")
    public Result add(@RequestBody TypeTemplate typetemplate);

    @PostMapping("/typeTemplate/update")
    public Result update(@RequestBody TypeTemplate typetemplate);

    @GetMapping("/typeTemplate/findOne")
    public TypeTemplate findOne(@RequestParam("id") Long id);

    @GetMapping("/typeTemplate/findAll")
    public List<TypeTemplate> findAll();

    @RequestMapping("/typeTemplate/delete")
    public Result delete(@RequestParam("ids") Long[] ids);

    @PostMapping("/typeTemplate/search")
    public PageResult search(TypeTemplate typetemplate,@RequestParam("page") Integer pageNo, @RequestParam("rows") Integer pageSize);



}
