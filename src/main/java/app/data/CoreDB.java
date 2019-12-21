package app.data;

import app.domain.Entity;

import java.util.List;
import java.util.UUID;

public interface CoreDB<T extends Entity> {

    T get(String tableName, T val);
    T getById(String tableName, UUID uuid);
    T create(String tableName, T val);
    T change(String tableName, T val);
    boolean delete(String tableName, T val);
    List<T> getAll(String tableName);
    List<T> searchAll(String tableName, String inText);
}
