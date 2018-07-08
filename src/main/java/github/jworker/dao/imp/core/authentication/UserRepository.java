package github.jworker.dao.imp.core.authentication;

import github.jworker.dao.dec.core.authentication.IUserRepository;
import github.jworker.dao.imp.GenericRepository;
import github.jworker.model.core.authentication.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;


@Repository
public class UserRepository extends GenericRepository implements IUserRepository {
    protected Class objectClass = User.class;

    @Override
    public User findByUserName(String username) {
        Map<String, Object> map = new HashMap<>();
        String hql = " from  " + objectClass.getName() + " e where e.username like :username  ";
        map.put("username", username);
        Query query = getQuery(hql, map);
        User user ;
        try{
             user = (User) query.getSingleResult() ;
        }catch (NoResultException nre){
            user = null ;
        }
      return user;
    }



}

