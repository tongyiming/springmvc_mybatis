package cn.itcast.ssm.service;

import cn.itcast.ssm.po.Items;
import cn.itcast.ssm.po.ItemsCustom;
import cn.itcast.ssm.po.ItemsQueryVo;

import java.util.List;

/**
 * Created by tym on 17-7-19.
 * 商品管理service
 */
public interface ItemsService {

    //商品查询列表
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;

    //跟据id查询商品信息
    public ItemsCustom findItemsById(Integer id)throws Exception;

    //修改商品信息
    public void updateItems(Integer id, ItemsCustom itemsCustom)throws Exception;
}
