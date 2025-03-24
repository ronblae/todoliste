package com.inmediasp.todoList.mapper;

import com.inmediasp.todoList.dto.TodoDTO;
import com.inmediasp.todoList.entity.TodoEntity;
import com.inmediasp.todoList.entity.TodoUserEntity;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public TodoDTO toDTO(TodoEntity todoEntity) {
        TodoUserEntity creator = todoEntity.getCreator();
        int creatorId = creator.getUserId();
        int ownerId = todoEntity.getOwner().getUserId();
        boolean fromFriend = creatorId != ownerId;
        String creatorName = creator.getFirstName() + " " + creator.getLastName();

        return TodoDTO.builder()
                .todoId(todoEntity.getTodoId())
                .title(todoEntity.getTitle())
                .important(todoEntity.isImportant())
                .checked(todoEntity.isChecked())
                .date(todoEntity.getDate())
                .description(todoEntity.getDescription())
                .fromFriend(fromFriend)
                .creatorName(creatorName)
                .build();
    }

    public TodoEntity toEntity(TodoDTO todoDTO, TodoUserEntity todoUser) {
        return TodoEntity.builder()
                .todoId(todoDTO.getTodoId())
                .title(todoDTO.getTitle())
                .important(todoDTO.isImportant())
                .checked(todoDTO.isChecked())
                .description(todoDTO.getDescription())
                .date(todoDTO.getDate())
                .creator(todoUser)
                .owner(todoUser)
                .build();
    }
}
