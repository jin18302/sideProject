package hairSalonReservation.sideProject.domain.reservation.entity;

import hairSalonReservation.sideProject.domain.calendar.entity.Slot;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import hairSalonReservation.sideProject.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Slot slot;

    @ManyToOne(fetch = FetchType.LAZY)
    private ServiceMenu serviceMenu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Designer designer;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus = ReservationStatus.REQUESTED;

    private Reservation(Slot slot, ServiceMenu serviceMenu, Designer designer, User user){
        this.slot = slot;
        this.serviceMenu = serviceMenu;
        this.designer = designer;
        this.user = user;
    }

    public static Reservation of(Slot slot, ServiceMenu serviceMenu, Designer designer, User user){
        return new Reservation(slot, serviceMenu, designer, user);
    }
}
