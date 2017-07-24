package cn.itcast.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tym on 17-7-22.
 */
//测试拦截器1
public class HandlerInterceptor2 implements HandlerInterceptor{

    //进入Handler方法之前执行
    //用于身份认证,身份授权
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("HandlerInterceptor2...preHandle");
        return true;
    }

    //进入Handler方法之后,返回ModelAndView之前执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("HandlerInterceptor2...postHandle");
    }

//    执行Handler完成执行此方法
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("HandlerInterceptor2...afterCompletion");
    }
}
