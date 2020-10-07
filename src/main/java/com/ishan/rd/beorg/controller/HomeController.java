package com.ishan.rd.beorg.controller;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    /*@GetMapping(value = "/")
    @ResponseBody
    public String home(HttpServletRequest request, HttpServletResponse response, final Authentication authentication) throws IOException {

        if (authentication!= null && authentication instanceof TestingAuthenticationToken) {
            TestingAuthenticationToken token = (TestingAuthenticationToken) authentication;

            DecodedJWT jwt = JWT.decode(token.getCredentials().toString());
            String email = jwt.getClaims().get("email").asString();
            response.sendRedirect("http://localhost:8000");
            return "Welcome, " + email + "!";
        } else {
            response.sendRedirect("http://localhost:8080/login");
            return null;
        }
    }*/

}
