package com.inmediasp.todoList.service;

import com.inmediasp.todoList.dao.FriendshipRepository;
import com.inmediasp.todoList.dao.TodoUserRepository;
import com.inmediasp.todoList.dto.FriendDTO;
import com.inmediasp.todoList.entity.FriendshipEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipService {
    @Autowired
    private TodoUserRepository todoUserRepository;
    @Autowired
    private FriendshipRepository friendshipRepository;

    public List<FriendDTO> getFriendshipsFromUser(Integer userId) {
        List<FriendshipEntity> friendshipEntities = friendshipRepository.findAll();
        friendshipEntities.removeIf(friendship -> !friendship.getUser1().equals(userId) && !friendship.getUser2().equals(userId));

        List<Integer> friendshipIds = friendshipEntities.stream()
                .map(friendship -> friendship.getUser1().equals(userId) ? friendship.getUser2() : friendship.getUser1())
                .toList();

        return friendshipIds.stream()
                .map(friendshipId -> todoUserRepository.findById(friendshipId).orElseThrow())
                .map(todoUser -> new FriendDTO(todoUser.getEmail(), todoUser.getFirstName() + " " + todoUser.getLastName()))
                .toList();
    }

    public FriendshipEntity createFriendship(Integer userId1, Integer userId2) {
        return friendshipRepository.save(new FriendshipEntity(userId1, userId2));
    }

    public List<FriendshipEntity> getFriendships() {
        return friendshipRepository.findAll();
    }
}
