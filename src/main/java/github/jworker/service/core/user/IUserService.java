package github.jworker.service.core.user;

import github.jworker.model.core.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;
public interface IUserService  extends UserDetailsService {
    void saveUser(User user);
}
