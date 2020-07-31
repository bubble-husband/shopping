package com.hapiio.shopweb.controller;


import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.ExtendGoods;
import com.hapiio.pojo.model.Goods;
import com.hapiio.shopweb.feign.GoodsFeignClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Resource
	private GoodsFeignClient goodsFeignClient;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Goods> findAll(){
		return goodsFeignClient.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return goodsFeignClient.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param goods
	 * @return
	 */
	@PostMapping("/add")
	public Result add(@RequestBody ExtendGoods goods){
		Result ret=new Result(true,"保存成功!");
		System.out.println("goods.goods:"+goods.getGoods().getBrandId()+"----------"+goods.getGoods().getCategory1Id());
		System.out.println("goods.goodsDesc.introduce"+goods.getGoodsDesc().getIntroduction());
		System.out.println("goods.goodsDesc.packageList"+goods.getGoodsDesc().getPackageList());
		System.out.println("goods.goodsDesc.saleService"+goods.getGoodsDesc().getSaleService());

		return ret;
	}
	
	/**
	 * 修改
	 * @param goods
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Goods goods){
		return goodsFeignClient.update(goods);

	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Goods findOne(Long id){
		return goodsFeignClient.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){

		return goodsFeignClient.delete(ids);

	}
	
	/**
	 * 查询+分页
	 */
	@PostMapping("/search")
	public PageResult search(@RequestBody Goods goods, @RequestParam("page") Integer page, @RequestParam("rows") Integer rows){
		System.out.println("1111:"+page+rows);
		return goodsFeignClient.search(goods, page, rows);
	}
	
}
