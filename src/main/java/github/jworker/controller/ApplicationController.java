/**
 *
 */
package github.jworker.controller;

import github.jworker.model.core.authentication.User;
import github.jworker.service.dec.core.authentication.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
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

  @Autowired private IUserService iUserService ;

    private RequestCache requestCache = new HttpSessionRequestCache();


  @GetMapping("/")
  public String homePage(Model model ) {
    return "main";
  }



  /*@GetMapping("/login")
  public String  login(Model model ){
    return "/login";
  }*/
  @RequestMapping("/login")
  public String login(ModelMap model) {
      System.out.println("In the login method");
        return "login";
    }



  /*  @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, @ModelAttribute User loginForm,
                        BindingResult result) throws ServletException {
        try {
            request.login(loginForm.getUsername(), loginForm.getPassword());
            SavedRequest savedRequest = requestCache.getRequest(request, response);
            if (savedRequest != null) {
                return "redirect:" + savedRequest.getRedirectUrl();
            } else {
                return "redirect:/";
            }

        } catch (ServletException authenticationFailed) {
            result.rejectValue(null, "authentication.failed");
            return "login";
        }
    }
*/


  @GetMapping("/registration")
 public ModelAndView registration(){
    ModelAndView modelAndView = new ModelAndView();
    User user = new User();
    modelAndView.addObject("user", user);
    modelAndView.setViewName("registration");
    return modelAndView;
  }

  @PostMapping("/registration")
  public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();
      System.out.println(user.getUsername());
      User userExists = (User) iUserService.loadUserByUsername(user.getUsername());
    if (userExists != null) {
      bindingResult.rejectValue("username", "error.username",
                      "There is already a user registered with the email provided");
    }
    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("registration");

    } else {
      iUserService.saveUser(user);
/*
      securityConfig.autologin(user);
*/

      modelAndView.addObject("successMessage", "User has been registered successfully");
      modelAndView.addObject("user", new User());
      modelAndView.setViewName("registration");

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
