package com.hapiio.sellergoodsservice.service.impl;

import com.github.pagehelper.Page;
import com.hapiio.commons.common.PageResult;
import com.hapiio.pojo.model.Specification;
import com.hapiio.pojo.model.SpecificationExample;
import com.hapiio.pojo.model.SpecificationOption;
import com.hapiio.sellergoodsservice.mapper.SpecificationMapper;
import com.hapiio.sellergoodsservice.mapper.SpecificationOptionMapper;
import com.hapiio.sellergoodsservice.service.SpecificationService;

import com.github.pagehelper.PageHelper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Resource
	private SpecificationMapper specificationMapper;

	@Resource
	private SpecificationOptionMapper specificationOptionMapper;


	@Override
	public List<Map> selectOptionList() {
		return specificationMapper.selectOptionList();
	}
	/**
	 * 查询全部
	 */
	@Override
	public List<Specification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<Specification> page=   (Page<Specification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		specificationMapper.insert(specification);
		List<SpecificationOption> options = specification.getSpecificationOptionList();
		for (int i = 0; i < options.size(); i++) {
			SpecificationOption option = options.get(i);
			option.setSpecId(specification.getId());
			specificationOptionMapper.insert(options.get(i));

		}
	}


	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		//修改规格
		specificationMapper.updateByPrimaryKey(specification);
		//删掉
		specificationOptionMapper.deleteSpecificationOption(specification.getId());
		//重新保存
		List<SpecificationOption> options = specification.getSpecificationOptionList();
		for (int i = 0; i < options.size(); i++) {
			SpecificationOption option = options.get(i);
			option.setSpecId(specification.getId());
			specificationOptionMapper.insert(options.get(i));

		}
	}

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		List<SpecificationOption> specificationOptionsList = specificationOptionMapper.findSpecificationOption(id);
		Specification specification = specificationMapper.selectByPrimaryKey(id);
		specification.setSpecificationOptionList(specificationOptionsList);
		return specification;

	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			specificationOptionMapper.deleteSpecificationOption(id);
		}
	}


	@Override
	public PageResult findPage(Specification specification, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);

		SpecificationExample example=new SpecificationExample();
		SpecificationExample.Criteria criteria = example.createCriteria();

		if(specification!=null){
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}

		}

		Page<Specification> page= (Page<Specification>)specificationMapper.selectByExample(example);
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
