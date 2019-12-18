package app.data;

import app.domain.Entity;
import app.data.index.PrefixTrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GeneralDBImpl<T extends Entity> implements GeneralDB<T> {
    private Map<String, Map<UUID, T>> entries = new HashMap<>();

    @Autowired
    private PrefixTrie trie;

    @Override
    public T get(T val) {
        return getById(val.getId(), val.getTable());
    }

    @Override
    public T getById(UUID uuid, String tableName) {
        return  getTable(tableName).get(uuid);
    }


    public Map<UUID, T> getTable(String nameTable) {
        return entries.get(nameTable);
    }

    @Override
    public T add(T val) {
        trie.insert(val.getVal(), val.generateUUID());
        //mb get a ne put
        return getTable(val.getTable()).put(val.getId(), val);
    }

    @Override
    public T change(T val) {
        trie.delete(val.getVal(), val.getId());
        trie.insert(val.getVal(), val.getId());
        return getTable(val.getTable()).put(val.getId(), val);
    }

    @Override
    public void remove(T val) {
        trie.delete(val.getVal(), val.getId());
        getTable(val.getTable()).remove(val.getId());
    }

    @Override
    public List<T> getAll(String tableName) {
        return new ArrayList<T>(entries.get(tableName).values());
    }

    public List<T> searchUUIDS(String inText, String tableName) {

        return trie.searchAll(inText).stream()
                                     .map((uuid) -> entries.get(uuid))
                                     .collect(Collectors.toList());
    }
}
