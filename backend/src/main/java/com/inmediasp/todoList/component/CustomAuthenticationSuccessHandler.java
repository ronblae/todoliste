package com.inmediasp.todoList.component;

import com.inmediasp.todoList.dao.TodoUserRepository;
import com.inmediasp.todoList.entity.RoleEntity;
import com.inmediasp.todoList.entity.TodoUserEntity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
public class CustomAuthenticationSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements AuthenticationSuccessHandler {
    @Autowired
    private final TodoUserRepository todoUserRepository;

    @Autowired
    private final SecurityContextFacade securityContextFacade;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        DefaultOidcUser principal = (DefaultOidcUser) authentication.getPrincipal();
        OidcUserInfo userInfo = principal.getUserInfo();
        String subject = userInfo.getSubject();

        TodoUserEntity todoUser = findOrCreateUser(subject, userInfo);

        UsernamePasswordAuthenticationToken newAuthentication = createNewAuthentication(todoUser, principal);
        SecurityContext context = securityContextFacade.getContext();
        context.setAuthentication(newAuthentication);

        setDefaultTargetUrl("/");
        handle(request, response, authentication);
    }

    private TodoUserEntity findOrCreateUser(String subject, OidcUserInfo userInfo) {
        TodoUserEntity todoUser = todoUserRepository.findBySubjectWithRoles(subject);

        if (todoUser == null) {
            Set<RoleEntity> standardRoleSetEntity = new HashSet<>();
            standardRoleSetEntity.add(new RoleEntity(2, "USER"));

            todoUser = new TodoUserEntity(userInfo.getGivenName(), userInfo.getFamilyName(), userInfo.getEmail(), subject, standardRoleSetEntity);
            todoUserRepository.save(todoUser);
        }
        return todoUser;
    }

    private static UsernamePasswordAuthenticationToken createNewAuthentication(TodoUserEntity todoUser, DefaultOidcUser principal) {
        List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
        todoUser.getRoles().forEach(role ->
                updatedAuthorities.add(new SimpleGrantedAuthority(role.getName())));

        return new UsernamePasswordAuthenticationToken(principal, "", updatedAuthorities);
    }
}
