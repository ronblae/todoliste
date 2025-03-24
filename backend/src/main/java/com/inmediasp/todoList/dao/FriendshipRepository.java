package com.inmediasp.todoList.dao;

import com.inmediasp.todoList.entity.FriendshipEntity;
import com.inmediasp.todoList.entity.FriendshipIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipEntity, FriendshipIdEntity> {

}
