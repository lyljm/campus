package xhu.click.db.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageResult {
    /**
     * 页数
     */
    Long total;
    /**
     * 当前页数
     */
    int current;
    /**
     * 每页大小
     */
    int pageSize;
    /**
     * 数据
     */
    List list;
}
