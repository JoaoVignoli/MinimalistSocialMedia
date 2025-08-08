package br.com.vignoli.minimal_social_media.Repositories;

import br.com.vignoli.minimal_social_media.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
