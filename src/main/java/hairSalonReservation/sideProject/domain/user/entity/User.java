package hairSalonReservation.sideProject.domain.user.entity;

import hairSalonReservation.sideProject.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private User(String name, String email, String password, Gender gender, UserRole userRole){
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.userRole = userRole;
    }

    public static User of(String name, String email, String password, String gender, String userRole){
        return new User(name, email, password, Gender.of(gender), UserRole.of(userRole));
    }
}
