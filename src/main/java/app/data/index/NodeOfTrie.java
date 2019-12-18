package app.data.index;

import lombok.Data;

import java.util.*;

@Data
public class NodeOfTrie {
    private char c;
    private Map<Character, NodeOfTrie> children = new HashMap<>();
    private boolean isLeaf = false;
    private List<UUID> idListForLeaf = new ArrayList<>();

    public NodeOfTrie() { }

    public NodeOfTrie(char c) {
        this.c = c;
    }

    public void addIdForLead(UUID uuid) {
        idListForLeaf.add(uuid);
    }

    public int sizeIdList() {
        return idListForLeaf.size();
    }

    public void deleteId(UUID uuid) {
        idListForLeaf.remove(uuid);
    }
}
