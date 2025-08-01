package hairSalonReservation.sideProject.common.dummy;

import java.util.*;

public class TagMapperDummy {

    private static final String TAG_TABLE_NAME = "shop_tag_mappers";
    private static final String TAG_COLUMNS = "id,shop_id,shop_tag_id";
    private static final int TOTAL_SHOPS = 5_000_000;
    private static final int TOTAL_TAGS = 25;

    public static void main(String[] args) {

        List<String> tagDataList = new ArrayList<>();
        Random random = new Random();
        int idCounter = 1;

        for(int i = 1 ; i < 5_000_000 ; i++){
                long shopId = random.nextLong(TOTAL_SHOPS) + 1;
                long tagId = random.nextLong(TOTAL_TAGS) + 1;

                StringBuilder builder = new StringBuilder();

                builder.append(idCounter++).append(','); // id
                builder.append(shopId).append(',');       // shop_id
                builder.append(tagId);

                tagDataList.add(builder.toString());
        }
        CSVGenerator.generate(TAG_TABLE_NAME, TAG_COLUMNS, tagDataList);
    }
}
