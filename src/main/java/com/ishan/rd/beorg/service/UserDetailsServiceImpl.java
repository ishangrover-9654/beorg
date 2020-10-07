package com.ishan.rd.beorg.service;

import com.ishan.rd.beorg.domain.entities.User;
import com.ishan.rd.beorg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).
                orElseThrow(() -> new UsernameNotFoundException("User not found with email " + s));
        return  UserDetailsImpl.build(user);
    }
}
