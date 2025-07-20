package hairSalonReservation.sideProject.domain.shopTag.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity @Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ShopTagMapper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
