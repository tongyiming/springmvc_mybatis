package cn.itcast.ssm.service.impl;

import cn.itcast.ssm.mapper.ItemsMapper;
import cn.itcast.ssm.mapper.ItemsMapperCustom;
import cn.itcast.ssm.po.Items;
import cn.itcast.ssm.po.ItemsCustom;
import cn.itcast.ssm.po.ItemsQueryVo;
import cn.itcast.ssm.service.ItemsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by tym on 17-7-19.
 * 商品管理
 */
public class ItemsServiceImpl implements ItemsService{

    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
        //通过ItemsMapperCustom查询数据库

        return itemsMapperCustom.findItemsList(itemsQueryVo);
    }

    @Override
    public ItemsCustom findItemsById(Integer id) throws Exception {

        Items items = itemsMapper.selectByPrimaryKey(id);

//        ItemsCustom itemsCustom = new ItemsCustom();
//        //将items的内容拷贝到itemsCustom
//        BeanUtils.copyProperties(items,itemsCustom);

        ItemsCustom itemsCustom = null;
        if(items!=null){
            itemsCustom=new ItemsCustom();
            BeanUtils.copyProperties(items,itemsCustom);
        }

        return itemsCustom;
    }

    @Override
    public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {

        //对关键参数进行校验

        //更新商品信息
        //更新商品信息使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段，包括 大文本类型字段
        //updateByPrimaryKeyWithBLOBs要求必须转入id
        itemsCustom.setId(id);
        itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
    }
}
