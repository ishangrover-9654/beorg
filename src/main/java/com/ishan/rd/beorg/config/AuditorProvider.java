package com.ishan.rd.beorg.config;

import com.ishan.rd.beorg.domain.entities.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@Getter @Setter
public class AuditorProvider implements AuditorAware<User> {

    private User user;
    @Override
    public Optional<User> getCurrentAuditor() {
        return Optional.ofNullable(user);
    }
}
