package ua.com.salary.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ua.com.salary.core.common.services.IUserService;
import ua.com.salary.db.entity.User;
import ua.com.salary.db.entity.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Victor Zagnitko on 04.07.2014.
 */
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService mUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.mUserService.findUserByName(username);
        user.setAuthorities(buildUserAuthority(user.getUserRole()));
        return user;
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> roles) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        for (UserRole role : roles) {
            setAuths.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new ArrayList<>(setAuths);
    }

}
