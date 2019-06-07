package com.csye6225.services;

import com.csye6225.models.User;
import com.csye6225.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService  implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User u= userRepository.findByEmailAddress(s);
        if (u == null) {

            throw new UsernameNotFoundException(s);
        }

        return new MyUserDetails(u);


    }


    public void register(User user){
        user.setPassword(bcrypt.encode(user.getPassword()));
//                user.setEmailAddress(email_id);
        userRepository.save(user);

    }
}