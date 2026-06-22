package com.nanxinda.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//NOTE springMvc绑定对象参数时，依赖JavaBean的get和set方法
// 只有type和sender有get和set属性，这两个属性才能正常绑定
@NoArgsConstructor
@AllArgsConstructor
public class SendDTO {
    public String type;
    public String sender;
}
