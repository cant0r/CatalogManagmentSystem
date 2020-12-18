package com.cant0r.cms.controllers;


import com.cant0r.cms.security.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
    @PostMapping
    public boolean login(@RequestBody User user) {
        return (user.getUsername().equals("cms") && user.getPassword().equals("cms")) ||
                (user.getUsername().equals("admin") && user.getPassword().equals("admin"));
    }
}

