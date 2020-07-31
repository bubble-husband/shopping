package com.hapiio.sellergoodsservice.controller;
import java.util.List;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.SpecificationOption;
import com.hapiio.sellergoodsservice.service.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/specificationOption")
public class SpecificationOptionController {

	@Autowired
	private SpecificationOptionService specificationOptionService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<SpecificationOption> findAll(){
		return specificationOptionService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return specificationOptionService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param specificationOption
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody SpecificationOption specificationOption){
		try {
			specificationOptionService.add(specificationOption);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param specificationOption
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody SpecificationOption specificationOption){
		try {
			specificationOptionService.update(specificationOption);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public SpecificationOption findOne(Long id){
		return specificationOptionService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			specificationOptionService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param specificationOption
	 * @param page
	 * @param rows
	 * @return
	 */
	@PostMapping("/search")
	public PageResult search(@RequestBody SpecificationOption specificationOption, int page, int rows  ){
		return specificationOptionService.findPage(specificationOption, page, rows);		
	}
	
}
