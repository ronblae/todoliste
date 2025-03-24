package com.inmediasp.todoList.unitTests.restController;

import com.inmediasp.todoList.component.SecurityContextFacade;
import com.inmediasp.todoList.dto.TodoDTO;
import com.inmediasp.todoList.entity.TodoEntity;
import com.inmediasp.todoList.entity.TodoUserEntity;
import com.inmediasp.todoList.restController.TodoController;
import com.inmediasp.todoList.service.TodoService;
import com.inmediasp.todoList.service.TodoUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TodoControllerTest {
    @Mock
    TodoService todoService;

    @Mock
    TodoUserService todoUserService;

    @Mock
    SecurityContextFacade securityContextFacade;

    @InjectMocks
    TodoController todoController;

    @Mock
    Authentication authentication;

    @Mock
    TodoUserEntity todoUser;

    @Mock
    TodoDTO expectedTodo;

    final int USER_ID = 1;

    final int TODO_ID = 1;

    @BeforeEach
    void setup() {
        when(securityContextFacade.getAuthentication()).thenReturn(authentication);
        when(todoUserService.determineUser(authentication)).thenReturn(todoUser);
        lenient().when(todoService.findById(TODO_ID)).thenReturn(TodoEntity.builder().owner(todoUser).build());
        lenient().when(todoUser.getUserId()).thenReturn(USER_ID);
    }

    @Test
    void GIVEN_getRequest_THEN_returnListOfTodos() {
        List<TodoDTO> expectedList = new ArrayList<>();
        expectedList.add(expectedTodo);
        when(todoService.getTodosByUserId(USER_ID)).thenReturn(expectedList);

        ResponseEntity<List<TodoDTO>> result = todoController.getTodos();

        assertNotNull(result);
        assertEquals(1, Objects.requireNonNull(result.getBody()).size());
        assertEquals(expectedList, result.getBody());
        verify(todoService, times(1)).getTodosByUserId(USER_ID);
    }

    @Test
    void GIVEN_postRequest_THEN_returnSavedTodo() {
        when(todoService.save(expectedTodo, todoUser)).thenReturn(expectedTodo);

        ResponseEntity<TodoDTO> result = todoController.postTodo(expectedTodo);

        assertNotNull(result);
        assertEquals(expectedTodo, result.getBody());
        verify(todoService, times(1)).save(expectedTodo, todoUser);
    }

    @Test
    void GIVEN_updateRequest_THEN_returnUpdatedTodo() {
        when(todoService.update(TODO_ID, expectedTodo, todoUser)).thenReturn(expectedTodo);

        ResponseEntity<TodoDTO> result = todoController.updateTodo(TODO_ID, expectedTodo);

        assertNotNull(result);
        assertEquals(expectedTodo, result.getBody());
        verify(todoService, times(1)).update(TODO_ID, expectedTodo, todoUser);
    }

    @Test
    void GIVEN_deleteRequest_THEN_returnNoContent() {
        ResponseEntity<Void> result = todoController.deleteTodo(TODO_ID);

        assertNotNull(result);
        verify(todoService, times(1)).delete(TODO_ID);
    }
}
