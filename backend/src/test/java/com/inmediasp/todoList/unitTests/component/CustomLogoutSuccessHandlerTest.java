package com.inmediasp.todoList.unitTests.component;

import com.inmediasp.todoList.component.CustomLogoutSuccessHandler;
import com.inmediasp.todoList.component.SecurityContextFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomLogoutSuccessHandlerTest {
    @InjectMocks
    CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Mock
    SecurityContextFacade securityContextFacade;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Mock
    Authentication authentication;

    @Mock
    HttpSession session;

    @Test
    void GIVEN_userLogsOut_THEN_invalidateSessionAndRedirect() throws IOException {
        when(request.getSession()).thenReturn(session);

        customLogoutSuccessHandler.onLogoutSuccess(request, response, authentication);

        verify(securityContextFacade, times(1)).clearContext();
        verify(session, times(1)).invalidate();
        verify(response, times(1)).sendRedirect(any());
    }
}
