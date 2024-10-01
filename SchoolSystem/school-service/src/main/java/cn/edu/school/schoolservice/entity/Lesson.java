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
 * 授课表
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Lesson")
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 授课编号
     */
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    /**
     * 教师编号
     */
    private Integer teacherId;

    /**
     * 课程编号
     */
    private Integer courseId;


}
