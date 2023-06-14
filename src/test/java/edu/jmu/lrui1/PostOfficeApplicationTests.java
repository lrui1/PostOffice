package edu.jmu.lrui1;

import edu.jmu.lrui1.entity.User;
import edu.jmu.lrui1.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostOfficeApplicationTests {
    @Autowired
    private IUserService iUserService;

    @Test
    void contextLoads() {
        User user = new User(1L, "lrui1", "lrui1", 1L);
        iUserService.save(user);
        List<User> list = iUserService.list();
        System.out.println(list);
    }

}
