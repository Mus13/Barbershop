package com.Mustapha.SpringBootDemo.Services;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class EmployeesDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Replace this with your actual user lookup logic
        if ("Mustapha".equals(username)) {
            return User.withDefaultPasswordEncoder()
                    .username("Mustapha")
                    .password("admin")
                    .roles("ADMIN")
                    .build();
        }
        if ("Daria".equals(username)) {
            return User.withDefaultPasswordEncoder()
                    .username("Daria")
                    .password("staff")
                    .roles("User")
                    .build();
        }
        return null;
    }
}
