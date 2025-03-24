package com.inmediasp.todoList.beanTests;

import com.inmediasp.todoList.component.CustomAuthenticationSuccessHandler;
import com.inmediasp.todoList.component.CustomLogoutSuccessHandler;
import com.inmediasp.todoList.component.SecurityContextFacade;
import com.inmediasp.todoList.configuration.CorsConfig;
import com.inmediasp.todoList.configuration.SecurityConfig;
import com.inmediasp.todoList.dao.FriendshipRepository;
import com.inmediasp.todoList.dao.TodoRepository;
import com.inmediasp.todoList.dao.TodoUserRepository;
import com.inmediasp.todoList.exceptionHandler.RestExceptionHandler;
import com.inmediasp.todoList.mapper.TodoMapper;
import com.inmediasp.todoList.restController.FriendshipController;
import com.inmediasp.todoList.restController.RedirectController;
import com.inmediasp.todoList.restController.TodoController;
import com.inmediasp.todoList.service.FriendshipService;
import com.inmediasp.todoList.service.TodoService;
import com.inmediasp.todoList.service.TodoUserService;
import com.inmediasp.todoList.userDetails.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BeanTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Autowired
    CustomLogoutSuccessHandler customLogoutSuccessHandler;
    @Autowired
    CorsConfig corsConfig;
    @Autowired
    SecurityConfig securityConfig;
    @Autowired
    FriendshipRepository friendshipRepository;
    @Autowired
    TodoRepository todoRepository;
    @Autowired
    TodoUserRepository todoUserRepository;
    @Autowired
    RestExceptionHandler restExceptionHandler;
    @Autowired
    TodoMapper todoMapper;
    @Autowired
    FriendshipController friendshipController;
    @Autowired
    RedirectController redirectController;
    @Autowired
    TodoController todoController;
    @Autowired
    FriendshipService friendshipService;
    @Autowired
    TodoService todoService;
    @Autowired
    TodoUserService todoUserService;
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    SecurityContextFacade securityContextFacade;

    @Test
    public void GIVEN_applicationStart_THEN_allBeansAreNotNull() {
        assertNotNull(applicationContext);
        assertNotNull(customAuthenticationSuccessHandler);
        assertNotNull(customLogoutSuccessHandler);
        assertNotNull(corsConfig);
        assertNotNull(securityConfig);
        assertNotNull(friendshipRepository);
        assertNotNull(todoRepository);
        assertNotNull(todoUserRepository);
        assertNotNull(restExceptionHandler);
        assertNotNull(todoMapper);
        assertNotNull(friendshipController);
        assertNotNull(redirectController);
        assertNotNull(todoController);
        assertNotNull(friendshipService);
        assertNotNull(todoService);
        assertNotNull(todoUserService);
        assertNotNull(customUserDetailsService);
        assertNotNull(securityContextFacade);
    }
}
