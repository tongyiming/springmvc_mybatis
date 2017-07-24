package cn.itcast.ssm.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tym on 17-7-20.
 */
//日期转换器
public class CustomDateConverter implements Converter<String, Date>{

    @Override
    public Date convert(String s) {

        //将日期串转成日期类型(格式是:yyyy-MM-dd HH:mm:ss)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            //转换成功返回
            return simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //参数绑定失败反悔null
        return null;
    }
}
