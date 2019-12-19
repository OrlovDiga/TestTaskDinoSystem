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
    private String tableName;
    private String name;
    private String value;
    private UUID userId;

    public UUID generateUUID() {
        this.id = UUID.randomUUID();
        return this.id;
    }

    @Override
    public String getVal() {
        return value;
    }

    @Override
    public String getTable() {
        return null;
    }

    public UUID getId() {
        return this.id;
    }

    public void change(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
