package com.cya.util.vo;

import com.cya.entity.Users;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Description 用户vo类
 * @Date 2022/9/4 16:35
 *
 */
@Data
public class UserOut extends Users {

    @ApiModelProperty("身份")
    private String ident;

    @ApiModelProperty("生日：yyyy-MM-dd格式")
    private String birth;
}
