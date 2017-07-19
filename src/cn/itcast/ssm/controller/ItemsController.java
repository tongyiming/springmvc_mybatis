package cn.itcast.ssm.controller;

import cn.itcast.ssm.po.Items;
import cn.itcast.ssm.po.ItemsCustom;
import cn.itcast.ssm.service.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tym on 17-7-19.
 * 商品的controller
 */
//@Controller说明它是控制器类

@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    //商品查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws Exception {

        //调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(null);

        //返回ModelAndView
        ModelAndView modelAndView =  new ModelAndView();
        //相当 于request的setAttribut，在jsp页面中通过itemsList取数据
        modelAndView.addObject("itemsList", itemsList);

        //指定视图
        //modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
        //如果在视图解析器中配置了jsp路径的前缀和jsp路径的后缀,则可以修改为:
        modelAndView.setViewName("items/itemsList");
        return modelAndView;
    }
}
