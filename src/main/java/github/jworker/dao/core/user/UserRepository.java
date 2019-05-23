package github.jworker.dao.core.user;

import github.jworker.dao.GenericRepository;
import github.jworker.model.core.user.User;
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
        String hql = " from  " + objectClass.getName() + " e where e.username = :username  ";
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

