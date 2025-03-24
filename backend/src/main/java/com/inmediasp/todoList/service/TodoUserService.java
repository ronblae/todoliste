package com.inmediasp.todoList.service;

import com.inmediasp.todoList.dao.TodoUserRepository;
import com.inmediasp.todoList.entity.TodoUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

@Service
public class TodoUserService {
    @Autowired
    private TodoUserRepository todoUserRepository;

    public TodoUserEntity getUserBySubject(String subject) {
        return todoUserRepository.findBySubject(subject);
    }

    public TodoUserEntity determineUser(Authentication authentication) {
        DefaultOidcUser user = (DefaultOidcUser) authentication.getPrincipal();
        String userSubject = user.getSubject();
        return getUserBySubject(userSubject);
    }
}
