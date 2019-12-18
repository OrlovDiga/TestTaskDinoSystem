package app.controllers;

import app.data.GeneralDBImpl;
import app.domain.EntryPhoneBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/pb")
public class PhoneBookController {

    @Autowired
    private GeneralDBImpl<EntryPhoneBook> entryPhoneBookGeneralDB;

    @GetMapping
    public ResponseEntity<List<EntryPhoneBook>> getAll() {
        return ResponseEntity.ok(entryPhoneBookGeneralDB.getAll());
    }

    @GetMapping(path = "/{entryId}")
    public ResponseEntity<EntryPhoneBook> getUser(@PathVariable(value = "entryId") UUID entryId, String tableName) {
        return ResponseEntity.ok(entryPhoneBookGeneralDB.getById(entryId));
    }

    @GetMapping(path = "/{entryId}")
    public ResponseEntity<EntryPhoneBook> getUser(@PathVariable(value = "entryId") UUID entryId) {
        return ResponseEntity.ok(entryPhoneBookGeneralDB.getById(entryId));
    }

    @PostMapping
    public ResponseEntity<EntryPhoneBook> createUser(@RequestBody EntryPhoneBook entryPB) {
        entryPhoneBookGeneralDB.add(entryPB);

        return ResponseEntity.ok(entryPhoneBookGeneralDB.add(entryPB));
    }

    @PutMapping
    public ResponseEntity<EntryPhoneBook> changeUser(@RequestBody EntryPhoneBook entryPB) {
        entryPhoneBookGeneralDB.change(entryPB);

        return ResponseEntity.ok(entryPhoneBookGeneralDB.get(entryPB));
    }

    @DeleteMapping
    public ResponseEntity<EntryPhoneBook> deleteUser(@RequestBody EntryPhoneBook entryPB) {
        entryPhoneBookGeneralDB.remove(entryPB);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "search")
    public ResponseEntity<List<EntryPhoneBook>> searchEntriesForPB(@RequestParam("inText") String inText) {
        List<EntryPhoneBook> userList = entryPhoneBookGeneralDB.searchUUIDS(inText);
        return ResponseEntity.ok(userList);
    }
}
