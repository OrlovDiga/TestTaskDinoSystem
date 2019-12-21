package app.data;

import app.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CoreDBImpl<T extends Entity> implements CoreDB<T> {

    private Map<String, GeneralTableImpl<T>> tables = new HashMap<>();

    public T get(String tableName, T val) {
        return tables.containsKey(tableName) ? tables.get(tableName).get(val) : null;
    }

    public T getById(String tableName, UUID uuid) {
        return tables.containsKey(tableName) ? tables.get(tableName).getById(uuid) : null;
    }

    public T create(String tableName, T val) {
        if (!tables.containsKey(tableName)) {
            tables.put(tableName, new GeneralTableImpl<T>());
        }
        val.setTableName(tableName);
        return tables.get(tableName).add(val);
    }

    public T change(String tableName, T val) {
        return tables.containsKey(tableName) ? tables.get(tableName).change(val) : null;
    }

    public boolean delete(String tableName, T val) {
        return tables.containsKey(tableName) && tables.get(tableName).remove(val);
    }

    public List<T> getAll(String tableName) {
        return tables.containsKey(tableName) ? tables.get(tableName).getAll() : null;
    }

    public List<T> searchAll(String tableName, String inText) {
        return tables.containsKey(tableName) ? tables.get(tableName).searchEntries(inText) : null;
    }
}
