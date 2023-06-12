package com.siuuuu.establers.dto;

import lombok.Getter;
import lombok.Setter;

// 회원가입
@Getter
@Setter
public class AddUserRequest {
    private String email;
    private String password;

}
