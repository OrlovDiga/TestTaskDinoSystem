package app.controllers;

import app.data.CoreDBImpl;
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
    }

    @Autowired
    private CoreDBImpl<EntryPhoneBook> entryPhoneBookCoreDB;

    @GetMapping
    public ResponseEntity<List<EntryPhoneBook>> getAll() {
        return ResponseEntity.ok(entryPhoneBookCoreDB.getAll(tableName));
    }

    @GetMapping(path = "/{entryId}")
    public ResponseEntity<EntryPhoneBook> getEntryPhoneBook(@PathVariable(value = "entryId") UUID entryId, String tableName) {
        EntryPhoneBook pb = entryPhoneBookCoreDB.getById(tableName, entryId);

        if (pb == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entryPhoneBookCoreDB.getById(tableName, entryId));
    }

    @PostMapping
    public ResponseEntity<EntryPhoneBook> createEntryPhoneBook(@RequestBody EntryPhoneBook entryPB) {

        return ResponseEntity.ok(entryPhoneBookCoreDB.create(tableName, entryPB));
    }

    @PutMapping
    public ResponseEntity<EntryPhoneBook> changeEntryPhoneBook(@RequestBody EntryPhoneBook entryPB) {

        return ResponseEntity.ok(entryPhoneBookCoreDB.change(tableName, entryPB));
    }

    @DeleteMapping
    public ResponseEntity<EntryPhoneBook> deleteEntryPhoneBook(@RequestBody EntryPhoneBook entryPB) {
        entryPhoneBookCoreDB.delete(tableName, entryPB);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "search")
    public ResponseEntity<List<EntryPhoneBook>> searchEntriesForPB(@RequestParam("inText") String inText) {
        List<EntryPhoneBook> userList = entryPhoneBookCoreDB.searchAll(tableName, inText);
        return ResponseEntity.ok(userList);
    }
}
