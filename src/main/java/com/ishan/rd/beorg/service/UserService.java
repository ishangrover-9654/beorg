package com.ishan.rd.beorg.service;


import com.ishan.rd.beorg.domain.dto.LoginRequest;
import com.ishan.rd.beorg.domain.dto.LoginResponse;
import com.ishan.rd.beorg.domain.dto.UserSummary;
import com.ishan.rd.beorg.domain.entities.User;
import org.springframework.http.ResponseEntity;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:54, Pzt
 **/
public interface UserService {
    ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken, String refreshToken);

    ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken);

    UserSummary getUserProfile();

    User registerNewUserAccount(LoginRequest loginRequest);
}
