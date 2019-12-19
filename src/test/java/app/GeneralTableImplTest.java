package app;

import app.data.GeneralTableImpl;
import app.domain.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class GeneralTableImplTest {

    List<User> users = new ArrayList<>();
    GeneralTableImpl<User> userGeneralTable;

    @Before
    public void useBefore() {
        userGeneralTable = new GeneralTableImpl<>();

        users.add(userGeneralTable.add(new User("Oleg", "Sokol")));
        users.add(userGeneralTable.add(new User("Fufel", "Finlandov")));
        users.add(userGeneralTable.add(new User("Kek", "Lolov")));


    }

    @Test
    public void addTesting() {
        User temp = userGeneralTable.add(new User("Zhora", "Kolosov"));
        assertEquals(temp.getName(), "Zhora");
        assertEquals(temp.getSurname(), "Kolosov");
        assertNotNull(temp.getId());
        assertNotNull(temp.getPhoneBookId());
        assertEquals(temp, userGeneralTable.get(temp));
    }

    @Test
    public void removeTesting() {
        assertEquals(userGeneralTable.get(users.get(0)), users.get(0));
        userGeneralTable.remove(users.get(0));
        assertNotEquals(userGeneralTable.get(users.get(0)), users.get(0));
    }

    @Test
    public void changeTesting() {
        User temp = users.get(0);
        UUID tempPBId = temp.getPhoneBookId();
        assertEquals(userGeneralTable.get(temp), temp);
        temp.setName("Johan");
        temp.setSurname("Jembo");
        userGeneralTable.change(temp);
        assertEquals(userGeneralTable.get(temp).getSurname(), "Jembo");
        assertEquals(userGeneralTable.get(temp).getName(), "Johan");
        assertEquals(userGeneralTable.get(temp).getPhoneBookId(), tempPBId);
    }

    @Test
    public void getTesting() {
        assertEquals(userGeneralTable.get(users.get(0)), users.get(0));
        assertNotEquals(userGeneralTable.get(users.get(0)), users.get(1));
        assertEquals(userGeneralTable.getById(users.get(0).getId()), users.get(0));
    }

    @Test
    public void getAllTesting() {
        List<User> tempUsers = users;
        tempUsers.addAll(userGeneralTable.getAll());
        assertEquals(users, tempUsers);
    }
}
