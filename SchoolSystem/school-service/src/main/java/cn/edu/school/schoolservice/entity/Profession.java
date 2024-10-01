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
 * 专业表
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("Profession")
public class Profession implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专业编号
     */
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 学院
     */
    private Integer collegeId;

    /**
     * 创立时间
     */
    private LocalDateTime createTime;


}
