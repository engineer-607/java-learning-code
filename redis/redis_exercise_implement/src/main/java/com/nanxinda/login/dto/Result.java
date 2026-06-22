package com.nanxinda.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result {
    // 请求是否成功
    private Boolean success;
    // 表示错误
    private String errorMsg;
    // 给前端返回真正的数据
    private Object data;
    // 分页查询用（总条数）
    private Long total;

    public static Result ok(){
        return new Result(true,null,null,null);
    }

    public static Result ok(Object data){
        return new Result(true,null,data,null);
    }

    public static Result ok(List<?> data,Long total){
        return new Result(true,null,data,total);
    }

    public static Result fail(String errorMsg){
        return new Result(false,errorMsg,null,null);
    }




}
