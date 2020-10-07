package com.ishan.rd.beorg.service;


import com.arangodb.springframework.core.ArangoOperations;
import com.ishan.rd.beorg.domain.dto.LoginRequest;
import com.ishan.rd.beorg.domain.dto.LoginResponse;
import com.ishan.rd.beorg.domain.dto.Token;
import com.ishan.rd.beorg.domain.dto.UserSummary;
import com.ishan.rd.beorg.domain.entities.Role;
import com.ishan.rd.beorg.domain.entities.RoleEnum;
import com.ishan.rd.beorg.domain.entities.User;
import com.ishan.rd.beorg.repository.RoleRepository;
import com.ishan.rd.beorg.repository.UserRepository;
import com.ishan.rd.beorg.security.CookieUtil;
import com.ishan.rd.beorg.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:55, Pzt
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CookieUtil cookieUtil;



    @Override
    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest, String accessToken, String refreshToken) {
        HttpHeaders responseHeaders;
        try{
            String email = loginRequest.getEmail();
            User user = userRepository.findByUsername(email).orElseThrow(() -> new IllegalArgumentException("User not found with email " + email));

            Boolean accessTokenValid = tokenProvider.validateToken(accessToken);
            Boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);

            responseHeaders = new HttpHeaders();
            Token newAccessToken;
            Token newRefreshToken;
            if (!accessTokenValid && !refreshTokenValid) {
                newAccessToken = tokenProvider.generateAccessToken(user.getUsername());
                newRefreshToken = tokenProvider.generateRefreshToken(user.getUsername());
                addAccessTokenCookie(responseHeaders, newAccessToken);
                addRefreshTokenCookie(responseHeaders, newRefreshToken);
            }

            if (!accessTokenValid && refreshTokenValid) {
                newAccessToken = tokenProvider.generateAccessToken(user.getUsername());
                addAccessTokenCookie(responseHeaders, newAccessToken);
            }

            if (accessTokenValid && refreshTokenValid) {
                newAccessToken = tokenProvider.generateAccessToken(user.getUsername());
                newRefreshToken = tokenProvider.generateRefreshToken(user.getUsername());
                addAccessTokenCookie(responseHeaders, newAccessToken);
                addRefreshTokenCookie(responseHeaders, newRefreshToken);
            }

            LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");
            return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
        }catch (Exception e){
            LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.FAILURE, "Auth Failed.");
            return ResponseEntity.ok().body(loginResponse);
        }
    }

    @Override
    public ResponseEntity<LoginResponse> refresh(String accessToken, String refreshToken) {
        Boolean refreshTokenValid = tokenProvider.validateToken(refreshToken);
        if (!refreshTokenValid) {
            throw new IllegalArgumentException("Refresh Token is invalid!");
        }

        String currentUserEmail = tokenProvider.getUsernameFromToken(accessToken);

        Token newAccessToken = tokenProvider.generateAccessToken(currentUserEmail);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(newAccessToken.getTokenValue(), newAccessToken.getDuration()).toString());

        LoginResponse loginResponse = new LoginResponse(LoginResponse.SuccessFailure.SUCCESS, "Auth successful. Tokens are created in cookie.");
        return ResponseEntity.ok().headers(responseHeaders).body(loginResponse);
    }

    @Override
    public UserSummary getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails customUserDetails = (UserDetails) authentication.getPrincipal();

        User user = userRepository.findByEmail(customUserDetails.getUsername()).orElseThrow(() -> new IllegalArgumentException("User not found with email " + customUserDetails.getUsername()));
        return user.toUserSummary();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Override
    public User registerNewUserAccount(LoginRequest loginRequest) {
        User user = new User();
        user.setEmail(loginRequest.getEmail());
        user.setUsername(loginRequest.getEmail());
        Role role = roleRepository.findByName(RoleEnum.USER).get();
        user.setRoles(new HashSet<>(Arrays.asList(role)));

        user.setPassword(passwordEncoder.encode(loginRequest.getPassword()));

        return userRepository.save(user);
    }

    private void addAccessTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createAccessTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

    private void addRefreshTokenCookie(HttpHeaders httpHeaders, Token token) {
        httpHeaders.add(HttpHeaders.SET_COOKIE, cookieUtil.createRefreshTokenCookie(token.getTokenValue(), token.getDuration()).toString());
    }

}
