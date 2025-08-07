package main.java.br.com.vignoli.minimal_social_media.Repositories;

import com.senac.produtos.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
