package com.hapiio.portalweb.controller;


import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Content;
import com.hapiio.pojo.model.FastDfsFile;
import com.hapiio.portalweb.feign.ContentFeignClient;
import com.hapiio.portalweb.feign.FastFileFeignClient;
import com.hapiio.portalweb.feign.RedisFileFeignClient;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Resource
    private ContentFeignClient contentFeignClient;

	@Resource
    private RedisFileFeignClient redisFileFeignClient;

	@Resource
    private FastFileFeignClient fastFileFeignClient;


    @GetMapping("/findBanners")
    public List<FastDfsFile> findBanners() {
        return fastFileFeignClient.findByType(1);
    }

    @GetMapping("/findByCategoryId")
    public List<Content> findByCategoryId(Long categoryId) {
        System.out.println("1.redis:开始时间:" + (new Date()).getTime());
        List banners = redisFileFeignClient.findList("banners");
        System.out.println("1.redis:结束时间:" + (new Date()).getTime());
        System.out.println("redis-----------------------------------------" + banners.size());
        if (banners == null || banners.size() == 0) {
            //从redis中查找不到，则去数据库中查找

            System.out.println("1.database:开始时间:" + (new Date()).getTime());
            banners = contentFeignClient.findByCategoryId(categoryId);
            System.out.println("1.database:结束时间:" + (new Date()).getTime());
            System.out.println("数据库中-----------------------------------------" + banners.size());
            //查找出来之后，保存到redis
            System.out.println(banners.toArray());

            redisFileFeignClient.saveList("banners", banners);
        }
        return banners;
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<Content> findAll() {
        return contentFeignClient.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return contentFeignClient.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param content
     * @return
     */
    @RequestMapping("/add")
    public Result add(@RequestBody Content content) {
        try {
            contentFeignClient.add(content);
            return new Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param content
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Content content) {
        try {
            contentFeignClient.update(content);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public Content findOne(Long id) {
        return contentFeignClient.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids) {
        try {
            contentFeignClient.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param content
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody Content content, @RequestParam("page") Integer page, @RequestParam("rows") Integer rows) {
        return contentFeignClient.search(content, page, rows);
    }

}
