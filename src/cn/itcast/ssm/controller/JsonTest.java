package cn.itcast.ssm.controller;

import cn.itcast.ssm.po.ItemsCustom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tym on 17-7-22.
 */
@Controller
public class JsonTest {

    @RequestMapping("/requestJson")
    public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom){

        return itemsCustom;
    }

    @RequestMapping("/responseJson")
    public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom){

        return itemsCustom;
    }
}
