package hairSalonReservation.sideProject.dummy;

import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReviewDummy {

    public static List<String> dataList = new ArrayList<>();
    public static Random random = new Random();
    public static Faker faker = new Faker();

    private static final String REVIEW_TABLE_NAME = "reviews";
    private static final String REVIEW_COLUMNS = "id,user_id,reservation_id,title,content,rating,created_at,updated_at,@is_deleted,@deleted_at";

    public static void main(String[] args) {

        for(long i = 1 ; i < 300_000 ; i++){

            StringBuffer stringBuffer = new StringBuffer();

            //id
            stringBuffer.append(i);
            stringBuffer.append(", ");

            //user
            stringBuffer.append(random.nextLong(100_000) + 1);
            stringBuffer.append(", ");

            //reservation
            stringBuffer.append(i);
            stringBuffer.append(", ");

            //title
            stringBuffer.append(faker.book().title().replace(",", ""));
            stringBuffer.append(", ");

            //content
            stringBuffer.append(faker.lorem().paragraph().replace(",", ""), 0, 30);
            stringBuffer.append(", ");

            //rating
            stringBuffer.append(faker.number().numberBetween(1, 6));
            stringBuffer.append(", ");

            //createdAt
            stringBuffer.append(LocalDateTime.now());
            stringBuffer.append(", ");

            //modifiedAt
            stringBuffer.append(LocalDateTime.now());
            stringBuffer.append(", ");

            //isDeleted
            stringBuffer.append(0);
            stringBuffer.append(", ");

            //deletedAt
            stringBuffer.append(LocalDateTime.now());
            stringBuffer.append(", ");

            dataList.add(stringBuffer.toString());
        }

        CSVGenerator.generate(REVIEW_TABLE_NAME, REVIEW_COLUMNS, dataList);
        System.out.println("SET is_deleted = CAST(@is_deleted AS UNSIGNED),\n"
                + "deleted_at = NULLIF(@deleted_at, '');");

    }
}
