package com.yj.domain.vo.category;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Package com.yj.domain.vo
 * @Author yJade
 * @Date 2023-03-07 20:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryListVo {
    private Long id;
    /**分类名*/
    private String name;
    /**描述*/
    private String description;
    /**状态0:正常,1禁用*/
    private String status;

}
