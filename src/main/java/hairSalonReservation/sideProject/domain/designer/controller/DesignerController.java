package hairSalonReservation.sideProject.domain.designer.controller;

import hairSalonReservation.sideProject.domain.designer.service.DesignerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DesignerController {

    private final DesignerService designerService;
}
