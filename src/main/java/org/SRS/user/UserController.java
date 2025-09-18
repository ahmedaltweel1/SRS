package org.SRS.user;


import org.SRS.security.JwtResponse;
import org.SRS.security.TokenUtil;
import org.SRS.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/auth")
public class UserController {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping(value={"","/"})
    public JwtResponse SignIn(@RequestBody LoginDTO loginDto){

        final Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails=userService.loadUserByUsername(loginDto.getUsername());
        String Token=tokenUtil.generateToken(userDetails);

        JwtResponse response= new JwtResponse(Token);
        return response;
    }

    @PostMapping(value="/signup")
    public JwtResponse SignUp(@RequestBody @Validated CreateUserDTO createUserDTO){
        User user= userService.registerUser(createUserDTO);
        UserDetailsImpl userDetails=new UserDetailsImpl(user);
        String token= tokenUtil.generateToken(userDetails);
        JwtResponse response= new JwtResponse(token);

        return response;


    }
}
