package com.inmediasp.todoList.restController;

import com.inmediasp.todoList.component.SecurityContextFacade;
import com.inmediasp.todoList.dto.TodoDTO;
import com.inmediasp.todoList.entity.TodoUserEntity;
import com.inmediasp.todoList.service.TodoService;
import com.inmediasp.todoList.service.TodoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    TodoService todoService;
    @Autowired
    TodoUserService todoUserService;
    @Autowired
    SecurityContextFacade securityContextFacade;

    @GetMapping
    @Secured("USER")
    public ResponseEntity<List<TodoDTO>> getTodos() {
        Authentication authentication = securityContextFacade.getAuthentication();
        int userId = todoUserService.determineUser(authentication).getUserId();
        List<TodoDTO> todos = todoService.getTodosByUserId(userId);

        return ResponseEntity.ok(todos);
    }

    @PostMapping
    @Secured("USER")
    public ResponseEntity<TodoDTO> postTodo(@RequestBody TodoDTO todoDTO) {
        Authentication authentication = securityContextFacade.getAuthentication();
        TodoUserEntity todoUser = todoUserService.determineUser(authentication);

        TodoDTO savedTodoDTO = todoService.save(todoDTO, todoUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTodoDTO);
    }

    @PutMapping("/{id}")
    @Secured("USER")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable("id") Integer todoId, @RequestBody TodoDTO todoDTO) {
        Authentication authentication = securityContextFacade.getAuthentication();
        TodoUserEntity todoUser = todoUserService.determineUser(authentication);

        checkUserAuthorization(todoUser, todoId);

        TodoDTO updatedTodoDTO = todoService.update(todoId, todoDTO, todoUser);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodoDTO);
    }

    @DeleteMapping("/{id}")
    @Secured("USER")
    public ResponseEntity<Void> deleteTodo(@PathVariable("id") Integer todoId) {
        Authentication authentication = securityContextFacade.getAuthentication();
        TodoUserEntity todoUser = todoUserService.determineUser(authentication);

        checkUserAuthorization(todoUser, todoId);

        todoService.delete(todoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void checkUserAuthorization(TodoUserEntity todoUser, int todoId) {
        TodoUserEntity todoOwner = todoService.findById(todoId).getOwner();
        if (!todoOwner.equals(todoUser)) {
            throw new AccessDeniedException("User is not authorized to access this todo");
        }
    }
}
