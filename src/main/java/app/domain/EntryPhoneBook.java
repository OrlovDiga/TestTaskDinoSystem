package app.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryPhoneBook implements Entity {

    private UUID id;
    private String name;
    private String value;

    public UUID generateUUID() {
        this.id = UUID.randomUUID();
        return this.id;
    }

    @Override
    public String getVal() {
        return value;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UUID getId() {
        return this.id;
    }

    public void change(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getTable() {
        return "phone_books";
    }
}
