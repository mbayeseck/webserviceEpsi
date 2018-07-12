package com.epsi.cloud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCommentInterface extends JpaRepository<Comments, Long>, CrudRepository<Comments, Long>{
	List<Comments> findById(int id);
	List<Comments> findByArticle(String article);
	List<Comments> findByArticleAndId(String article,int id);
}
