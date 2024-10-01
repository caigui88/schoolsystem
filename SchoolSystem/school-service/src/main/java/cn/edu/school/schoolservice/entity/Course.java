package cn.edu.school.schoolservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课程表
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程编号
     */
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 开课学期
     */
    private String semester;

    /**
     * 专业
     */
    private Integer professionId;

    /**
     * 学院
     */
    private Integer collegeId;

    /**
     * 课程类别(必修/选修)
     */
    private String category;

    /**
     * 课程性质(学类核心、专业核心、通识必修、通识选修、其他)
     */
    private String property;

    /**
     * 考核方式
     */
    private String assessment;

    /**
     * 学分
     */
    private Integer credit;


}
