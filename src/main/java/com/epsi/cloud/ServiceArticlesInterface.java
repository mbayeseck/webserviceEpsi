package com.epsi.cloud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ServiceArticlesInterface extends JpaRepository<Articles, Integer> {
	List<Articles> findById(int id);
	List<Articles> findBySlug(String slug);
}
