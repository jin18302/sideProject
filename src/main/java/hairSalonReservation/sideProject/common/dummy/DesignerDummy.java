package hairSalonReservation.sideProject.common.dummy;

import net.datafaker.Faker;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DesignerDummy {

    private static final String DESIGNER_TABLE_NAME = "designers";
    private static final String DESIGNER_COLUMNS = "id,shop_id,name,introduction, profile_image, day_off_weekday_list" +
                                                    "image_url_list, sns_url_list,created_at,updated_at,@is_deleted,@deleted_at  ";

    private static Faker faker = new Faker();
    private static Random random = new Random();

    public static void main(String[] args) {

            List<String> designerDataList = new ArrayList<>();

            for (int i = 1; i <= 100000000 ; i++) {

                StringBuilder builder = new StringBuilder();

                //id
                builder.append(i);
                builder.append(',');

                //shop
                builder.append(random.nextLong(5000000) + 1 );
                builder.append(',');

                //name
                builder.append( faker.company().name() );
                builder.append(',');

                //introduction
                builder.append(faker.lorem().sentence() );
                builder.append(',');

                //profile
                builder.append(faker.internet().image());
                builder.append(',');

                //image
                builder.append(faker.internet().image());
                builder.append(',');

                //sns
                builder.append(faker.internet().url());
                builder.append(',');

                //createdAt
                builder.append(LocalDateTime.now());
                builder.append(',');

                //modifiedAt
                builder.append(LocalDateTime.now());
                builder.append(',');

                //deletedAt
                builder.append(LocalDateTime.now());
                builder.append(',');

                //isDeleted
                builder.append(0);
                builder.append(',');

                //deletedAt

                designerDataList.add(builder.toString());
            }

            CSVGenerator.generate(DESIGNER_TABLE_NAME,DESIGNER_COLUMNS,designerDataList);
            System.out.println("SET is_deleted = CAST(@is_deleted AS UNSIGNED),\n"
                               + "is_deleted_at = NULLIF(@is_deleted_at, '');");
        }
}
