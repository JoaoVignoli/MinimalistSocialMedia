package main.java.br.com.vignoli.minimal_social_media.Entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(mappedBy = "Post", cascade=CascadeType.ALL)
    private ArrayList<Like> postLikes = new ArrayList<>();
    @OneToMany(mappedBy = "Post", cascade=CascadeType.ALL)
    private ArrayList<Comment> postComments = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public ArrayList<Like> getPostLikes() {
        return postLikes;
    }

    public void setPostLikes(ArrayList<Like> postLikes) {
        this.postLikes = postLikes;
    }

    public ArrayList<Comment> getPostComments() {
        return postComments;
    }

    public void setPostComments(ArrayList<Comment> postComments) {
        this.postComments = postComments;
    }

}
