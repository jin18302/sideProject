package hairSalonReservation.sideProject.domain.calendar.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private boolean isOff = false;

    private Calendar(LocalDate date){
        this.date = date;
    }

    public static Calendar from(LocalDate date){
        return new Calendar(date);
    }
}
