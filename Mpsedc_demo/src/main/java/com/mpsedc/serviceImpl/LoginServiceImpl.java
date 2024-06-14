/*
 * package com.mpsedc.serviceImpl;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * 
 * import com.mpsedc.configuration.MyUserDetails; import
 * com.mpsedc.entity.Login; import com.mpsedc.repository.LoginRepository; import
 * com.mpsedc.service.LoginService;
 * 
 * public class LoginServiceImpl implements LoginService {
 * 
 * @Autowired private LoginRepository loginRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { Login loign =
 * loginRepository.findByUsername(username); if (loign == null) { throw new
 * UsernameNotFoundException("User not found"); } return new
 * MyUserDetails(loign); }
 * 
 * }
 */