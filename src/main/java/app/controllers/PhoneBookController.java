package app.controllers;

import app.data.CoreDBImpl;
import app.domain.EntryPhoneBook;
import app.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/pb/{idPB}")
public class PhoneBookController {
    private String tableName;

    @HandleBeforeCreate
    public void setTableName(@PathVariable(value = "idPB") String path) {
        this.tableName = path;
    }

    @Autowired
    private CoreDBImpl<EntryPhoneBook> entryPhoneBookCoreDB;

    @GetMapping
    public ResponseEntity<List<EntryPhoneBook>> getAll() {
        List<EntryPhoneBook> result = entryPhoneBookCoreDB.getAll(tableName);

        return (result != null && !result.isEmpty()) ?
                ResponseEntity.ok(result) :
                ResponseEntity.notFound().build();
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
        return isFilled(entryPB) ?
                ResponseEntity.ok(entryPhoneBookCoreDB.create(tableName, entryPB)) :
                ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<EntryPhoneBook> changeEntryPhoneBook(@RequestBody EntryPhoneBook entryPB) {
        EntryPhoneBook res = entryPhoneBookCoreDB.change(tableName, entryPB);

        return (isFilled(entryPB) && res != null) ?
                ResponseEntity.ok(entryPhoneBookCoreDB.change(tableName, entryPB)) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity<EntryPhoneBook> deleteEntryPhoneBook(@RequestBody EntryPhoneBook entryPB) {
        return entryPhoneBookCoreDB.delete(tableName, entryPB) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }

    @GetMapping(path = "search")
    public ResponseEntity<List<EntryPhoneBook>> searchEntriesForPB(@RequestParam("inText") String inText) {
        List<EntryPhoneBook> entryPhoneBookList = entryPhoneBookCoreDB.searchAll(tableName, inText);

        if (entryPhoneBookList == null || entryPhoneBookList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(entryPhoneBookList);
    }

    private boolean isFilled(EntryPhoneBook entryPhoneBook) {
        return entryPhoneBook != null &&
                entryPhoneBook.getName() != null && !entryPhoneBook.getName().equals("") &&
                entryPhoneBook.getValue() != null && !entryPhoneBook.getValue().equals("") ;
    }
}
