package com.bootdo.oa.domain;

public class BaseDO {

    private static final long serialVersionUID = 1L;

    private Long createBy;//创建者用户id

    private String createTime;//创建时间

    private Long updateBy;//修改者用户id

    private String updateTime;//修改时间

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
