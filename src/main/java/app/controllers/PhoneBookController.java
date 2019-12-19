package app.controllers;

import app.data.HeadDBImpl;
import app.domain.EntryPhoneBook;
import org.jboss.arquillian.container.test.api.BeforeDeployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/pb/{idPB}")
public class PhoneBookController {
    private String tableName;

    @BeforeDeployment
    public void setTableName(@PathVariable(value = "idPB") String path) {
        this.tableName = path;
        System.out.println(tableName);
    }

    @Autowired
    private HeadDBImpl<EntryPhoneBook> userHeadDB;

    @GetMapping
    public ResponseEntity<List<EntryPhoneBook>> getAll() {
        return ResponseEntity.ok(userHeadDB.getAll(tableName));
    }

    @GetMapping(path = "/{entryId}")
    public ResponseEntity<EntryPhoneBook> getUser(@PathVariable(value = "entryId") UUID entryId, String tableName) {
        return ResponseEntity.ok(userHeadDB.getById(tableName, entryId));
    }

    @PostMapping
    public ResponseEntity<EntryPhoneBook> createUser(@RequestBody EntryPhoneBook entryPB) {
        return ResponseEntity.ok(userHeadDB.create(tableName, entryPB));
    }

    @PutMapping
    public ResponseEntity<EntryPhoneBook> changeUser(@RequestBody EntryPhoneBook entryPB) {

        return ResponseEntity.ok(userHeadDB.change(tableName, entryPB));
    }

    @DeleteMapping
    public ResponseEntity<EntryPhoneBook> deleteUser(@RequestBody EntryPhoneBook entryPB) {
        userHeadDB.delete(tableName, entryPB);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "search")
    public ResponseEntity<List<EntryPhoneBook>> searchEntriesForPB(@RequestParam("inText") String inText) {
        List<EntryPhoneBook> userList = userHeadDB.searchAll(tableName, inText);
        return ResponseEntity.ok(userList);
    }
}
