package cn.edu.school.schoolcommon.constant;

/**
 * BeanValidation需要使用到的常量
 */
public class ValidationConstant {

    public static final String NAME_REGEX = "[\\u4e00-\\u9fa5\\w]{1,10}";
    public static final String NAME_REGEX_ERROR = "名称需任意字符1-10位";

    public static final String PASSWORD_REGEX = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,10}";
    public static final String PASSWORD_REGEX_ERROR = "密码需6-10位，至少包含大小写字母和数字";

}
