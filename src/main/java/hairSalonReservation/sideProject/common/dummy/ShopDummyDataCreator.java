package hairSalonReservation.sideProject.common.dummy;

import hairSalonReservation.sideProject.domain.shop.entity.ShopStatus;
import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopDummyDataCreator {

    private static final String SHOP_TABLE_NAME = "shops";
    private static final String SHOP_COLUMNS = "id,user_id,name,business_id,address,phone_number,open_time,end_time,introduction," +
                                               "image_url_list,sns_uri_list,shop_status,open_date,created_at,updated_at,@is_deleted,@deleted_at";

    static List<String> provinces = List.of(
            "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "세종특별자치시",
            "경기도", "강원특별자치도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도"
    );

    static List<String> districts = List.of(
            // 서울특별시
            "종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구",
            "서대문구", "마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강동구",
            "중구", "서구", "동구", "영도구", "부산진구", "동래구", "남구", "북구", "해운대구", "사하구", "금정구", "강서구",
            "중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군",
            "중구", "동구", "미추홀구", "연수구", "남동구", "부평구", "계양구", "서구", "강화군", "옹진군", "세종시",
            "수원시", "성남시", "안양시", "부천시", "광명시", "평택시", "의정부시", "시흥시", "파주시", "김포시", "하남시", "용인시", "고양시", "남양주시",
            "춘천시", "원주시", "강릉시", "속초시", "동해시", "삼척시", "태백시", "홍천군", "횡성군",
            "청주시", "충주시", "제천시", "음성군", "단양군", "보은군",
            "천안시", "공주시", "보령시", "서산시", "아산시", "논산시", "계룡시", "당진시",
            "전주시", "군산시", "익산시", "정읍시", "남원시", "김제시",
            "목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군",
            "포항시", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시",
            "창원시", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시",
            "제주시", "서귀포시"
    );

    static List<String> neighborhoods = List.of(
            "청운효자동", "사직동", "삼청동", "부암동", "평창동", "무악동", "교남동", "가회동", "종로1.2.3.4가동", "종로5.6가동",
            "이화동", "혜화동", "창신1동", "창신2동", "창신3동", "숭인1동", "숭인2동", "소공동", "회현동", "명동",
            "필동", "장충동", "광희동", "을지로동", "신당동", "다산동", "약수동", "청구동", "신당5동", "동화동",
            "황학동", "중림동", "광희동1가", "남산동1가", "남산동2가", "예장동", "후암동", "용산2가동", "남영동", "청파동",
            "원효로1동", "원효로2동", "효창동", "도원동", "이촌1동", "이촌2동", "이태원1동", "이태원2동", "한남동", "서빙고동",
            "보광동", "금호1가동", "금호2.3가동", "금호4가동", "옥수동", "성수1가1동", "성수1가2동", "성수2가1동", "성수2가3동", "송정동",
            "용답동", "자양1동", "자양2동", "자양3동", "자양4동", "구의1동", "구의2동", "구의3동", "중곡1동", "중곡2동",
            "중곡3동", "중곡4동", "능동", "화양동", "군자동", "회기동", "휘경1동", "휘경2동", "이문1동", "이문2동",
            "제기동", "전농1동", "전농2동", "답십리1동", "답십리2동", "장안1동", "장안2동", "청량리동", "용신동", "신설동",
            "면목2동", "면목4동", "면목5동", "면목본동", "면목7동", "상봉1동", "상봉2동", "중화1동", "중화2동", "묵1동",
            "묵2동", "망우본동", "망우3동", "신내1동", "신내2동", "성북동", "삼선동", "동선동", "돈암1동", "돈암2동",
            "안암동", "보문동", "정릉1동", "정릉2동", "정릉3동", "정릉4동", "길음1동", "길음2동", "종암동", "월곡1동",
            "월곡2동", "장위1동", "장위2동", "장위3동", "석관동", "삼양동", "미아동", "송중동", "송천동", "삼각산동",
            "번1동", "번2동", "번3동", "수유1동", "수유2동", "수유3동", "우이동", "인수동", "쌍문1동", "쌍문2동",
            "쌍문3동", "쌍문4동", "창1동", "창2동", "창3동", "창4동", "창5동", "월계1동", "월계2동", "월계3동",
            "공릉1동", "공릉2동", "하계1동", "하계2동", "중계본동", "중계1동", "중계4동", "중계2.3동", "상계1동", "상계2동",
            "상계3.4동", "상계5동", "상계6.7동", "상계8동", "상계9동", "상계10동", "상계11동", "녹번동", "불광1동", "불광2동",
            "갈현1동", "갈현2동", "구산동", "대조동", "응암1동", "응암2동", "응암3동", "역촌동", "신사1동", "신사2동",
            "증산동", "수색동", "홍제1동", "홍제2동", "홍제3동", "홍은1동", "홍은2동", "남가좌1동", "남가좌2동", "북가좌1동",
            "북가좌2동", "아현동", "공덕동", "도화동", "용강동", "대흥동", "염리동", "신수동", "서강동", "서교동",
            "합정동", "망원1동", "망원2동", "연남동", "성산1동", "성산2동", "중동", "신월1동", "신월2동", "신월3동"
    );


    public static void main(String[] args) {
        Faker faker = new Faker();
        Random random = new Random();

        List<String> shopDataList = new ArrayList<>();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (int i = 1; i <= 500_000; i++) {
            StringBuilder builder = new StringBuilder();

            // id
            builder.append(i).append(", ");

            // user_id
            builder.append(faker.number().numberBetween(1, 100001)).append(", ");

            // name
            builder.append(faker.name().fullName().replace(",", "")).append(", ");

            // business_id
            String businessId = String.format("%03d-%02d-%05d",
                    faker.number().numberBetween(100, 999),
                    faker.number().numberBetween(10, 99),
                    faker.number().numberBetween(10000, 99999));
            builder.append(businessId).append(", ");


            // address
            builder.append(generateRandomAddress());
            builder.append(", ");

            // phone_number
            builder.append(faker.phoneNumber().phoneNumber().replace(",", "")).append(", ");

            // open_time & end_time
            builder.append(LocalTime.of(random.nextInt(24), 0).format(timeFormatter)).append(", ");
            builder.append(LocalTime.of(random.nextInt(24), 0).format(timeFormatter)).append(", ");

            // introduction
            builder.append(faker.lorem().paragraph().replace(",", "")).append(", ");;

            // image_url_list
            builder.append(faker.internet().image()).append(", ");;

            // sns_uri_list
            builder.append(faker.internet().url()).append(", ");;

            // shop_status
            builder.append(i >= 250_000 ? ShopStatus.BEFORE_OPEN : ShopStatus.OPEN).append(", ");;

            // open_date, created_at, updated_at, is_deleted, is_deleted_at
            String now = LocalDateTime.now().format(dateTimeFormatter);
            builder.append(now).append(", "); // open_date
            builder.append(now).append(", "); // created_at
            builder.append(now).append(", "); // updated_at
            builder.append(0).append(", ");   // @is_deleted

            shopDataList.add(builder.toString());
        }

        CSVGenerator.generate(SHOP_TABLE_NAME, SHOP_COLUMNS, shopDataList);
        System.out.println("SET is_deleted = CAST(@is_deleted AS UNSIGNED),\n"
                           + "deleted_at = NULLIF(@deleted_at, '');");
    }

    public static String generateRandomAddress() {
        Random random = new Random();

        String province = provinces.get(random.nextInt(provinces.size()));
        String district = districts.get(random.nextInt(districts.size()));
        String neighborhood = neighborhoods.get(random.nextInt(neighborhoods.size()));

        return province + " " + district + " " + neighborhood;
    }
}


