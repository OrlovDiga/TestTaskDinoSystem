package app.domain;

import org.springframework.stereotype.Component;

import java.util.UUID;

public interface Entity {
    UUID getId();
    UUID generateUUID();
    String getVal();
    String getTable();
    void setTable(String tableName);
}
