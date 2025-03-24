package com.inmediasp.todoList.userDetails;


import com.inmediasp.todoList.entity.TodoUserEntity;
import com.inmediasp.todoList.service.TodoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private TodoUserService todoUserService;

    @Override
    public UserDetails loadUserByUsername(String subject) throws UsernameNotFoundException {
        TodoUserEntity user = todoUserService.getUserBySubject(subject);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new CustomUserDetails(user.getSubject(), authorities);
    }
}
