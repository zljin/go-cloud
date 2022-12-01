package io.leonard.go.consumer.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import io.leonard.go.common.pojo.CommonReturnType;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author leonard
 * @date 2022/12/1
 * @Description Sentinel 限流成功后的返回定于
 */
@Configuration
public class SentinelConfig  {
    public SentinelConfig() {
        WebCallbackManager.setUrlBlockHandler(new UrlBlockHandler() {
            @Override
            public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException ex) throws IOException {
                CommonReturnType returnType = CommonReturnType.create("sentinel limit");
                response.setContentType("application/json;charset=utf-8");
                response.getWriter().write(JSON.toJSONString(returnType));
            }
        });
    }
}
