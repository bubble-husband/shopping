package com.hapiio.portalweb.feign;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Content;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient("content-service")
public interface ContentFeignClient {

    @GetMapping("/content/findByCategoryId")
    public List<Content> findByCategoryId(@RequestParam("categoryId") Long categoryId);

    @GetMapping("/content/findPage")
    public PageResult findPage(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    @PostMapping("/content/add")
    public Result add(@RequestBody Content content);

    @PostMapping("/content/update")
    public Result update(@RequestBody Content content);

    @GetMapping("/content/findOne")
    public Content findOne(@RequestParam("id") Long id);

    @GetMapping("/content/findAll")
    public List<Content> findAll();

    @RequestMapping("/content/delete")
    public Result delete(@RequestParam("ids") Long[] ids);

    @PostMapping("/content/search")
    public PageResult search(Content content,@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);


}
