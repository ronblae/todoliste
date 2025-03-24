package com.inmediasp.todoList.dao;

import com.inmediasp.todoList.entity.TodoUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUserEntity, Integer> {
    TodoUserEntity findBySubject(String subject);

    @Query("SELECT u FROM TodoUserEntity u LEFT JOIN FETCH u.roles WHERE u.subject = ?1")
    TodoUserEntity findBySubjectWithRoles(String subject);
}
