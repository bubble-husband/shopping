package com.hapiio.shopweb.controller;

import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.Seller;
import com.hapiio.shopweb.feign.SellerFeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

	@Resource
	private SellerFeignClient sellerFeignClient;

	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<Seller> findAll(){
		return sellerFeignClient.findAll();
	}


	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult findPage(int page, int rows){
		return sellerFeignClient.findPage(page, rows);
	}

	/**
	 * 增加
	 * @param seller
	 * @return
	 */
	@PostMapping("/add")
	public Result add(@RequestBody Seller seller){
		System.out.println(seller.getPassword());
		return sellerFeignClient.add(seller);
	}

	/**
	 * 修改
	 * @param Seller
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody Seller Seller){
		return sellerFeignClient.update(Seller);
	}

	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public Seller findOne(Long id){
		return sellerFeignClient.findOne(id);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
//	@RequestMapping("/delete")
//	public Result delete(Long [] ids){
//		try {
//			sellerFeignClient.delete(ids);
//			return new Result(true, "删除成功");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return new Result(false, "删除失败");
//		}
//	}

	/**
	 * 查询+分页
	 * @param Seller
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody Seller Seller, int page, int rows  ){
		return sellerFeignClient.search(Seller, page, rows);
	}

	@RequestMapping("/selectOptionList")
	public List<Map> selectOptionList() {
		return sellerFeignClient.selectOptionList();
	}

}
