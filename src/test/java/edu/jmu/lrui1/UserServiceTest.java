//package edu.jmu.lrui1;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import edu.jmu.lrui1.common.util.SaltUtils;
//import edu.jmu.lrui1.dao.UserDao;
//import edu.jmu.lrui1.entity.User;
//import edu.jmu.lrui1.service.IUserService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.shiro.crypto.hash.SimpleHash;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author: lrui1
// * @description
// * @date: 2023/6/17
// */
//@SpringBootTest
//@Slf4j
//public class UserServiceTest {
//    @Autowired
//    private IUserService userService;
//
//    @Test
//    void testSave() {
//        User user = new User(1L, "lrui1", "lrui1", SaltUtils.getSalt(SaltUtils.count), "15160286018", "开架大飞机", "351200", 2);
//        String newPassword = new SimpleHash("md5", user.getPassword(), user.getSalt(), 1).toString();
//        user.setPassword(newPassword);
//        userService.save(user);
//        List<User> list = userService.list();
//        for(User u : list) {
//            System.out.println(u);
//        }
//
//    }
//
//    @Test
//    void authenticated() {
//        User user = new User();
//        user.setUsername("lrui1");
//        user.setPassword("lrui1");
//        User databaseUser = userService.getUserByUsername(user.getUsername());
//        String salt = databaseUser.getSalt();
//        String newPassword = new SimpleHash("md5", user.getPassword(), salt, 1).toString();
//        if(newPassword.equals(databaseUser.getPassword())) {
////            登录成功
//            System.out.println("登录成功");
//        }
//    }
//    @Test
//    void addMoreUser() {
//        List<User> users = new ArrayList<>();
//        Long id = 2L;
//        for(int i = 0; i < 20; i++) {
//            User user = new User(id, "lrui1", "lrui1", SaltUtils.getSalt(SaltUtils.count), "15160286018", "开架大飞机", "351200", 2);
//            String newPassword = new SimpleHash("md5", user.getPassword(), user.getSalt(), 1).toString();
//            user.setPassword(newPassword);
//            users.add(user);
//            id++;
//        }
//        userService.saveBatch(users);
//    }
//
//    //分页查询
////    @Test
////    void testSelectPage(){
////        IPage<User> page = userService.selectPage(1, 3, null);
////        //3 获取分页结果
////        System.out.println("当前页码值："+page.getCurrent());
////        System.out.println("每页显示数："+page.getSize());
////        System.out.println("一共多少页："+page.getPages());
////        System.out.println("一共多少条数据："+page.getTotal());
////        System.out.println("数据："+page.getRecords());
////    }
//}
