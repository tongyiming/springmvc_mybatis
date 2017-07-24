package cn.itcast.ssm.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by tym on 17-7-22.
 */
//登陆认证的拦截器
public class LoginInterceptor implements HandlerInterceptor{

    //进入Handler方法之前执行
    //用于身份认证,身份授权
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o) throws Exception {

        //获取请求的URL
        String url = httpServletRequest.getRequestURI();

        if(url.indexOf("login.action")>=0){
            return true;
        }
        //判断session
        HttpSession session = httpServletRequest.getSession();
        String username = (String)session.getAttribute("username");

        if(username!=null){
            return true;
        }
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(httpServletRequest,httpServletResponse);

        return false;
    }

    //进入Handler方法之后,返回ModelAndView之前执行
    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("HandlerInterceptor1...postHandle");
    }

//    执行Handler完成执行此方法
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("HandlerInterceptor1...afterCompletion");
    }
}
