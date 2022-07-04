package com.atguigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

// common returning format
@Data
public class R {
  @ApiModelProperty(value = "success or not")
  private Boolean success;

  @ApiModelProperty(value = "returning code")
  private Integer code;

  @ApiModelProperty(value = "returning message")
  private String message;

  @ApiModelProperty(value = "returning data")
  private Map<String, Object> data = new HashMap<String, Object>();

  // private constructor
  private R() {}

  // successful static method to return a new R
  public static R ok() {
    R r = new R();
    r.setSuccess(true);
    r.setCode(ResultCode.SUCCESS);
    r.setMessage("Success");
    return r;
  }

  // failed static method to return a new R
  public static R error() {
    R r = new R();
    r.setSuccess(false);
    r.setCode(ResultCode.ERROR);
    r.setMessage("Error");
    return r;
  }

  // Builder (as a chain)
  public R success(Boolean success){
    this.setSuccess(success);
    return this;
  }

  public R message(String message){
    this.setMessage(message);
    return this;
  }

  public R code(Integer code){
    this.setCode(code);
    return this;
  }

  public R data(String key, Object value){
    this.data.put(key, value);
    return this;
  }

  public R data(Map<String, Object> map){
    this.setData(map);
    return this;
  }
}
