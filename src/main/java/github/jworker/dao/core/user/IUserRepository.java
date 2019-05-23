package github.jworker.dao.core.user;

import github.jworker.dao.IGenericRepository;
import github.jworker.model.core.user.User;


public interface IUserRepository extends IGenericRepository {
    User findByUserName(String userName);
}
