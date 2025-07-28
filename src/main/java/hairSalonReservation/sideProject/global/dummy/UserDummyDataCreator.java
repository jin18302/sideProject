package hairSalonReservation.sideProject.global.dummy;

import hairSalonReservation.sideProject.common.util.PasswordEncoder;
import hairSalonReservation.sideProject.domain.user.entity.Gender;
import hairSalonReservation.sideProject.domain.user.entity.UserRole;
import net.datafaker.Faker;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDummyDataCreator {

    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMNS = "id,name,email,password,gender,user_role,created_at,updated_at,@is_deleted,@is_deleted_at";


    public static void main(String[] args) {
        Faker faker = new Faker();
        PasswordEncoder passwordEncoder = new PasswordEncoder();

        List<String> userDataList = new ArrayList<>();
        Set<String> emails = new HashSet<>();

        for (int i = 1; i <= 100000 ; i++) {

            StringBuilder builder = new StringBuilder();
            String email ;

            do {
                email = faker.internet().emailAddress();
            } while (!emails.add(email));

            //id
            builder.append(i);
            builder.append(',');

            //name
            builder.append(faker.name().fullName());
            builder.append(',');

            //email
            builder.append(email);
            builder.append(',');

            //password
            builder.append(passwordEncoder.encode(faker.internet().password()+"!"));
            builder.append(',');

            //gender
            builder.append( i<=10000 ? Gender.MALE : Gender.FEMALE);
            builder.append(',');

            //userRole
            builder.append( i<=1000 ? UserRole.ADMIN.toString() : UserRole.CUSTOMER.toString());
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

            userDataList.add(builder.toString());
        }

        CSVGenerator.generate(USER_TABLE_NAME,USER_COLUMNS,userDataList);
        System.out.println("SET is_deleted = CAST(@is_deleted AS UNSIGNED),\n"
                           + "is_deleted_at = NULLIF(@is_deleted_at, '');");
    }

}
