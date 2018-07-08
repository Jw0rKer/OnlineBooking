package github.jworker.service.impl.core.authentication;

import github.jworker.model.core.authentication.Authority;
import github.jworker.service.dec.core.authentication.IAuthorityService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService implements IAuthorityService {

    @Override
    public Authority findByAuthority(String Authority) {
        return null;
    }
}
