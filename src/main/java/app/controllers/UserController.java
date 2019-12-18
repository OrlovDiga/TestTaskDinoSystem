package app.controllers;

import app.data.GeneralDBImpl;
import app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/user")
public class UserController {

    @Autowired
    private GeneralDBImpl<User> userGeneralDB;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userGeneralDB.getAll());
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<User> getUser(@PathVariable(value = "userId") UUID userId) {
        User user = userGeneralDB.getById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userGeneralDB.add(user));
    }

    @PutMapping
    public ResponseEntity<User> changeUser(@RequestBody User user) {
        return ResponseEntity.ok(userGeneralDB.change(user));
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestBody User user) {
        userGeneralDB.remove(user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "search")
    public ResponseEntity<List<User>> searchUser(@RequestParam("inText") String inText) {
        List<User> userList = userGeneralDB.searchUUIDS(inText);

        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userList);
    }


}
