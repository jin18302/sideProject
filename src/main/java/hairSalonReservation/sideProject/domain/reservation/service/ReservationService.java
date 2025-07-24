package hairSalonReservation.sideProject.domain.reservation.service;

import hairSalonReservation.sideProject.domain.designer.entity.Designer;
import hairSalonReservation.sideProject.domain.designer.repository.DesignerRepository;
import hairSalonReservation.sideProject.domain.reservation.dto.request.CreateReservationRequest;
import hairSalonReservation.sideProject.domain.reservation.dto.request.UpdateReservationStatusRequest;
import hairSalonReservation.sideProject.domain.reservation.dto.response.ReservationResponse;
import hairSalonReservation.sideProject.domain.reservation.entity.Reservation;
import hairSalonReservation.sideProject.domain.reservation.repository.ReservationRepository;
import hairSalonReservation.sideProject.domain.reservation.repository.ReservationRepositoryCustomImpl;
import hairSalonReservation.sideProject.domain.serviceMenu.entity.ServiceMenu;
import hairSalonReservation.sideProject.domain.serviceMenu.repository.ServiceMenuRepository;
import hairSalonReservation.sideProject.domain.user.entity.User;
import hairSalonReservation.sideProject.domain.user.repository.UserRepository;
import hairSalonReservation.sideProject.global.exception.BadRequestException;
import hairSalonReservation.sideProject.global.exception.ErrorCode;
import hairSalonReservation.sideProject.global.exception.ForbiddenException;
import hairSalonReservation.sideProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationRepositoryCustomImpl reservationRepositoryCustom;
    private final DesignerRepository designerRepository;
    private final ServiceMenuRepository serviceMenuRepository;
    private final UserRepository userRepository;


    @Transactional
    public ReservationResponse createReservation(Long userId, Long designerId, CreateReservationRequest request){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        Designer designer = designerRepository.findByIdAndIsDeletedFalse(designerId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.DESIGNER_NOT_FOUND));

        ServiceMenu serviceMenu = serviceMenuRepository.findById(request.serviceMenuId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.SERVICE_MENU_NOT_FOUND));

        if(reservationRepositoryCustom.existByDesignerIdAndSlot(designerId, request.date(), request.time())){
            throw new BadRequestException(ErrorCode.TIME_SLOT_ALREADY_BOOKED);
        }

        Reservation reservation = Reservation.of(serviceMenu, designer, user, request.date(), request.time());

        try{
            reservationRepository.save(reservation);
        }catch (DataIntegrityViolationException ex){
            throw new BadRequestException(ErrorCode.TIME_SLOT_ALREADY_BOOKED);
        }

        return ReservationResponse.from(reservation);
    }

    public List<ReservationResponse> readByDesignerIdAndDate(Long designerId, LocalDate date){

        return reservationRepositoryCustom.findByDesignerIdAndDate(designerId, date);
    }

    public Page<ReservationResponse> readByUserId(Long userId, Pageable pageable){

        return reservationRepository.findByUserId(userId, pageable).map(ReservationResponse::from);
    }

    @Transactional
    public ReservationResponse cancelReservation(Long userId, Long reservationId){

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RESERVATION_NOT_FOUND));

        if(!userId.equals(reservation.getUser().getId())){throw new ForbiddenException(ErrorCode.FORBIDDEN);}
        reservation.cancel();
        return ReservationResponse.from(reservation);
    }

    @Transactional
    public ReservationResponse updateReservationStatus(Long userId, Long reservationId,  UpdateReservationStatusRequest request){

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RESERVATION_NOT_FOUND));

        if(!userId.equals(reservation.getUser().getId())){throw new ForbiddenException(ErrorCode.FORBIDDEN);}
        reservation.updateReservationStatus(request.status());
        return ReservationResponse.from(reservation);
    }
}
