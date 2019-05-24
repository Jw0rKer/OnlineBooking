/**
 *
 */
package github.jworker.controller;

import github.jworker.model.core.user.User;
import github.jworker.service.core.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * @author JK
 */
@Controller()
public class ApplicationController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/")
    public String homePage(Model model) {
        return "index";
    }

    @RequestMapping("/login")
    public String login(ModelMap model) {
        return "login";
    }

    @GetMapping("/register")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @PostMapping("/singUp")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(user.getUsername());
        try {
            User userExists = (User) iUserService.loadUserByUsername(user.getUsername());
            bindingResult.rejectValue("username", "error.username",
                    "There is already a user registered with the email provided");
            modelAndView.setViewName("error");
        } catch (UsernameNotFoundException e) {
            iUserService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("index");
        }
        return modelAndView;
    }


    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        return mav;
    }


}
