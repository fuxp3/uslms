package com.cya.util.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BookPageIn {

    private String name;
    private String isbn;
    private String publish;
    private String type;

    private String number;

    /** 当前页 */
    @ApiModelProperty("当前页")
    private Integer currPage;

    /** 当前页条数 */
    @ApiModelProperty("每页数量")
    private Integer pageSize;

}
