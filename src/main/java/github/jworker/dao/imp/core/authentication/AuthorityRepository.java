package github.jworker.dao.imp.core.authentication;

import github.jworker.dao.dec.core.authentication.IAuthorityRepository;
import github.jworker.dao.imp.GenericRepository;
import github.jworker.model.core.authentication.Authority;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;
@Repository
public class AuthorityRepository extends GenericRepository implements IAuthorityRepository {
    protected Class objectClass = Authority.class;

    @Override
    public Authority findByAuthority(String authority) {
        Map<String, Object> map = new HashMap<>();
        String hql = "from  "+ objectClass.getName() + " e where e.authority = :authority " ;
        map.put("authority" , authority) ;
        Query query = getQuery(hql, map);
        return (Authority) query.getSingleResult();
    }
}
