package hairSalonReservation.sideProject.domain.serviceMenu.service;

import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ServiceMenuService {

    private final ServiceMenuRepository serviceMenuRepository;
}
