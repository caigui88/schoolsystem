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
 * 教师表
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教师编号
     */
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    /**
     * 教师名称
     */
    private String name;

    /**
     * 学院
     */
    private Integer collegeId;

    /**
     * 专业
     */
    private Integer professionId;

    /**
     * 职称
     */
    private String title;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 职称级别
     */
    private String level;

    /**
     * 入职时间
     */
    private LocalDateTime hiredate;


}
