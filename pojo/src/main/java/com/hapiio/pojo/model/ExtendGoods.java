package com.hapiio.pojo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExtendGoods implements Serializable {

    private Goods goods;

    private GoodsDesc goodsDesc;

    private List<Item> items;

    public ExtendGoods() {

    }

    public ExtendGoods(Goods goods, GoodsDesc goodsDesc, List<Item> items) {
        this.goods = goods;
        this.goodsDesc = goodsDesc;
        this.items = items;
    }
}
