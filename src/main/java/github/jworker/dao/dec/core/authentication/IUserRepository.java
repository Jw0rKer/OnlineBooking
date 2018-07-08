package github.jworker.dao.dec.core.authentication;

import github.jworker.dao.dec.IGenericRepository;
import github.jworker.model.core.authentication.User;


public interface IUserRepository extends IGenericRepository {
    User findByUserName(String userName);
}
