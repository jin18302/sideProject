package hairSalonReservation.sideProject.domain.reservation.service;


import com.fasterxml.jackson.core.type.TypeReference;
import hairSalonReservation.sideProject.common.exception.ErrorCode;
import hairSalonReservation.sideProject.common.exception.ForbiddenException;
import hairSalonReservation.sideProject.common.exception.NotFoundException;
import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.designer.repository.DesignerRepository;
import hairSalonReservation.sideProject.domain.reservation.dto.request.CreateScheduleBlockRequest;
import hairSalonReservation.sideProject.domain.reservation.dto.response.ScheduleBlockResponse;
import hairSalonReservation.sideProject.domain.reservation.entity.ScheduleBlock;
import hairSalonReservation.sideProject.domain.reservation.repository.ScheduleBlockRepositoryCustomImpl;
import hairSalonReservation.sideProject.domain.reservation.repository.ScheduleBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ScheduleBlockService {

    private final ScheduleBlockRepository scheduleBlockRepository;
    private final ScheduleBlockRepositoryCustomImpl blockRepositoryCustom;
    private final DesignerRepository designerRepository;

    @Transactional
    public ScheduleBlockResponse createBlock(Long ownerId, Long designerId, CreateScheduleBlockRequest request) {

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        Long targetId = designer.getShop().getUser().getId();
        if (!ownerId.equals(targetId)) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN);
        }

        Optional<ScheduleBlock> targetBlock = blockRepositoryCustom.findByDesignerIdAndDate(designerId, request.date());

        if (targetBlock.isEmpty()) {

            ScheduleBlock block = ScheduleBlock.of(designer, request.date(), JsonHelper.toJson(request.time()));
            scheduleBlockRepository.save(block);
            return ScheduleBlockResponse.from(block);

        } else {
            targetBlock.get().updateTimeSlot(JsonHelper.toJson(request.time()));
            return ScheduleBlockResponse.from(targetBlock.get());

        }
    }

    public ScheduleBlockResponse readByDesignerId(Long designerId, LocalDate date) {

        ScheduleBlock block = blockRepositoryCustom.findByDesignerIdAndDate(designerId, date)
                .orElseGet(null);//TODO

        return ScheduleBlockResponse.from(block);
    }

    @Transactional
    public void deleteBlock(Long ownerId, Long designerId, LocalDate date, LocalTime time) {

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        Long targetId = designer.getShop().getUser().getId();
        if (!ownerId.equals(targetId)) {
            throw new ForbiddenException(ErrorCode.FORBIDDEN);
        }

        ScheduleBlock block = blockRepositoryCustom.findByDesignerIdAndDate(designerId, date)
                .orElseThrow(() -> new NotFoundException(ErrorCode.BLOCK_NOT_FOUND));

        List<LocalTime> timeList = JsonHelper.fromJsonToList(block.getTimeList(), new TypeReference<List<LocalTime>>() {
        });
        if (timeList.contains(time)) {
            timeList.remove(time);
        } else {
            throw new NotFoundException(ErrorCode.BLOCK_NOT_FOUND);
        }
    }
}
