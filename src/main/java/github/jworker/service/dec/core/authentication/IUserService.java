package github.jworker.service.dec.core.authentication;

import github.jworker.model.core.authentication.User;
import org.springframework.security.core.userdetails.UserDetailsService;
/**
 * @author shayan mirzaee
 */
public interface IUserService  extends UserDetailsService {
    void saveUser(User user);
}
