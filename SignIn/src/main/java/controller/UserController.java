package controller;

import org.springframework.web.servlet.ModelAndView;
import pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.userService.UserService;

/**
 * Created by user on 2017/3/5.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("HelloWord")
    public ModelAndView insertUser(User user)
    {
        userService.insertUser(user);
        ModelAndView modelAndView =  new ModelAndView();
        modelAndView.setViewName("success.html");
        return modelAndView;
    }
}
