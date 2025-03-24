package com.inmediasp.todoList.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@AllArgsConstructor
@Data
public class TodoDTO {
    private int todoId;

    private String title;

    private boolean important;

    private boolean checked;

    private Date date;

    private String description;

    private boolean fromFriend;

    private String creatorName;
}
