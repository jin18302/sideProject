package hairSalonReservation.sideProject.domain.reservation.entity;

import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleBlock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "designer_id")
    private Designer designer;

    private LocalDate date;

    private String timeList;


    private ScheduleBlock(Designer designer, LocalDate date, String timeList){
        this.designer = designer;
        this.date = date;
        this.timeList = timeList;
    }

    public static ScheduleBlock of(Designer designer, LocalDate date, String timeList){
        return new ScheduleBlock(designer, date, timeList);
    }

    public void updateTimeSlot(String timeList){
        this.timeList = timeList;
    }
}
