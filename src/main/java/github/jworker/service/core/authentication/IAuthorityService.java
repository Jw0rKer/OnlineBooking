package github.jworker.service.core.authentication;

import github.jworker.model.core.authentication.Authority;

public interface IAuthorityService {
    Authority findByAuthority(String authority);
}
