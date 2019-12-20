package app.data;

import app.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CoreDBImpl<T extends Entity> implements CoreDB<T> {

    @Autowired
    private Map<String, GeneralTableImpl<T>> tables = new HashMap<>();

    public T get(String tableName, T val) {
        return tables.get(tableName).get(val);
    }

    public T getById(String tableName, UUID uuid) {
        return tables.get(tableName).getById(uuid);
    }

    public T create(String tableName, T val) {
        if (!tables.containsKey(tableName)) {
            System.out.println("create table ept");
            tables.put(tableName, new GeneralTableImpl<T>());
        }
        val.setTableName(tableName);
        return tables.get(tableName).add(val);
    }

    public T change(String tableName, T val) {
        return tables.get(tableName).change(val);
    }

    public void delete(String tableName, T val) {
        tables.get(tableName).remove(val);
    }

    public List<T> getAll(String tableName) {
        return tables.get(tableName).getAll();
    }

    public List<T> searchAll(String tableName, String inText) {
        return tables.get(tableName).searchEntries(inText);
    }
}
