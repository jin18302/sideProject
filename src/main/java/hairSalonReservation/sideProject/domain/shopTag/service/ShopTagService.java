package hairSalonReservation.sideProject.domain.shopTag.service;

import hairSalonReservation.sideProject.domain.shopTag.repository.ShopTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ShopTagService {

    private final ShopTagRepository shopTagRepository;

}
