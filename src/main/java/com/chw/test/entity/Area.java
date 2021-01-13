package com.chw.test.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 地区表
 * </p>
 *
 * @author ChenWei
 * @since 2021-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Area extends Model<Area> {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 下级数量
     */
    private Integer childCount;

    /**
     * 父级ID
     */
    private String parentId;

    /**
     * 完成名称
     */
    private String fullName;

    /**
     * 级别
     */
    private Integer level;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    private String lng;

    private String lat;

    private String position;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
