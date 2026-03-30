package com.yozakura_minato.kensaku_be.service.implement;

import com.yozakura_minato.kensaku_be.entity.Users;
import com.yozakura_minato.kensaku_be.repository.UserRepository;
import com.yozakura_minato.kensaku_be.exception.message.SignInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailServiceImplement implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param username (string). <i>In this case, it is <b><u>email</u></b></i>.
     * @return (UserDetails)
     * @throws UsernameNotFoundException SignInException.Email.notFound
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users existsEmail = userRepository.findByEmail(username);
        if (Objects.isNull(existsEmail)) {
            throw new UsernameNotFoundException(SignInException.Email.notFound);
        }
        return existsEmail;
    }

}
