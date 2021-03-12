package com.example.jwt.base;

import lombok.Data;

/**
 * @Author HeSuiJin
 * @Date 2021/3/12 12:05
 * @Description:
 */
@Data
public class PageModel {

    //@ApiModelProperty(value = "当前页", required = true)
    private Long page;

    //@ApiModelProperty(value = "每页条数", required = true)
    private Long size;

    private Long index;

    public PageModel(){
        this.page = 1L;
        this.size = 10L;
    }

    public Long getIndex() {
        if (null == this.index) {
            this.index = (null == page ? 0 : (page - 1) * this.size) +1 ;
        }
        return this.index++;
    }

    public void setIndex(Long index) {
        this.index = index;
    }
}
