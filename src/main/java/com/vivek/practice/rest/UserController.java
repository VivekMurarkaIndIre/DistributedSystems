package com.vivek.practice.rest;

import com.vivek.practice.entity.AppUser;
import com.vivek.practice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
//@Api(tags = "users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    //private final ModelMapper modelMapper;

    @PostMapping("/signin")
    //@ApiOperation(value = "${UserController.signin}")
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 422, message = "Invalid username/password supplied")})
    public String login(//
                        @RequestParam String username, //
                        @RequestParam String password) {
        return userService.signin(username, password);
    }

    @PostMapping("/signup")
   // @ApiOperation(value = "${UserController.signup}")
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 422, message = "Username is already in use")})
    public String signup(//@ApiParam("Signup User")
                         @RequestBody AppUser user) {
        return userService.signup(user);
    }

    @DeleteMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ApiOperation(value = "${UserController.delete}", authorizations = { @Authorization(value="apiKey") })
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 404, message = "The user doesn't exist"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public String delete(//@ApiParam("Username")
                             @PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 404, message = "The user doesn't exist"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public AppUser search(//ApiParam("Username")
                                      @PathVariable String username) {
        return userService.search(username);
    }

    @GetMapping(value = "/me")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
//    @ApiOperation(value = "${UserController.me}", response = UserResponseDTO.class, authorizations = { @Authorization(value="apiKey") })
//    @ApiResponses(value = {//
//            @ApiResponse(code = 400, message = "Something went wrong"), //
//            @ApiResponse(code = 403, message = "Access denied"), //
//            @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
    public AppUser whoami(HttpServletRequest req) {
        return userService.whoami(req);
    }

    @GetMapping("/refresh")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public String refresh(HttpServletRequest req) {
        return userService.refresh(req.getRemoteUser());
    }

}