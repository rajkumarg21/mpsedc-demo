/*
 * package com.mpsedc.controller;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.web.bind.annotation.*;
 * 
 * import com.mpsedc.configuration.AuthenticationRequest; import
 * com.mpsedc.configuration.AuthenticationResponse; import
 * com.mpsedc.configuration.JwtUtil; import
 * com.mpsedc.serviceImpl.LoginServiceImpl;
 * 
 * @RestController public class AuthenticationController {
 * 
 * @Autowired private AuthenticationManager authenticationManager;
 * 
 * // @Autowired // private LoginServiceImpl loginServiceImpl; LoginServiceImpl
 * loginServiceImpl =new LoginServiceImpl();
 * 
 * @Autowired private JwtUtil jwtUtil;
 * 
 * @PostMapping("/authenticate") public ResponseEntity<?>
 * createAuthenticationToken(@RequestBody AuthenticationRequest
 * authenticationRequest) throws Exception { try {
 * authenticationManager.authenticate( new
 * UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
 * authenticationRequest.getPassword()) ); } catch (Exception e) { throw new
 * Exception("Incorrect username or password", e); }
 * 
 * final UserDetails userDetails
 * =loginServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
 * final String jwt = jwtUtil.generateToken(userDetails);
 * 
 * return ResponseEntity.ok(new AuthenticationResponse(jwt)); } }
 */