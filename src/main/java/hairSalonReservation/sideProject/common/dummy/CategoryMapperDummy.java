package hairSalonReservation.sideProject.common.dummy;


import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CategoryMapperDummy {

    private static final String MENU_TABLE_NAME = "service_category_mappers";
    private static final String MENU_COLUMNS = "id,service_menu_category_id,designer_id";

    private static Faker faker = new Faker();
    private static Random random = new Random();
    private static List<String> serviceMenuList = new ArrayList<>();

    public static void main(String[] args) {

        int c = 1;

        for (int i = 1; i <= 1_000_000; i++) {
            for (int j = 1; j <= 4; j++) {
                StringBuffer stringBuffer = new StringBuffer();

                //id
                stringBuffer.append(c++).append(", ");

                //ServiceMenuCategory
                stringBuffer.append(j).append(", ");

                //Designer
                stringBuffer.append(i);

                serviceMenuList.add(stringBuffer.toString());
            }
        }

        CSVGenerator.generate(MENU_TABLE_NAME, MENU_COLUMNS, serviceMenuList);
        System.out.println("SET is_deleted = CAST(@is_deleted AS UNSIGNED),\n"
                           + "deleted_at = NULLIF(@deleted_at, '');");
    }
}
