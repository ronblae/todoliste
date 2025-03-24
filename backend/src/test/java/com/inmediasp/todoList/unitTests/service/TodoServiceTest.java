package com.inmediasp.todoList.unitTests.service;

import com.inmediasp.todoList.dao.TodoRepository;
import com.inmediasp.todoList.dto.TodoDTO;
import com.inmediasp.todoList.entity.TodoEntity;
import com.inmediasp.todoList.entity.TodoUserEntity;
import com.inmediasp.todoList.mapper.TodoMapper;
import com.inmediasp.todoList.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    TodoRepository todoRepository;

    @Mock
    TodoMapper todoMapper;

    @InjectMocks
    TodoService todoService;

    @Mock
    TodoDTO expectedTodo;

    @Mock
    TodoUserEntity todoUser;

    @Mock
    TodoEntity todoEntity;

    final int USER_ID = 1;

    final int TODO_ID = 1;

    @Test
    public void GIVEN_userId_THEN_returnListOfUserTodos() {
        List<TodoEntity> todos = new ArrayList<>();
        todos.add(new TodoEntity());

        when(todoRepository.findTodosByOwnerId(USER_ID)).thenReturn(todos);
        when(todoMapper.toDTO(any())).thenReturn(expectedTodo);

        List<TodoDTO> result = todoService.getTodosByUserId(USER_ID);

        assertNotNull(result);
        assertEquals(expectedTodo, result.getFirst());
    }

    @Test
    public void GIVEN_todoAndUser_THEN_saveAndReturnTodo() {
        when(todoMapper.toEntity(expectedTodo, todoUser)).thenReturn(todoEntity);
        when(todoRepository.save(todoEntity)).thenReturn(todoEntity);
        when(todoMapper.toDTO(todoEntity)).thenReturn(expectedTodo);

        TodoDTO savedTodo = todoService.save(expectedTodo, todoUser);

        assertNotNull(savedTodo);
        assertEquals(expectedTodo, savedTodo);
    }

    @Test
    public void GIVEN_validTodoId_THEN_returnTodo() {
        when(todoRepository.findById(TODO_ID)).thenReturn(Optional.of(todoEntity));

        TodoEntity result = todoService.findById(TODO_ID);

        assertNotNull(result);
        assertEquals(todoEntity, result);
    }

    @Test
    public void GIVEN_invalidTodoId_THEN_returnThrowNoSuchElementException() {
        when(todoRepository.findById(TODO_ID)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> todoService.findById(TODO_ID));
    }

    @Test
    public void testUpdateTodo() {
        when(todoMapper.toEntity(expectedTodo, todoUser)).thenReturn(todoEntity);
        when(todoRepository.save(todoEntity)).thenReturn(todoEntity);
        when(todoMapper.toDTO(todoEntity)).thenReturn(expectedTodo);

        TodoDTO updatedTodo = todoService.update(TODO_ID, expectedTodo, todoUser);

        assertNotNull(updatedTodo);
        assertEquals(expectedTodo, updatedTodo);
    }

    @Test
    public void testDeleteTodo() {
        todoService.delete(TODO_ID);

        verify(todoRepository, times(1)).deleteById(TODO_ID);
    }
}
