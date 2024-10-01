package cn.edu.school.schoolcommon.utils;

import java.io.IOException;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Web工具类，用于处理SpringSecurity的失败信息
 */
public class WebUtil {

    /**
     * 将字符串渲染到客户端
     * @param response 渲染对象
     * @param string   待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response,
                                      String string, Integer status) {
        try {
            response.setStatus(status);    // 用户认证授权失败
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}