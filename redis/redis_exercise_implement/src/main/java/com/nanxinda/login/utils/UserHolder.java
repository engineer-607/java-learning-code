package com.nanxinda.login.utils;

import com.nanxinda.login.dto.UserDTO;
//NOTE 存储当前用户信息的UserDTO对象如果使用传参获取或者接口重复编写从redis获取
// 较为麻烦，可以将存储用户信息的对象和当前线程绑定，线程有一个属性ThreadLocal.ThreadLocalMap threadLocals;
// 而threadLocals对象的Entry[] table属性存储着全项目共有的tl，以及每个线程专有的对象UserDTO
public class UserHolder {
    private static final ThreadLocal<UserDTO> tl = new ThreadLocal<>();
    //NOTE 调用get方法本质是在当前线程的threadLocals中，拿着tl获取存进去的UserDTO对象
    public static UserDTO getUser(){
        return tl.get();
    }

    public static void saveUser(UserDTO userDTO){
        tl.set(userDTO);
    }
    //NOTE 当客户端请求某个接口时，Tomcat会从线程池中分配一个线程来处理当前请求，而这个线程并不是
    // 都是新创建的，有可能是旧线程的复用，所以每次线程结束时拦截器必须对UserDTO信息进行清除，避免
    // 下次分配线程时还残留着线程执行上个任务的信息
    public static void remove(){
        tl.remove();
    }
}
