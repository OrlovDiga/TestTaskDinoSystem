package app.data;

import app.domain.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class GeneralDBImplTest {
    @Autowired
    private GeneralDBImpl<User> userGeneralDB;

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
