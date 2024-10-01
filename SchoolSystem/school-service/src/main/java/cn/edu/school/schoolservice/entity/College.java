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
 * 学院表
 * </p>
 *
 * @author n70huihui
 * @since 2024-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("College")
public class College implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学院编号
     */
    @TableId(value = "id", type = IdType.NONE)
    private Integer id;

    /**
     * 学院名称
     */
    private String name;

    /**
     * 创立时间
     */
    private LocalDateTime createTime;


}
