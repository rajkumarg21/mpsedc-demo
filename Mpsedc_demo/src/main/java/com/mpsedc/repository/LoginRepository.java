package com.mpsedc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mpsedc.entity.Login;

@Repository
public interface LoginRepository  extends JpaRepository<Login,Long>{

	Login findByUsername(String username);

}
