package com.nanxinda.domain.query;

import com.nanxinda.domain.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends User {
    private Integer age2;

}
