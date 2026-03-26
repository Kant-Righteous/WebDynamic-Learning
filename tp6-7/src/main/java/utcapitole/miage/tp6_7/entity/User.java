package utcapitole.miage.tp6_7.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    /* =========================================================
        双向一对多关系配置 (一个用户可以保存多部电影)
        =========================================================
        1. mappedBy = "user":
            告诉 Hibernate，真正的外键维护者是 SavedFilm 类里的 "user" 属性，你去那边找外键。

        2. cascade = CascadeType.ALL (级联操作):
            父母没了，孩子也跟着走。如果从数据库注销/删除了这个 User，
            他名下的所有 SavedFilm 记录也会被自动删除。

        3. orphanRemoval = true (移除孤儿):
            父母还在，但不要这个孩子了。如果在 Java 代码中执行了 user.getSavedFilms().remove(film)，
            Hibernate 会直接从数据库里彻底 DELETE 这条电影记录，绝不留下垃圾数据。
        ========================================================= */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SavedFilm> savedFilms = new ArrayList<>();

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SavedFilm> getSaveFilms() {
        return savedFilms;
    }

    public void setSaveFilms(List<SavedFilm> savedFilms) {
        this.savedFilms = savedFilms;
    }

}
