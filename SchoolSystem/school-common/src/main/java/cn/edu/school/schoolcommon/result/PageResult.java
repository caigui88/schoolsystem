package cn.edu.school.schoolcommon.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页数据封装
 * @param <T> 分页数据泛型
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult <T> {

    /**
     * 总记录条数
     */
    private Integer total;

    /**
     * 分页数据
     */
    private List<T> records;

}
