package com.javaee.ecard.service;

import com.javaee.ecard.mapper.UsersMapper;
import com.javaee.ecard.model.User;
import com.javaee.ecard.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UsersMapper usersMapper;

    @Transactional(readOnly = true)
    public SecurityUser loadUserByUsername(String username) {
        User user = usersMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }

//       to_do: List<String> roles = usersMapper.findRolesByUserId(userEntity.getId());
        Set<String> roles = Set.of("ADMIN","USER");
        return new SecurityUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                roles,
                true
        );
    }

}