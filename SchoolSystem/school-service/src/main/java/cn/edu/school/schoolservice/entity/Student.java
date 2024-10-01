package cn.edu.school.schoolservice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生编号
     */
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生密码
     */
    private String password;

    /**
     * 班级编号
     */
    private Integer klassId;

    /**
     * 专业编号
     */
    private Integer professionId;

    /**
     * 学院编号
     */
    private Integer collegeId;

    /**
     * 入学时间
     */
    private LocalDateTime entryTime;


}
