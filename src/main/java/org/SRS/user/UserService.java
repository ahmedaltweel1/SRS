package org.SRS.user;

import org.SRS.error.NotFoundException;
import org.SRS.gender.Gender;
import org.SRS.gender.GenderRepository;
import org.SRS.role.Role;
import org.SRS.role.RoleRepository;
import org.SRS.security.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final GenderRepository genderRepository;



    public UserService (UserRepository user,PasswordEncoder passwordEncoder,RoleRepository roleRepository,GenderRepository genderRepository){
        this.userRepository=user;
        this.passwordEncoder=passwordEncoder;
        this.roleRepository=roleRepository;
        this.genderRepository=genderRepository;
    }


    private User mapToUser(CreateUserDTO dto,Gender gender,Role role){
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setBirthday(dto.getBirthday());
        user.setRole(role);
        user.setGender(gender);
        return user;
    }


    @Transactional
    public User registerUser(CreateUserDTO dto){

        String hashPassword=passwordEncoder.encode(dto.getPassword());

        Role role =roleRepository.findById(dto.getRoleId()).orElseThrow(()-> new NotFoundException("Role not found"));
        Gender gender= genderRepository.findById(dto.getGender()).orElseThrow(()-> new NotFoundException("Gender not found"));

        User user= mapToUser(dto,gender,role);

        return userRepository.save(user);


    }



    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return new UserDetailsImpl(user);
    }

}
