package app.data;

import app.domain.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class HeadDBImpl<T extends Entity> implements HeadDB<T> {

    @Autowired
    private Map<String, GeneralTableImpl<T>> tables = new HashMap<>();

    @Override
    public T get(String tableName, T val) {
        return tables.get(tableName).get(val);
    }

    @Override
    public T getById(String tableName, UUID uuid) {
        return tables.get(tableName).getById(uuid);
    }

    @Override
    public T create(String tableName, T val) {
        if (!tables.containsKey(tableName)) {
            tables.put(tableName, new GeneralTableImpl<T>());
        }
        val.setTable(tableName);
        return tables.get(tableName).add(val);
    }

    @Override
    public T change(String tableName, T val) {
        return tables.get(tableName).change(val);
    }

    @Override
    public void delete(String tableName, T val) {
        tables.get(tableName).remove(val);
    }

    @Override
    public List<T> getAll(String tableName) {
        return tables.get(tableName).getAll();
    }

    @Override
    public List<T> searchAll(String tableName, String inText) {
        return tables.get(tableName).searchEntries(inText);
    }
}
