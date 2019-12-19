package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class User implements Entity {

    @JsonProperty(value = "id")
    private UUID id;
    private String tableName;
    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "surname")
    private String surname;
    @JsonProperty(value = "phone_book_id")
    private UUID phoneBookId;
    @JsonIgnore
    private String val;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public UUID generateUUID() {
            this.id = UUID.randomUUID();
            this.phoneBookId = UUID.randomUUID();
            return this.id;
    }

    @Override
    public String getVal() {
        return name;
    }

    public void change(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getTable() {
        return "users";
    }

    @Override
    public void setTable(String tableName) {
        this.tableName = tableName;

    }
}
