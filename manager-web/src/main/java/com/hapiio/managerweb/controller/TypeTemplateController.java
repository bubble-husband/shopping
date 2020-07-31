package com.hapiio.managerweb.controller;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.managerweb.feign.TypeTemplateFeignClient;
import com.hapiio.pojo.model.TypeTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {

	@Resource
	private TypeTemplateFeignClient typeTemplateFeignClient;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TypeTemplate> findAll(){
		return typeTemplateFeignClient.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return typeTemplateFeignClient.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param entity
	 * @return
	 */
	@PostMapping("/add")
	public Result add(@RequestBody TypeTemplate entity){
		System.out.println(entity.getBrandIds());
		return typeTemplateFeignClient.add(entity);

	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@PostMapping("/update")
	public Result update(@RequestBody TypeTemplate entity){
		return typeTemplateFeignClient.update(entity);
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TypeTemplate findOne(Long id){
		return typeTemplateFeignClient.findOne(id);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){

		return typeTemplateFeignClient.delete(ids);

	}
	
		/**
	 * 查询+分页
	 * @param typeTemplate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@PostMapping("/search")
	public PageResult search(@RequestBody TypeTemplate typeTemplate, @RequestParam("page") Integer pageNo, @RequestParam("rows") Integer pageSize){
		return typeTemplateFeignClient.search(typeTemplate, pageNo,pageSize);
	}
	
}
