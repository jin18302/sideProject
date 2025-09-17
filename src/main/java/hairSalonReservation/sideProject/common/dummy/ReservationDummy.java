package hairSalonReservation.sideProject.common.dummy;

import net.datafaker.Faker;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ReservationDummy {

    public static Faker faker = new Faker();
    public static Random random = new Random();


    public static void main(String[] args) {

        List<String> dataList = new ArrayList<>();

         String RESERVATION_TABLE_NAME = "reservations";
         String RESERVATION_COLUMNS = "id,service_menu_id,designer_id,user_id,date,time";

        for(int i = 1 ; i < 300_000 ; i++){

            StringBuffer stringBuffer = new StringBuffer();

            //id
            stringBuffer.append(i);
            stringBuffer.append(", ");

            //menu
            stringBuffer.append(random.nextLong(4_000_000) + 1);
            stringBuffer.append(", ");

            //user
            stringBuffer.append(random.nextLong(100_000) + 1);
            stringBuffer.append(", ");

            //designer
            stringBuffer.append(random.nextLong(300_000) + 1);
            stringBuffer.append(", ");

            //date

            long date =  random.nextLong(
                    LocalDate.of(2025, 1, 1).toEpochDay(),
                    LocalDate.of(2025, 12, 31).toEpochDay()
            );

            stringBuffer.append(LocalDate.ofEpochDay(date));
            stringBuffer.append(", ");

            //time
            long time = random.nextLong(0, 86_399_999_999_999L);
            stringBuffer.append(LocalTime.ofNanoOfDay(time));
            stringBuffer.append(", ");

            dataList.add(stringBuffer.toString());
        }

        CSVGenerator.generate(RESERVATION_TABLE_NAME, RESERVATION_COLUMNS, dataList);

    }
}
