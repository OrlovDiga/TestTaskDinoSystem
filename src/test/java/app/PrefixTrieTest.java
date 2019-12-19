package app;

import app.data.index.PrefixTrie;
import com.sun.tools.javac.util.List;
import lombok.ToString;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PrefixTrieTest {
    PrefixTrie trie = new PrefixTrie();

    public String getRandomWord() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

    @Before
    public void useBefore() {
        int count = (int) (1 + Math.random()*100);
        for (int i = 0; i < count; i++) {
            trie.insert(getRandomWord(), UUID.randomUUID());
        }
        trie.insert("Jon", UUID.fromString("d03e19ab-b9bd-403c-bf6d-0d5477b34fea"));
        trie.insert("Jon", UUID.fromString("1c95ab3f-370a-4526-a950-6429657fa40a"));
        trie.insert("Jo", UUID.fromString("7b2157ea-21ab-4ef0-85ab-807aa00e72d3"));
        trie.insert("Jin", UUID.fromString("02c36074-9c96-4a6e-aed2-5cd5cd9c192f"));
        trie.insert("molly", UUID.fromString("04b582a0-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Peter", UUID.fromString("04b5850c-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Max", UUID.fromString("04b59538-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Ann", UUID.fromString("04b59100-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Eva",UUID.fromString("04b58c0a-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Tanya", UUID.fromString("2413ffdc-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Kris", UUID.fromString("241403c4-22a1-11ea-978f-2e728ce88125"));
        trie.insert("gold", UUID.fromString("241411e8-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Kate", UUID.fromString("241413c8-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Vlad", UUID.fromString("24142728-22a1-11ea-978f-2e728ce88125"));
        trie.insert("Jack", UUID.fromString("fff61bf7-6c35-49a2-b697-05cf3e8bb104"));

    }

    @Test
    public void insertTesting() {
        trie.insert("Lol", UUID.randomUUID());

        assertEquals('l', trie.searchNode("Lol").getC());
    }

    @Test
    public void removeTesting() {
        assertFalse(trie.delete("Lole", UUID.randomUUID()));
    }

    @Test
    public void searchAllTesting() {
        assertEquals(trie.searchAll("J"), Arrays.asList(
                UUID.fromString("fff61bf7-6c35-49a2-b697-05cf3e8bb104"),
                UUID.fromString("02c36074-9c96-4a6e-aed2-5cd5cd9c192f"),
                UUID.fromString("7b2157ea-21ab-4ef0-85ab-807aa00e72d3"),
                UUID.fromString("d03e19ab-b9bd-403c-bf6d-0d5477b34fea"),
                UUID.fromString("1c95ab3f-370a-4526-a950-6429657fa40a")
                ));
    }
}
