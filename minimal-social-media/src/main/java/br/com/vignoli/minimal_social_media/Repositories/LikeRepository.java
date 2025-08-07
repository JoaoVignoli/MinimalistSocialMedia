package main.java.br.com.vignoli.minimal_social_media.Repositories;

import com.senac.produtos.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {

}
