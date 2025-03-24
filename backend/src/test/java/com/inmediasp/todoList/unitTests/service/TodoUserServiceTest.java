package com.inmediasp.todoList.unitTests.service;

import com.inmediasp.todoList.dao.TodoUserRepository;
import com.inmediasp.todoList.entity.TodoUserEntity;
import com.inmediasp.todoList.service.TodoUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoUserServiceTest {

    @Mock
    TodoUserRepository todoUserRepository;

    @InjectMocks
    TodoUserService todoUserService;

    @Mock
    Authentication authentication;

    @Mock
    DefaultOidcUser oidcUser;

    @Mock
    TodoUserEntity todoUser;

    final String TEST_SUBJECT = "testSubject";

    @Test
    void GIVEN_subject_THEN_returnUserOfSubject() {
        when(todoUserRepository.findBySubject(TEST_SUBJECT)).thenReturn(todoUser);

        TodoUserEntity result = todoUserService.getUserBySubject(TEST_SUBJECT);

        assertNotNull(result);
        assertEquals(todoUser, result);
    }

    @Test
    void GIVEN_authentication_THEN_returnCorrespondingUser() {
        when(todoUserRepository.findBySubject(TEST_SUBJECT)).thenReturn(todoUser);
        when(authentication.getPrincipal()).thenReturn(oidcUser);
        when(oidcUser.getSubject()).thenReturn(TEST_SUBJECT);

        TodoUserEntity result = todoUserService.determineUser(authentication);

        assertNotNull(result);
        assertEquals(todoUser, result);
    }
}
