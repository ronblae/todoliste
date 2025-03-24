package com.inmediasp.todoList.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table(name = "todo")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_id")
    private Integer todoId;

    @Column(name = "title")
    private String title;

    @Column(name = "important")
    private boolean important;

    @Column(name = "checked")
    private boolean checked;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    private TodoUserEntity creator;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "user_id")
    private TodoUserEntity owner;
}
