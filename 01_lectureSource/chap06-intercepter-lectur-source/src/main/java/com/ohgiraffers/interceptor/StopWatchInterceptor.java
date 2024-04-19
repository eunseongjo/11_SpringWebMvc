package com.ohgiraffers.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class StopWatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;
    public StopWatchInterceptor(MenuService menuService){
        this.menuService = menuService;
    }
    /*전처리 메소드*/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandler 호출함...");

        long startTime = System.currentTimeMillis();

        request.setAttribute("startTime", startTime);

        //true일때만 컨트롤러를 이어서 호출한다. false이면 핸들러 메소드를 호출하지 않는다.
        return true;
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    /*후처리 메소드*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler 호출함...");

        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        request.removeAttribute("startTime");

        modelAndView.addObject("interval", endTime-startTime);
    }

    /* 마지막에 호출하는 메소드 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion 호출함...");

        menuService.method();
    }
}
