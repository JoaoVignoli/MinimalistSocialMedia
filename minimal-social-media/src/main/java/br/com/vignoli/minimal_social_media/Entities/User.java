package main.java.br.com.vignoli.minimal_social_media.Entities;

import java.time.LocalDate;
import java.util.ArrayList;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Strign email;
    private LocalDate registerDate;

    @OneToMany(mappedBy = "User", cascade=CascadeType.ALL)
    private ArrayList<Post> userPosts = new ArrayList<>();
    @OneToMany(mappedBy = "User", cascade=CascadeType.ALL)
    private ArrayList<Like> userLikes = new ArrayList<>();
    @OneToMany(mappedBy = "User", cascade=CascadeType.ALL)
    private ArrayList<Comment> userComments = new ArrayList<>();


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

    public Strign getEmail() {
        return email;
    }

    public void setEmail(Strign email) {
        this.email = email;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public ArrayList<Post> getUserPosts() {
        return userPosts;
    }

    public void setUserPosts(ArrayList<Post> userPosts) {
        this.userPosts = userPosts;
    }
    public ArrayList<Like> getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(ArrayList<Like> userLikes) {
        this.userLikes = userLikes;
    }

    public ArrayList<Comment> getUserComments() {
        return userComments;
    }

    public void setUserComments(ArrayList<Comment> userComments) {
        this.userComments = userComments;
    }
    
}
