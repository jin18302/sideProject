package hairSalonReservation.sideProject.review.entity;

import hairSalonReservation.sideProject.common.entity.BaseEntity;
import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;
import hairSalonReservation.sideProject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "review_id")
    private Reservation reservation;

    private String title;

    private String content;

    private Integer rating;

    private Review(User user, Reservation reservation, String title, String content, Integer rating){
        this.user = user;
        this.reservation = reservation;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public static Review of(User user, Reservation reservation, String title, String content, Integer rating){
        return new Review(user, reservation, title, content, rating);
    }

    public void update(String title, String content, Integer rating){
        this.title = title;
        this.content = content;
        this.rating = rating;
    }
}
