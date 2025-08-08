package br.com.vignoli.minimal_social_media.Entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private LocalDate registerDate;

    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Post> userPosts = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Like> userLikes = new ArrayList<>();
    @OneToMany(mappedBy = "author", cascade=CascadeType.ALL)
    @JsonIgnore
    private List<Comment> userComments = new ArrayList<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public List<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(List<Post> userPosts) {
        this.userPosts = userPosts;
    }
    public List<Like> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(List<Like> userLikes) {
        this.userLikes = userLikes;
    }

    public List<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(List<Comment> userComments) {
        this.userComments = userComments;
    }
    
}
