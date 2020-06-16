package com.atguigu.demo;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.util.List;
import java.util.Map;

public class MyInterceptor implements Interceptor {
    /**
     * 初始化
     */
    public void initialize() {

    }

    public Event intercept(Event event) {
        byte[] body = event.getBody();
        if((body[0] >= 'A' && body[0] <= 'Z') || (body[0] >= 'a' && body[0] <= 'z')){
            Map<String, String> headers = event.getHeaders();

            headers.put("type","letter");
        }else if(body[0] >= '0' && body[0] <= '9'){
            event.getHeaders().put("type","number");
        }
        return event;
    }

    public List<Event> intercept(List<Event> list) {
        for (Event event : list
             ) {
            intercept(event);
        }
        return list;
    }

    public void close() {

    }

    /**
     * 内部类（静态内部类）
     * 作用 ：返回MyInterceptor的实例
     * 注意 ：1.静态内部类  2.权限要是public
     */
    public static class Builder implements Interceptor.Builder{

        // 必须使用静态内部类来自动调用拦截器
        public Interceptor build() {
            return new MyInterceptor();
        }

        public void configure(Context context) {

        }
    }

    // dev分支 111111111111111111111111

    //  dev 22222222222222222222222222333333333333333333333333333

}
