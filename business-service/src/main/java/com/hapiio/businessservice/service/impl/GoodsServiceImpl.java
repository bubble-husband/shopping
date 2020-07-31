package com.hapiio.businessservice.service.impl;

import com.github.pagehelper.Page;
import com.hapiio.businessservice.mapper.GoodsDescMapper;
import com.hapiio.businessservice.mapper.GoodsMapper;
import com.hapiio.businessservice.mapper.ItemMapper;
import com.hapiio.businessservice.service.GoodsService;
import com.hapiio.commons.common.PageResult;
import com.hapiio.commons.common.Result;
import com.hapiio.pojo.model.ExtendGoods;
import com.hapiio.pojo.model.Goods;
import com.hapiio.pojo.model.GoodsExample;

import com.github.pagehelper.PageHelper;
import com.hapiio.pojo.model.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private GoodsDescMapper goodsDescMapper;

    @Resource
    private ItemMapper itemMapper;


    /**
     * 查询全部
     */
    @Override
    public List<Goods> findAll() {
        return goodsMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Goods> page = (Page<Goods>) goodsMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Transactional
    @Override
    public void add(ExtendGoods goods) {
        //同时对三个表进行操作，要么都成功，要么都不成功
        goodsMapper.insert(goods.getGoods());

        goodsDescMapper.insert(goods.getGoodsDesc());

        List<Item> itemList = goods.getItems();
        //100,连接100次
        for (int i = 0; i < itemList.size(); i++) {
            itemMapper.insert(itemList.get(i));
        }

    }


    /**
     * 修改
     */
    @Override
    public void update(Goods goods) {
        goodsMapper.updateByPrimaryKey(goods);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Goods findOne(Long id) {
        return goodsMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            goodsMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult search(Goods goods, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        GoodsExample example = new GoodsExample();
        GoodsExample.Criteria criteria = example.createCriteria();
        Page<Goods> page = (Page<Goods>) goodsMapper.selectByExample(example);
        List<Goods> Goods = page.getResult();
        Page<ExtendGoods> extendGoodsPage = new Page<ExtendGoods>();
        List<ExtendGoods> extendGoods = new ArrayList<>();

        for (int i = 0; i < Goods.size(); i++) {
            ExtendGoods goods1 = new ExtendGoods();
            goods1.setGoods(Goods.get(i));
            extendGoods.add(goods1);
        }


        PageResult pageResult = new PageResult(page.getTotal(),extendGoods);
        return pageResult;
    }

}
