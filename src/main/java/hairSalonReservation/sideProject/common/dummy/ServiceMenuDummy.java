package hairSalonReservation.sideProject.common.dummy;

import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServiceMenuDummy {

    private static final String MENU_TABLE_NAME = "service_menus";
    private static final String MENU_COLUMNS = "id,service_menu_category_mapper_id,name,price,introduction,created_at,updated_at,@is_deleted,@deleted_at";

    private static Faker faker = new Faker();
    private static Random random = new Random();
    private static List<String> serviceMenuList = new ArrayList<>();

    public static void main(String[] args) {

        for(int i = 1 ; i <= 5_000_000 ; i++){

            StringBuffer buffer = new StringBuffer();

            //id
            buffer.append(i).append(",,");

            //mapperId
            buffer.append(random.nextLong(3_000_000)).append(",,");

            //name
            String name = faker.name().firstName();
            buffer.append(name.length() > 20 ? name.substring(0, 20) : name).append(",,");

            //price
            buffer.append(faker.number().randomDigit()).append(",,");

            //description
            buffer.append(faker.name().fullName()).append(",,");

            //createdAt
            buffer.append(LocalDateTime.now());
            buffer.append(",,");

            //modifiedAt
            buffer.append(LocalDateTime.now());
            buffer.append(",,");

            //isDeleted
            buffer.append(0);
            buffer.append(",,");

            //deletedAt
            buffer.append(LocalDateTime.now());
            buffer.append(",,");

            serviceMenuList.add(buffer.toString());

        }

        CSVGenerator.generate(MENU_TABLE_NAME,MENU_COLUMNS,serviceMenuList);
        System.out.println("SET is_deleted = CAST(@is_deleted AS UNSIGNED),\n"
                           + "deleted_at = NULLIF(@deleted_at, '');");
    }

}
