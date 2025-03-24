package com.inmediasp.todoList.service;

import com.inmediasp.todoList.dao.TodoRepository;
import com.inmediasp.todoList.dto.TodoDTO;
import com.inmediasp.todoList.entity.TodoEntity;
import com.inmediasp.todoList.entity.TodoUserEntity;
import com.inmediasp.todoList.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoMapper todoMapper;

    public List<TodoDTO> getTodosByUserId(Integer userId) {
        List<TodoEntity> todoEntities = todoRepository.findTodosByOwnerId(userId);
        return todoEntities.stream().map(todoEntity -> todoMapper.toDTO(todoEntity)).toList();
    }


    public TodoDTO save(TodoDTO todoDTO, TodoUserEntity todoUser) {
        TodoEntity todoEntity = todoMapper.toEntity(todoDTO, todoUser);
        TodoEntity savedEntity = todoRepository.save(todoEntity);

        return todoMapper.toDTO(savedEntity);
    }

    public TodoEntity findById(Integer todoId) {
        return todoRepository.findById(todoId).orElseThrow();
    }

    public TodoDTO update(Integer todoId, TodoDTO todoDTO, TodoUserEntity todoUser) {
        TodoEntity todoEntity = todoMapper.toEntity(todoDTO, todoUser);
        todoEntity.setTodoId(todoId);
        TodoEntity updatedEntity = todoRepository.save(todoEntity);

        return todoMapper.toDTO(updatedEntity);
    }

    public void delete(Integer todoId) {
        todoRepository.deleteById(todoId);
    }
}
