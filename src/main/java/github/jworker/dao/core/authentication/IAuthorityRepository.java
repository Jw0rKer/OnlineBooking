package github.jworker.dao.core.authentication;

import github.jworker.dao.IGenericRepository;
import github.jworker.model.core.authentication.Authority;

public interface IAuthorityRepository extends IGenericRepository {
    Authority findByAuthority(String authority);

}
