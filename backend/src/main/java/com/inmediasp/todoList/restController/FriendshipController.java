package com.inmediasp.todoList.restController;

import com.inmediasp.todoList.dto.FriendDTO;
import com.inmediasp.todoList.entity.FriendshipEntity;
import com.inmediasp.todoList.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/friendships")
public class FriendshipController {

    private final FriendshipService friendshipService;

    @Autowired
    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/{userId}")
    @Secured("USER")
    public ResponseEntity<List<FriendDTO>> getFriendshipsByUserId(@PathVariable Integer userId) {
        List<FriendDTO> friendships = friendshipService.getFriendshipsFromUser(userId);
        if (friendships.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(friendships);
    }

    @GetMapping
    public ResponseEntity<List<FriendshipEntity>> getFriendships() {
        List<FriendshipEntity> friendshipEntities = friendshipService.getFriendships();
        if (friendshipEntities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(friendshipEntities);
    }

    @PostMapping
    public ResponseEntity<String> createFriendship(@RequestBody Map<String, Integer> requestBody) {
        Integer userId1 = requestBody.get("userId1");
        Integer userId2 = requestBody.get("userId2");

        if (userId1 != null && userId2 != null) {
            if (friendshipService.createFriendship(userId1, userId2) == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            return ResponseEntity.ok("FriendshipEntity created successfully");
        } else {
            return ResponseEntity.badRequest().body("Missing userId1 or userId2 in the request body");
        }
    }
}
