package hairSalonReservation.sideProject.domain.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Slot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Calendar calendar;

    private LocalTime time;

    private boolean isReservable = true;

    private Slot(Calendar calendar, LocalTime time){
        this.calendar = calendar;
        this.time = time;
    }

    public static Slot of(Calendar calendar, LocalTime time){
        return new Slot(calendar, time);
    }
}
