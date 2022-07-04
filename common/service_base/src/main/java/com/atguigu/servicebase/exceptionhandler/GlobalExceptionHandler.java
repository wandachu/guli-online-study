package com.atguigu.servicebase.exceptionhandler;

import com.atguigu.commonutils.ExceptionUtil;
import com.atguigu.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  // General Exception
  @ExceptionHandler(Exception.class) // the exception type
  @ResponseBody // so that we can return an object
  public R error(Exception e) {
    e.printStackTrace();
    return R.error().message("Handled the exception...");
  }

  // Specific Exception
  @ExceptionHandler(ArithmeticException.class) // the exception type
  @ResponseBody // so that we can return an object
  public R error(ArithmeticException e) {
    e.printStackTrace();
    return R.error().message("Handled ArithmeticException...");
  }

  // Customized Exception
  @ExceptionHandler(GuliException.class) // the exception type
  @ResponseBody // so that we can return an object
  public R error(GuliException e) {
//    log.error(e.getMessage()); // only write the error itself (very short)
    log.error(ExceptionUtil.getMessage(e)); // write the entire error stack trace
    e.printStackTrace();
    return R.error().code(e.getCode()).message(e.getMsg());
  }
}
