package com.epsi.cloud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ServiceUsersInterface extends JpaRepository<Users, Integer>, CrudRepository<Users, Integer>{
	List<Users> findById(int id);
	List<Users> findAll();
	Users findByUsername(String username);
}
