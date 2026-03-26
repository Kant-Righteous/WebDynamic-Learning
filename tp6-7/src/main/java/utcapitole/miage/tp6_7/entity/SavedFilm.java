package utcapitole.miage.tp6_7.entity;

import jakarta.persistence.*;

@Entity
public class SavedFilm {

    public SavedFilm() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long tmdbFilmId;
    private String title;
    private String posterPath;

    private boolean isFavorite;
    private boolean isToWatch;

    /* =========================================================
        多对一关系配置 (多条电影记录可以属于同一个 User)
        =========================================================
        @ManyToOne: 标明这是多对一的一方。
        @JoinColumn(name = "user_id"): 告诉 Hibernate，在最终生成的
        saved_film 数据表里，一定要建一个名叫 "user_id" 的列来当外键。
    ========================================================= */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTmdbFilmId() {
        return tmdbFilmId;
    }

    public void setTmdbFilmId(Long tmdbFilmId) {
        this.tmdbFilmId = tmdbFilmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public boolean isToWatch() {
        return isToWatch;
    }

    public void setToWatch(boolean toWatch) {
        isToWatch = toWatch;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
