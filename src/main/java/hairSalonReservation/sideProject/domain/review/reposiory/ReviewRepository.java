package hairSalonReservation.sideProject.domain.review.reposiory;

import hairSalonReservation.sideProject.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
