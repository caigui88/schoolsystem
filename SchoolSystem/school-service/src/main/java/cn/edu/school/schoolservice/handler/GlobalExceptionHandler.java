package cn.edu.school.schoolservice.handler;

import cn.edu.school.schoolcommon.result.Result;
import jakarta.validation.ConstraintViolationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理BeanValidation异常，用于@RequestBody校验失败的情况
     * @param e MethodArgumentNotValidException
     * @return 响应字段异常信息
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<String> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        StringBuilder builder = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fieldError ->
                    builder.append(fieldError.getField()).append(": ")
                            .append(fieldError.getDefaultMessage()).append(";\n")
            );
        }
        return Result.error(builder.toString());
    }

    /**
     * 处理BeanValidation异常，用于@RequestParam、@PathVariable校验失败的情况
     * @param ex ConstraintViolationException
     * @return 响应字段异常信息
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder builder = new StringBuilder();
        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            builder.append(fieldName).append(": ")
                    .append(errorMessage).append(";\n");
        });
        return Result.error(builder.toString());
    }

    /**
     * 处理BeanValidation异常，用于处理在表单或@RequestParam参数的绑定过程中，格式校验失败的场景。
     * @param e BindException
     * @return 响应字段异常信息
     */
    @ExceptionHandler(BindException.class)
    public Result<String> handleBindException(BindException e) {
        StringBuilder builder = new StringBuilder();
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                builder.append(fieldError.getField()).append(": ")
                        .append(fieldError.getDefaultMessage()).append(";\n")
        );
        return Result.error(builder.toString());
    }

    /**
     * 处理BeanValidation异常，用于处理当请求中的参数类型与方法参数的期望类型不匹配的情况
     * @param e MethodArgumentTypeMismatchException
     * @return 响应异常字段信息
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        StringBuilder builder = new StringBuilder();
        builder.append("参数: ").append(e.getName()).append(" 类型不匹配, 期待类型: ")
                .append(e.getRequiredType().getName()).append(", 实际输入: ")
                .append(e.getValue()).append(";\n");
        return Result.error(builder.toString());
    }

    /**
     * 捕获父级异常，兜底方案
     * @param e Exception
     * @return 响应异常字段信息
     */
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        return Result.error(e.getMessage());
    }
}
