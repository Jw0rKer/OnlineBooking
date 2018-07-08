package github.jworker.service.impl.core.authentication;

import github.jworker.dao.dec.core.authentication.IAuthorityRepository;
import github.jworker.dao.dec.core.authentication.IUserRepository;
import github.jworker.model.core.authentication.Authority;
import github.jworker.model.core.authentication.User;
import github.jworker.service.dec.core.authentication.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IAuthorityRepository iAuthorityRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    @Override
//    public User findByEmail(String email) {
//        return userRepository.findByEmail(email);    }
//
//    @Override
//    public User findUserByEmail(String email) {
//        return null;
//    }
//
//    @Override
//    public void saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setActive(1);
//        Authority userAuthority = AuthorityRepository.findByAuthority("USER");
//        user.setAuthoritys(new HashSet<Authority>(Arrays.asList(userAuthority)));
//        userRepository.persist(user);
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = iUserRepository.findByUserName(username) ;
        String dBuserName = user.getUsername();
        System.out.println(dBuserName);
        if(dBuserName == null){
            throw new UsernameNotFoundException("User not authorized.");
        }

        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
/*
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getAuthorities().toString());
*/
        return user ;


    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.toString();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Authority userAuthority = iAuthorityRepository.findByAuthority("USER");
        user.setAuthorities(new HashSet<Authority>(Arrays.asList(userAuthority)));
        iUserRepository.persist(user);
    }
}
