package hairSalonReservation.sideProject.domain.shop.service;

import hairSalonReservation.sideProject.domain.shop.repository.ShopTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopTagService {

    private final ShopTagRepository shopTagRepository;

}
