package com.inmediasp.todoList.component;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContextFacade {

    public void clearContext() {
        SecurityContextHolder.clearContext();
    }

    public SecurityContext getContext() {
        return SecurityContextHolder.getContext();
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

