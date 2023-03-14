package com.company;

import com.company.Models.Users.Role;
import com.company.Models.Users.Users;
import com.company.Repos.UsersRepos;
import com.company.Services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/** 
* UserService Tester. 
* 
* @author <Authors name> 
* @since <pre>Mar 14, 2023</pre> 
* @version 1.0 
*/

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest { 

    @Autowired
    public UserService userService;
    @Autowired
    public UsersRepos usersRepos;

/** 
* 
* Method: findByLogin(String login) 
* 
*/
    @Test
    public void testFindByLogin() throws Exception {
        Users user = new Users();
        user.setId(1);
        user.setLogin("user");
        user.setPassword("user");
        user.setRole(String.valueOf(Role.USER));
        usersRepos.save(user);
        user = userService.findByLogin("user");
        assertThat(user).isNotNull();
    }
} 
