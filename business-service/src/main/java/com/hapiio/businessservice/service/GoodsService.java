package com.hapiio.businessservice.service;

import com.hapiio.commons.common.PageResult;
import com.hapiio.pojo.model.ExtendGoods;
import com.hapiio.pojo.model.Goods;

import java.util.List;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface GoodsService {

	/**
	 * 返回全部列表
	 * @return
	 */
	List<Goods> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	void add(ExtendGoods goods);
	
	
	/**
	 * 修改
	 */
	void update(Goods goods);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	Goods findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	void delete(Long [] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	PageResult search(Goods goods, Integer pageNum,Integer pageSize);
	
}
