package main.java.br.com.vignoli.minimal_social_media.Repositories;

import com.senac.produtos.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
