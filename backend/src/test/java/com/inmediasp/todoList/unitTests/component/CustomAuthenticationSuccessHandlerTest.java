package com.inmediasp.todoList.unitTests.component;

import com.inmediasp.todoList.component.CustomAuthenticationSuccessHandler;
import com.inmediasp.todoList.component.SecurityContextFacade;
import com.inmediasp.todoList.dao.TodoUserRepository;
import com.inmediasp.todoList.entity.TodoUserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomAuthenticationSuccessHandlerTest {

    @Mock
    TodoUserRepository todoUserRepository;

    @Mock
    SecurityContextFacade securityContextFacade;

    @Mock
    SecurityContext context;

    @InjectMocks
    CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    Authentication authentication;

    @Mock
    DefaultOidcUser principal;

    @Mock
    OidcUserInfo userInfo;

    @Mock
    TodoUserEntity knownUser;

    final String SUBJECT = "testSubject";
    final String GIVEN_NAME = "Peter";
    final String FAMILY_NAME = "Pan";
    final String EMAIL = "peter.pan@email.de";

    @BeforeEach
    void setup() {
        when(authentication.getPrincipal()).thenReturn(principal);
        when(principal.getUserInfo()).thenReturn(userInfo);
        when(userInfo.getSubject()).thenReturn(SUBJECT);
        when(securityContextFacade.getContext()).thenReturn(context);
    }

    @Test
    public void GIVEN_newUserLogsInSuccessfully_THEN_saveNewUserAndSetAuthentication() throws ServletException, IOException {
        when(todoUserRepository.findBySubjectWithRoles(SUBJECT)).thenReturn(null);
        when(userInfo.getSubject()).thenReturn(SUBJECT);
        when(userInfo.getGivenName()).thenReturn(GIVEN_NAME);
        when(userInfo.getFamilyName()).thenReturn(FAMILY_NAME);
        when(userInfo.getEmail()).thenReturn(EMAIL);

        customAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(todoUserRepository, times(1)).save(any());
        verify(context, times(1)).setAuthentication(any());
    }

    @Test
    public void GIVEN_knownUserLogsInSuccessfully_THEN_findUserAndSetAuthentication() throws ServletException, IOException {
        when(todoUserRepository.findBySubjectWithRoles(SUBJECT)).thenReturn(knownUser);

        customAuthenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication);

        verify(todoUserRepository, times(0)).save(any());
        verify(context, times(1)).setAuthentication(any());
    }
}
