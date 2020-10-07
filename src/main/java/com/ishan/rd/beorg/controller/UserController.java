package com.ishan.rd.beorg.controller;

import com.ishan.rd.beorg.domain.dto.UserSummary;
import com.ishan.rd.beorg.service.ApiService;
import com.ishan.rd.beorg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserSummary> me() {
        return ResponseEntity.ok(userService.getUserProfile());
    }

    @Autowired
    private ApiService apiService;

    //@Autowired
    //private SecurityConfiguration config;

    /*@GetMapping(value="/users")
    @ResponseBody
    public ResponseEntity<String> users(HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<String> result = apiService.getCall(config.getUsersUrl());
        return result;
    }

    @GetMapping(value = "/userByEmail")
    @ResponseBody
    public ResponseEntity<String> userByEmail(HttpServletResponse response, @RequestParam String email) {
        ResponseEntity<String> result = apiService.getCall(config.getUsersByEmailUrl()+email);
        return result;
    }*/

    /*@GetMapping(value = "/createUser")
    @ResponseBody
    public ResponseEntity<String> createUser(HttpServletResponse response) {
        JSONObject request = new JSONObject();
        request.put("email", "norman.lewis@email.com");
        request.put("given_name", "Norman");
        request.put("family_name", "Lewis");
        request.put("connection", "Username-Password-Authentication");
        request.put("password", "Pa33w0rd");

        ResponseEntity<String> result = apiService.postCall(config.getUsersUrl(), request.toString());
        return result;
    }*/
}
