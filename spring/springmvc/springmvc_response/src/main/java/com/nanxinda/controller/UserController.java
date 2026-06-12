package com.nanxinda.controller;

import com.nanxinda.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    //相应页面/跳转页面(返回页面)
    @RequestMapping("/toJumpPage")
    public String toJumpPage(){
        System.out.println("跳转页面");
        return "page.jsp";
    }

    //响应文本数据(实际前面案例都是返回字符串)
    @RequestMapping("/toText")
    @ResponseBody
    /// 作用：控制当前控制器返回值为响应体
    public String toText(){
        System.out.println("返回纯文本数据");
        return "response.text";
    }

    //相应POJO对象(对象转Json)
    @RequestMapping("/toJsonPOJO")
    @ResponseBody
    public User toJsonPOJO(){
        System.out.println("返回json对象数据");
        User user = new User();
        user.setName("pangshaoxiang");
        user.setAge(19);
        return user;
    }

    @RequestMapping("/toJsonList")
    @ResponseBody
    public List<User> toJsonList() {
        User user1 = new User();
        user1.setName("赵云");
        user1.setAge(41);
        User user2 = new User();
        user2.setName("master 赵云");
        user2.setAge(40);
        List<User> userList = new ArrayList<User>();
        userList.add(user1);
        userList.add(user2);
        return userList;
    }
    //public interface HttpMessageConverter<T> {
    //    boolean canRead(Class<?> var1, @Nullable MediaType var2);
    //
    //    boolean canWrite(Class<?> var1, @Nullable MediaType var2);
    //
    //    List<MediaType> getSupportedMediaTypes();
    //
    //    T read(Class<? extends T> var1, HttpInputMessage var2) throws IOException, HttpMessageNotReadableException;
    //
    //    void write(T var1, @Nullable MediaType var2, HttpOutputMessage var3) throws IOException, HttpMessageNotWritableException;
    //}
    /// ResponseBody是通过HttpMessageConverter这个接口进行类型转化

}
