package github.jworker.service.dec.core.authentication;

import github.jworker.model.core.authentication.Authority;

/**
 * @author shayan mirzaee
 */
public interface IAuthorityService {
    Authority findByAuthority(String authority);
}
