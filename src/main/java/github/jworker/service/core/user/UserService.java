package github.jworker.service.core.user;

import github.jworker.dao.core.authentication.IAuthorityRepository;
import github.jworker.dao.core.user.IUserRepository;
import github.jworker.model.core.authentication.Authority;
import github.jworker.model.core.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IAuthorityRepository iAuthorityRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        Authority userAuthority = iAuthorityRepository.findByAuthority("USER");
        Authority authority = new Authority();
        authority.setAuthority("ROLE_USER");
        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(new Authority());
        user.setAuthorities(authoritySet);
        iUserRepository.persist(user);
    }
}
