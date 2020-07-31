package com.hapiio.portalweb.feign;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.ContentCategory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("content-service")
public interface ContentCategoryFeignClient {

    @GetMapping("/contentCategory/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/contentCategory/add")
    public Result add(@RequestBody ContentCategory contentCategory);

    @PostMapping("/contentCategory/update")
    public Result update(@RequestBody ContentCategory contentCategory);

    @GetMapping("/contentCategory/findOne")
    public ContentCategory findOne(@RequestParam("id") Long id);

    @GetMapping("/contentCategory/findAll")
    public List<ContentCategory> findAll();

    @RequestMapping("/contentCategory/delete")
    public Result delete(@RequestParam("ids") Long[] ids);

    @PostMapping("/contentCategory/search")
    public PageResult search(ContentCategory contentCategory,@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);



}
