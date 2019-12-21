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
        List<User> result = userHeadDB.getAll(tableName);

        return (result != null && !result.isEmpty()) ?
                ResponseEntity.ok(result) :
                ResponseEntity.notFound().build();
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
        return isFilled(user) ?
                ResponseEntity.ok(userHeadDB.create(tableName, user)) :
                ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<User> changeUser(@RequestBody User user) {
        User res = userHeadDB.change(tableName, user);

        return (isFilled(user) && res != null) ?
                ResponseEntity.ok(res) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        return userHeadDB.delete(tableName, user) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "search")
    public ResponseEntity<List<User>> searchUser(@RequestParam("inText") String inText) {
        List<User> userList = userHeadDB.searchAll(tableName, inText);

        if (userList == null || userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userList);
    }

    public boolean isFilled(User user) {
        return user != null &&
               user.getName() != null && !user.getName().equals("") &&
               user.getSurname() != null && !user.getSurname().equals("");
    }
}
