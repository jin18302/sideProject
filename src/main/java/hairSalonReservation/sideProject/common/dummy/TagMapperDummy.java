package hairSalonReservation.sideProject.common.dummy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TagMapperDummy {

    private static final String TAG_TABLE_NAME = "shop_tag_mappers";
    private static final String TAG_COLUMNS = "id,shop_id,shop_tag_id";
    private static final int TOTAL_ROWS = 50_000_000;
    private static final int TOTAL_SHOPS = 5_000_000;
    private static final int TOTAL_TAGS = 25;

    public static void main(String[] args) {
        List<String> tagDataList = new ArrayList<>();
        Set<String> uniqueCombinations = new HashSet<>();
        Random random = new Random();
        int idCounter = 1;

        while (tagDataList.size() < TOTAL_ROWS) {
            int shopId = random.nextInt(TOTAL_SHOPS) + 1;
            int tagId = random.nextInt(TOTAL_TAGS) + 1;
            String key = shopId + "-" + tagId;

            if (uniqueCombinations.contains(key)) {
                continue; // 중복 조합이면 건너뜀
            }

            uniqueCombinations.add(key);

            StringBuilder builder = new StringBuilder();
            builder.append(idCounter++).append(','); // id
            builder.append(shopId).append(',');       // shop_id
            builder.append(tagId);                    // shop_tag_id

            tagDataList.add(builder.toString());
        }

        CSVGenerator.generate(TAG_TABLE_NAME, TAG_COLUMNS, tagDataList);
        System.out.println("중복 없이 생성된 태그 매핑 수: " + tagDataList.size());
    }
}
