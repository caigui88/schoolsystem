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
 * 班级表
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Klass")
public class Klass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 班级编号
     */
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    /**
     * 班级名称
     */
    private String name;

    /**
     * 学生人数
     */
    private Integer studentNumber;

    /**
     * 所属专业编号
     */
    private Integer professionId;

    /**
     * 所属学院编号
     */
    private Integer collegeId;

    /**
     * 班主任编号
     */
    private Integer teacherId;


}
