package cn.golaxy.zstp.projecttemplatejava.filter;

import cn.golaxy.zstp.projecttemplatejava.domain.ResultObject;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：yaojianlin(yaojianlin@golaxy.cn)
 * @date ：Created in 2021/11/25 13:20
 * @description：
 */
@Component
@Slf4j
public class HeaderInterceptor implements HandlerInterceptor {

    private static List<String> IGNORE_PATH = Arrays.asList(
            "/doc.html"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String strValue = request.getServletPath();
        if(IGNORE_PATH.contains(strValue)
        ) {
            return true;
        }
        String userId = request.getHeader("userId");
        if (userId == null) {
            getResultObject(40001, "请在header中增加userId", response);
            return false;
        } else if (userId.equals("")) {
            getResultObject(40002, "请输入正确的userId", response);
            return false;
        } else {
            return true;
        }
    }

    private void getResultObject(Integer code, String data, HttpServletResponse response) {
        ResultObject resultObject = new ResultObject();
        resultObject.setCode(code);
        resultObject.setData(data);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.print(JSONObject.toJSONString(resultObject, SerializerFeature.DisableCircularReferenceDetect));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理请求完成后视图渲染之前的处理操作
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 视图渲染之后的操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
