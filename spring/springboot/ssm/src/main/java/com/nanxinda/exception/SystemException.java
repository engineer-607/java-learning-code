package com.nanxinda.exception;
/// 项目异常分类
/// 1.业务异常(BusinessException)
///   *规范的用户行为产生的异常
///   *不规范的用户行为操作产生的异常
/// 2.系统异常(SystemException)
///   *项目运行过程中可预计且无法避免的异常
/// 3.其他异常(Exception)
///   *编程人员未预期的异常
public class SystemException extends RuntimeException{
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public SystemException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public SystemException(Integer code,String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

}
