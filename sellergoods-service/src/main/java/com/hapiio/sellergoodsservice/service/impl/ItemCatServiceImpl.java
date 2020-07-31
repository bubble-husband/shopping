package com.hapiio.sellergoodsservice.service.impl;

import com.github.pagehelper.Page;
import com.hapiio.commons.common.PageResult;
import com.hapiio.pojo.model.ItemCat;
import com.hapiio.pojo.model.ItemCatExample;
import com.hapiio.sellergoodsservice.mapper.ItemCatMapper;
import com.hapiio.sellergoodsservice.service.ItemCatService;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Resource
	private ItemCatMapper itemCatMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<ItemCat> findAll() {
		return itemCatMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<ItemCat> page=   (Page<ItemCat>) itemCatMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(ItemCat itemCat) {
		itemCatMapper.insert(itemCat);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(ItemCat itemCat){
		itemCatMapper.updateByPrimaryKey(itemCat);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public ItemCat findOne(Long id){
		return itemCatMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			itemCatMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
	@Override
	public PageResult findPage(ItemCat itemCat, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		System.out.println("22222:"+itemCat.getParentId());
		ItemCatExample example=new ItemCatExample();
		ItemCatExample.Criteria criteria = example.createCriteria();
		
		if(itemCat!=null){
						if(itemCat.getName()!=null && itemCat.getName().length()>0){
				criteria.andNameLike("%"+itemCat.getName()+"%");
			}
			if (itemCat.getParentId()!=null){
				criteria.andParentIdEqualTo(itemCat.getParentId());
			}

		}
		if (criteria.getAllCriteria().size()>0){
			Page<ItemCat> page= (Page<ItemCat>)itemCatMapper.selectByExample(example);
			return new PageResult(page.getTotal(), page.getResult());
		}else {
			return findPage(pageNum,pageSize);
		}

	}
	
}
