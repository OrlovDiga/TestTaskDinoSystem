package app.data;

import app.domain.Entity;
import app.data.index.PrefixTrie;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GeneralTableImpl<T extends Entity> implements GeneralTable<T> {
    private Map<UUID, T> entries = new HashMap<>();

    private PrefixTrie trie = new PrefixTrie();

    public T get(T val) {
        return entries.getOrDefault(val.getId(), null);
    }

    public T getById(UUID id) {
        return entries.getOrDefault(id, null);
    }

    public T add(T val) {
        trie.insert(val.getVal(), val.generateUUID());
        entries.put(val.getId(), val);

        return entries.get(val.getId());
    }

    public T change(T val) {
        if (entries.containsKey(val.getId())) {
            trie.delete(val.getVal(), val.getId());
            trie.insert(val.getVal(), val.getId());
            entries.put(val.getId(), val);

            return entries.get(val.getId());
        }

        return null;
    }

    public boolean remove(T val) {
        if (entries.containsKey(val.getId())) {
            trie.delete(val.getVal(), val.getId());
            entries.remove(val.getId());

            return true;
        }

        return false;
    }

    public List<T> getAll() {
        return new ArrayList<T>(entries.values());
    }

    public List<T> searchEntries(String inText) {
        return trie.searchAll(inText).stream()
                                     .map((uuid) -> entries.get(uuid))
                                     .collect(Collectors.toList());
    }
}
