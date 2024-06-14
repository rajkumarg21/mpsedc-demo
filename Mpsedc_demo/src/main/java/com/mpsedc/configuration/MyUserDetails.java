/*
 * package com.mpsedc.configuration;
 * 
 * import java.util.Collection;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import com.mpsedc.entity.Login;
 * 
 * 
 * public class MyUserDetails implements UserDetails { private Login login;
 * 
 * public MyUserDetails(Login login) { this.login = login; }
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
 * return null; }
 * 
 * @Override public String getPassword() { return login.getPassword(); }
 * 
 * @Override public String getUsername() { return login.getUsername(); }
 * 
 * @Override public boolean isAccountNonExpired() { return true; }
 * 
 * @Override public boolean isAccountNonLocked() { return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() { return true; }
 * 
 * @Override public boolean isEnabled() { return true; } }
 */