package app.data.index;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PrefixTrie {
    private NodeOfTrie root = new NodeOfTrie();

    public void insert(String word, UUID id) {
        Map<Character, NodeOfTrie> childs = root.getChildren();

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            NodeOfTrie t;

            if (childs.containsKey(c)) {
                t = childs.get(c);
            } else {
                t = new NodeOfTrie(c);
                childs.put(c, t);
            }

            childs = t.getChildren();

            if (i == word.length() - 1) {
                t.setLeaf(true);
                t.addIdForLead(id);
            }
        }
    }

    public NodeOfTrie searchNode(String word) {
        NodeOfTrie p = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            p = p.getChildren().get(c);

            if (p == null) {
                return null;
            }
        }

        if (p == root) {
            return null;
        }

        return p;
    }

    public List<UUID> searchAll(String word) {
        List<UUID> result = new ArrayList<>();

        NodeOfTrie p = searchNode(word);

        if (p == null) {
            return result;
        }

        if (p.isLeaf()) {
            result.addAll(p.getIdListForLeaf());
            return result;
        }

        checkNode(result, p);

        return result;
    }

    public void checkNode(List<UUID> res, NodeOfTrie nodeTrie) {
        if (nodeTrie.isLeaf()) {
            res.addAll(nodeTrie.getIdListForLeaf());
        }

        if (nodeTrie.getChildren() != null) {
            for (Character c: nodeTrie.getChildren().keySet()) {
                NodeOfTrie temp = nodeTrie.getChildren().get(c);
                    checkNode(res, temp);
            }
        }
    }

    public void delete(String word, UUID uuid) {
        delete(root, word, 0, uuid);
    }

    private boolean delete(NodeOfTrie current, String word, int index, UUID uuid) {
        if (index == word.length()) {
            if (!current.isLeaf()) {
                return false;
            }

            current.setLeaf(false);
            return current.getChildren().isEmpty();
        }
        char ch = word.charAt(index);
        NodeOfTrie node = current.getChildren().get(ch);

        if (node == null) {
            return false;
        }

        boolean shouldDeleteCurrentNode = delete(node, word, index + 1, uuid) && !node.isLeaf();

        if (shouldDeleteCurrentNode) {
            if (current.getChildren().get(ch).sizeIdList() == 1) {
                current.getChildren().remove(ch);
                return current.getChildren().isEmpty();
            } else {
                current.getChildren().get(ch).deleteId(uuid);
                return !current.getChildren().isEmpty();
            }
        }
        return false;
    }
}
