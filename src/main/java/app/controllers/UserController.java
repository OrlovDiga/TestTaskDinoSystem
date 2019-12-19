package app.controllers;

import app.data.CoreDBImpl;
import app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    String tableName = "user";
    @Autowired
    private CoreDBImpl<User> userHeadDB;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userHeadDB.getAll(tableName));
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(value = "userId") UUID userId) {
        User user = userHeadDB.getById(tableName ,userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userHeadDB.create(tableName, user));
    }

    @PutMapping
    public ResponseEntity<User> changeUser(@RequestBody User user) {
        return ResponseEntity.ok(userHeadDB.change(tableName, user));
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        userHeadDB.delete(tableName, user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "search")
    public ResponseEntity<List<User>> searchUser(@RequestParam("inText") String inText) {
        List<User> userList = userHeadDB.searchAll(tableName, inText);

        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userList);
    }


}
