package sia.tacocloud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sia.tacocloud.dao.User;
import sia.tacocloud.repositories.UserRepository;

public class UserRepositoryUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserRepositoryUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.fingByUsername(username);
    if (user != null) {
      return user;
    }

    throw new UsernameNotFoundException("User '" + username + "' not found");
  }
}
