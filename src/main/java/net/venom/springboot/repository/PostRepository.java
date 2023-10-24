package net.venom.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.venom.springboot.entity.Post;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long>{
	
	Optional<Post> findByUrl(String url);

	//@Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.brand, ' ', p.madein, ' ', p.price) LIKE %?1%")
	@Query("SELECT p from Post p WHERE "+
			" p.title LIKE CONCAT('%', :query, '%') OR"+
			" p.shortDescription LIKE CONCAT('%', :query, '%')")
	List<Post> searchPosts(String query);

	@Query(value = "select * from posts p where p.created_by =:userId", nativeQuery = true)
	List<Post> findPostByUser(Long userId);
}
