package com.inmediasp.todoList.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendshipIdEntity implements Serializable {
    private Integer user1;
    private Integer user2;
}
