package hairSalonReservation.sideProject.dummy;

import java.util.ArrayList;
import java.util.List;

public class TagDummy {

    private static final String TAG_TABLE_NAME = "shop_tags";
    private static final String TAG_COLUMNS = "id,name,@is_deleted";

    static List<String> shopTags = List.of(
            "애견동반 가능",
            "여성전문",
            "남성전문",
            "주차 가능",
            "심야영업",
            "예약 필수",
            "1인샵",
            "단골 많은",
            "SNS 인기",
            "친절한 서비스",
            "커트 전문",
            "염색 전문",
            "펌 전문",
            "헤어 클리닉",
            "두피 관리",
            "조용한 분위기",
            "트렌디한 스타일",
            "아이동반 가능",
            "웨딩/혼주 전문",
            "메이크업 가능",
            "뷰티토탈샵",
            "학생 할인",
            "대중교통 편리",
            "영어 가능",
            "맞춤 스타일링"
    );


    public static void main(String[] args) {
        List<String> tagDataList = new ArrayList<>();

        for (int i = 0; i < shopTags.size(); i++) {
            StringBuilder builder = new StringBuilder();

            // id
            builder.append(i + 1); // 1부터 시작
            builder.append(", ");

            // name
            builder.append(shopTags.get(i));
            builder.append(", ");

            // isDeleted
            builder.append(0);

            tagDataList.add(builder.toString());
        }

        CSVGenerator.generate(TAG_TABLE_NAME, TAG_COLUMNS, tagDataList);
        System.out.println("""
                SET is_deleted = CAST(@is_deleted AS UNSIGNED),
                    deleted_at = NULLIF(@deleted_at, '');
                """);
    }
}
