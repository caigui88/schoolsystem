package cn.edu.school.schoolcommon.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应数据类，用于封装响应数据
 * @param <T> 响应数据泛型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 业务状态码，1-成功，0-失败
     */
    private Integer code;

    /**
     * 响应数据描述信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 快速返回操作成功响应结果（带响应数据）
     * @param data 响应数据
     * @return 响应数据的封装
     * @param <E> 响应数据泛型
     */
    public static <E> Result<E> success(E data) {
        return new Result<>(1, "ok", data);
    }

    /**
     * 快速返回操作成功响应结果
     * @return data为null的响应结果
     */
    public static Result<String> success() {
        return new Result<>(1, "ok", null);
    }

    /**
     * 快速返回操作失败响应结果
     * @param message 失败信息
     * @return 响应数据的封装
     */
    public static Result<String> error(String message) {
        return new Result<>(0, message, null);
    }
}