package hairSalonReservation.sideProject.domain.reservation.entity;

import com.fasterxml.jackson.core.type.TypeReference;
import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    private boolean isDayOff = false;

    private String timeList = "[]";

    private ScheduleBlock(Designer designer, LocalDate date, String timeList,  boolean isDayOff){
        this.designer = designer;
        this.date = date;
        this.timeList = timeList;
        this.isDayOff = isDayOff;
    }

    public static ScheduleBlock of(Designer designer, LocalDate date, String timeList, boolean isDayOff){
        return new ScheduleBlock(designer, date, timeList, isDayOff);
    }

    public void addTimeSlot(List<LocalTime> timeList){

      List<LocalTime> list =  JsonHelper.fromJsonToList(this.getTimeList(), new TypeReference<List<LocalTime>>(){});
      list.addAll(timeList);
      this.timeList = JsonHelper.toJson(list);
    }

    public void deleteTimeSlot(LocalTime time){
        List<LocalTime> list =  JsonHelper.fromJsonToList(this.getTimeList(), new TypeReference<List<LocalTime>>(){});
        list.remove(time);
        this.timeList = JsonHelper.toJson(list);
    }

    public void setIsDayOff(boolean isDayOff){
        this.isDayOff = isDayOff;
    }
}
