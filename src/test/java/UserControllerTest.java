import app.controllers.UserController;
import app.data.GeneralDBImpl;
import app.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @MockBean
    private GeneralDBImpl<User> userGeneralDB;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReturn200() throws Exception {
        given(userGeneralDB.getById(any())).willReturn(new User("Dima","Privalov"));


    }

    @Test
    public void test() {
        given(this.userGeneralDB.getById(any()))
                .willReturn(new User("Dima", "Ivanov"));
        List<User> userList = userGeneralDB.searchEntries("Di");
        String name = userList.get(0).getName();
        assertEquals(name, "Dima");

    }

}
