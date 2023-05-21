package kz.bitlab.treello.services;

import kz.bitlab.treello.entities.Users;
import kz.bitlab.treello.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user= usersRepository.findByEmail(username);
        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }
}
