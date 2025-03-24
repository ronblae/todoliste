package com.inmediasp.todoList.unitTests.mapper;

import com.inmediasp.todoList.dto.TodoDTO;
import com.inmediasp.todoList.entity.TodoEntity;
import com.inmediasp.todoList.entity.TodoUserEntity;
import com.inmediasp.todoList.mapper.TodoMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TodoMapperTest {

    @Mock
    TodoEntity todoEntity;

    @Mock
    TodoDTO todoDTO;

    @Mock
    TodoUserEntity todoUser;

    @InjectMocks
    TodoMapper todoMapper;

    @Test
    public void GIVEN_todoEntity_THEN_returnCorrespondingTodoDTO() {
        Date newDate = new Date();
        when(todoUser.getUserId()).thenReturn(1);
        when(todoUser.getFirstName()).thenReturn("Peter");
        when(todoUser.getLastName()).thenReturn("Pan");

        when(todoEntity.getTodoId()).thenReturn(1);
        when(todoEntity.getTitle()).thenReturn("Test Title");
        when(todoEntity.isImportant()).thenReturn(true);
        when(todoEntity.isChecked()).thenReturn(false);
        when(todoEntity.getDescription()).thenReturn("Test Description");
        when(todoEntity.getDate()).thenReturn(newDate);
        when(todoEntity.getCreator()).thenReturn(todoUser);
        when(todoEntity.getOwner()).thenReturn(todoUser);

        TodoDTO result = todoMapper.toDTO(todoEntity);

        assertNotNull(result);
        assertEquals(1, result.getTodoId());
        assertEquals("Test Title", result.getTitle());
        assertTrue(result.isImportant());
        assertFalse(result.isChecked());
        assertEquals("Test Description", result.getDescription());
        assertEquals(newDate, result.getDate());
        assertEquals("Peter Pan", result.getCreatorName());
        assertFalse(result.isFromFriend());
    }

    @Test
    public void given_todoDTO_THEN_returnCorrespondingTodoEntity() {
        Date newDate = new Date();
        when(todoDTO.getTodoId()).thenReturn(1);
        when(todoDTO.getTitle()).thenReturn("Test Todo");
        when(todoDTO.isImportant()).thenReturn(true);
        when(todoDTO.isChecked()).thenReturn(false);
        when(todoDTO.getDescription()).thenReturn("Test Description");
        when(todoDTO.getDate()).thenReturn(newDate);

        TodoEntity result = todoMapper.toEntity(todoDTO, todoUser);

        assertNotNull(result);
        assertEquals(1, result.getTodoId());
        assertEquals("Test Todo", result.getTitle());
        assertTrue(result.isImportant());
        assertFalse(result.isChecked());
        assertEquals("Test Description", result.getDescription());
        assertEquals(newDate, result.getDate());
        assertEquals(todoUser, result.getCreator());
        assertEquals(todoUser, result.getOwner());
    }
}
