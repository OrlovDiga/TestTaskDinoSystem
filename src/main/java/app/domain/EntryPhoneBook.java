package app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryPhoneBook implements Entity {

    private UUID id;
    @JsonIgnore
    private String tableName;
    private String name;
    private String value;
    @JsonProperty(value = "user_id")
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
    public String getTableName() {
        return tableName;
    }

    @Override
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void change(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
