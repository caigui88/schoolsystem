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
 * 成绩表
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Score")
public class Score implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成绩编号
     */
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    /**
     * 学生编号
     */
    private Integer studentId;

    /**
     * 授课编号
     */
    private Integer lessonId;

    /**
     * 课程成绩
     */
    private Integer score;


}
