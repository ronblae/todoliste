package com.inmediasp.todoList.dao;

import com.inmediasp.todoList.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Integer> {
    @Query("SELECT t FROM TodoEntity t WHERE t.owner.userId = :userId")
    List<TodoEntity> findTodosByOwnerId(@Param("userId") Integer userId);
}
