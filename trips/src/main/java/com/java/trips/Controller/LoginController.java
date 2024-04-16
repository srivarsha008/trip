package com.java.trips.Controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.java.trips.Model.Login;
import com.java.trips.Model.Profile;
import com.java.trips.Model.Destination;
import com.java.trips.service.LoginService;
import com.java.trips.service.DestinationService;
import com.java.trips.service.ProfileService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginController {
    private final LoginService loginService;
    private final ProfileService profileService;
    private final DestinationService orderItemService;

    public LoginController(LoginService loginService, ProfileService profileService, DestinationService orderItemService) {
        this.loginService = loginService;
        this.profileService = profileService;
        this.orderItemService = orderItemService;
    }

    @PostMapping("/login")
    public ResponseEntity<Login> createUser(@RequestBody Login login) {
        Login createdLogin = loginService.createLogin(login);
        if (createdLogin != null) {
            return new ResponseEntity<>(createdLogin, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/login")
    public ResponseEntity<Page<Login>> getAllLogin(
            @RequestParam int pageNo,
            @RequestParam int pageSize,
            @RequestParam String sortBy) {
        Page<Login> logins = loginService.getAllLogin(pageNo, pageSize, sortBy);
        if (!logins.isEmpty()) {
            return new ResponseEntity<>(logins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/login/{loginId}")
    public ResponseEntity<Login> getLoginById(@PathVariable int loginId) {
        return loginService.getLoginById(loginId)
                .map(login -> new ResponseEntity<>(login, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/login/{loginId}")
    public ResponseEntity<Login> updateLogin(@PathVariable int loginId, @RequestBody Login login) {
        return loginService.getLoginById(loginId)
                .map(existingLogin -> {
                    login.setId(loginId);
                    Login updatedLogin = loginService.updateLogin(login);
                    return new ResponseEntity<>(updatedLogin, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/login/{loginId}")
    public ResponseEntity<Boolean> deleteLogin(@PathVariable int loginId) {
            boolean deleted =  loginService.deleteLogin(loginId);

            if (deleted) {
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        }
    
    // New endpoint to retrieve login details by name
    @GetMapping("/login/byName/{name}")
    public ResponseEntity<List<Login>> getLoginByName(@PathVariable String name) {
        List<Login> logins = loginService.getLoginByName(name);
        if (!logins.isEmpty()) {
            return new ResponseEntity<>(logins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // New endpoint to retrieve login details by username
    @GetMapping("/login/byUsername/{username}")
    public ResponseEntity<List<Login>> getLoginByUsername(@PathVariable String username) {
        List<Login> logins = loginService.getLoginByUsername(username);
        if (!logins.isEmpty()) {
            return new ResponseEntity<>(logins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // New endpoint to retrieve all login records
    @GetMapping("/login/all")
    public ResponseEntity<List<Login>> getAllLogins() {
        List<Login> logins = loginService.getAllLogins();
        if (!logins.isEmpty()) {
            return new ResponseEntity<>(logins, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to create profile for a login
    @PostMapping("/login/{loginId}/profile")
    public ResponseEntity<Profile> createProfileForLogin(@PathVariable int loginId, @RequestBody Profile profile) {
        Login login = loginService.getLoginById(loginId).orElse(null);
        if (login != null) {
            profile.setLogin(login);
            Profile createdProfile = profileService.createProfile(profile);
            return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to retrieve profile by login ID
    @GetMapping("/login/{loginId}/profile")
    public ResponseEntity<Profile> getProfileByLoginId(@PathVariable int loginId) {
        Profile profile = profileService.getProfileByLoginId(loginId);
        if (profile != null) {
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to create order for a login
    @PostMapping("/login/{loginId}/order")
    public ResponseEntity<Destination> createOrderForLogin(@PathVariable int loginId, @RequestBody Destination orderItem) {
        Login login = loginService.getLoginById(loginId).orElse(null);
        if (login != null) {
            orderItem.setLogin(login);
            Destination createdOrderItem = orderItemService.createOrderItem(orderItem);
            return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to retrieve orders by login ID
    @GetMapping("/login/{loginId}/orderItems")
    public ResponseEntity<List<Destination>> getOrderItemsByLoginId(@PathVariable int loginId) {
        List<Destination> noofDestinations = orderItemService.getOrderItemsByLoginId(loginId);
        if (!noofDestinations.isEmpty()) {
            return new ResponseEntity<>(noofDestinations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}