package hairSalonReservation.sideProject.domain.reservation.entity;

import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import hairSalonReservation.sideProject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Entity
@Table(name = "reservations",
        uniqueConstraints = @UniqueConstraint(name = "uk_designer_slot", columnNames = {"designer_id", "date", "time"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceMenu serviceMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Designer designer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDate date;

    private LocalTime time;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus = ReservationStatus.REQUESTED;

    private Reservation(ServiceMenu serviceMenu, Designer designer, User user, LocalDate date, LocalTime time){
        this.serviceMenu = serviceMenu;
        this.designer = designer;
        this.user = user;
        this.date = date;
        this.time = time;
    }

    public static Reservation of(ServiceMenu serviceMenu, Designer designer, User user, LocalDate date, LocalTime time){
        return new Reservation(serviceMenu, designer, user, date, time);
    }

    public void cancel(){
        this.reservationStatus = ReservationStatus.CANCELLED;
    }

    public void updateReservationStatus(ReservationStatus status){
        this.reservationStatus = status;
    }
}
