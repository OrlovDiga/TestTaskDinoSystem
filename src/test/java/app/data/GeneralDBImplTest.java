package app.data;

import app.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GeneralDBImplTest {
    @Autowired
    private GeneralTableImpl<User> userGeneralDB;

    @Before
    public void before() {
        User temp = new User("Dima", "Privalov");
         userGeneralDB.add(temp);
    }

    @Test
    public void get() {

    }

    @Test
    public void getById() {
    }

    @Test
    public void add() {
    }

    @Test
    public void change() {
    }

    @Test
    public void remove() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void searchUUIDS() {
    }
}
