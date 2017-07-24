package cn.itcast.ssm.controller;

import cn.itcast.ssm.controller.validation.ValidGroup1;
import cn.itcast.ssm.exception.CustomException;
import cn.itcast.ssm.po.Items;
import cn.itcast.ssm.po.ItemsCustom;
import cn.itcast.ssm.po.ItemsQueryVo;
import cn.itcast.ssm.service.ItemsService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by tym on 17-7-19.
 * 商品的controller
 */
//@Controller说明它是控制器类

@Controller
//为了对url进行分类管理 ，可以在这里定义根路径，最终访问url是根路径+子路径
//比如：商品列表：/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    //商品查询
    @RequestMapping("/queryItems")
    public ModelAndView queryItems(ItemsQueryVo itemsQueryVo) throws Exception {
        //测试forward后request是否可以共享
//        System.out.println(request.getParameter("id"));

        //调用service查找 数据库，查询商品列表
        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

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

    //商品信息修改显示
//    @RequestMapping("/editItems")
    //限制http请求的方法,可以post,get
//    @RequestMapping(value="/editItems",method = {RequestMethod.GET,RequestMethod.POST})
//    public ModelAndView editItems()throws Exception{
//
//        ItemsCustom itemsCustom = itemsService.findItemsById(1);
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.addObject("itemsCustom",itemsCustom);
//        modelAndView.setViewName("items/editItems");
//        return modelAndView;
//    }


    @RequestMapping(value="/editItems",method = {RequestMethod.GET,RequestMethod.POST})
    public String editItems(Model model,Integer id)throws Exception{

        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        //判断商品是否为空，为空抛出异常，提醒用户商品不存在
        if(itemsCustom==null){
            throw new CustomException("修改的商品信息不存在!");
        }

        //通过形参中的model将model数据传到页面
        //相当于modelAndView.addObject方法
        model.addAttribute("itemsCustom",itemsCustom);
        return "items/editItems";
    }

    //查询商品信息,输出json
    //"/itemsView/{id}"中的{id}表示将这个位置的数据传到@PathVariable指定的名称中
    @RequestMapping("/itemsView/{id}")
    public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception{

        ItemsCustom itemsCustom = itemsService.findItemsById(id);

        return itemsCustom;
    }

    //商品信息修改提交
//    @RequestMapping("/editItemsSubmit")
//    public ModelAndView editItemsSubmit()throws Exception{
//
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.setViewName("success");
//        return modelAndView;
//    }


    // 在需要校验的pojo前边添加@Validated，在需要校验的pojo后边添加BindingResult
    // bindingResult接收校验出错信息
    // 注意：@Validated和BindingResult bindingResult是配对出现，并且形参顺序是固定的（一前一后）。
    //@Validated(value = {ValidGroup1.class})指定使用ValidGroup1分组的校验
    @RequestMapping("/editItemsSubmit")
    public String editItemsSubmit(Model model, HttpServletRequest request, Integer id,
                                  @Validated(value = {ValidGroup1.class}) ItemsCustom itemsCustom, BindingResult bindingResult,
                                    //接收商品图片
                                    MultipartFile items_pic)throws Exception{

        //获取校验错误信息
        if(bindingResult.hasErrors()){
            //输出错误信息
           List<ObjectError> allErrors =  bindingResult.getAllErrors();
           for(ObjectError objectError:allErrors){
               //输出错误信息
               System.out.println(objectError.getDefaultMessage());
           }
           //将错误信息传到页面
            model.addAttribute("allErrors",allErrors);
            return "items/editItems";
        }

        //原始名称
        String originalFilename = items_pic.getOriginalFilename();
        //上传图片
        if(items_pic!=null && originalFilename!=null && originalFilename.length()>0){

            //存储图片的物理路径
            String pic_path = "/home/tym/桌面/tomcat图片/";


            //新的图片名称
            String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //新图片
            File newFile = new File(pic_path+newFileName);

            //将内存中的数据写入磁盘
            items_pic.transferTo(newFile);

            //将新图片名称写到itemsCustom中
            itemsCustom.setPic(newFileName);

        }
        itemsService.updateItems(id,itemsCustom);
        //重定向到商品的查询列表
//        return "redirect:queryItems.action";
        //页面转发
//        return "forward:queryItems.action";
        return "success";
    }

    //批量删除商品信息
    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] items_id)throws Exception{


        return "success";
    }

    //批量修改商品页面
    @RequestMapping("/editItemsQuery")
    public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo)throws Exception{

        List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        modelAndView.setViewName("items/editItemsQuery");

        return modelAndView;
    }
    //批量修改商品提交
    @RequestMapping("/editItemsAllSubmit")
    public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo)throws Exception{

        return "success";
    }
}
