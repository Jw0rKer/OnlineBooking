package github.jworker.dao.dec.core.authentication;

import github.jworker.dao.dec.IGenericRepository;
import github.jworker.model.core.authentication.Authority;

public interface IAuthorityRepository extends IGenericRepository {
    Authority findByAuthority(String authority);

}
