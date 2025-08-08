package br.com.vignoli.minimal_social_media.Controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.vignoli.minimal_social_media.Dtos.PostResponseDto;
import br.com.vignoli.minimal_social_media.Dtos.UserResposeDto;
import br.com.vignoli.minimal_social_media.Dtos.UserResquestDto;
import br.com.vignoli.minimal_social_media.Entities.Post;
import br.com.vignoli.minimal_social_media.Entities.User;
import br.com.vignoli.minimal_social_media.Repositories.PostRepository;
import br.com.vignoli.minimal_social_media.Repositories.UserRepository;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/users")
public class UserController {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public ResponseEntity<UserResposeDto> createUser (
       @RequestBody UserResquestDto request
    ) {
        User userSave = new User();
        userSave.setName(request.name());
        userSave.setEmail(request.email());
        userSave.setRegisterDate(LocalDate.now());

        var userSaved = this.userRepository.save(userSave);

        UserResposeDto userResposeDto = new UserResposeDto(
            userSaved.getId(), userSaved.getName(), userSaved.getEmail(), userSaved.getRegisterDate()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(userResposeDto);
    }

    @GetMapping
    public List<User> getUsers() {
        return this.userRepository.findAll();
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUser(@PathVariable Integer userId) {

        Optional<User> userRespose = this.userRepository.findById(userId);

        if (userRespose.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(userRespose.get());
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<PostResponseDto>> getUserPosts(@PathVariable Integer userId) {

        Optional<User> author = this.userRepository.findById(userId);

        if (author.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<PostResponseDto> posts = this.postRepository.findAllByAuthor_Id(userId);
        
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<Object> alterUser(@PathVariable Integer userId, @RequestBody UserResquestDto entity) {
        
        Optional<User> userAlter = this.userRepository.findById(userId);
        
        if (userAlter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userAlter.get().setEmail(entity.email());
        userAlter.get().setName(entity.name());

        this.userRepository.save(userAlter.get());

        return ResponseEntity.status(HttpStatus.OK).body(userAlter);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        Optional<User> userDelete = this.userRepository.findById(userId);

        if (userDelete.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        this.userRepository.deleteById(userId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
