package hairSalonReservation.sideProject.common.dto;

public record PresignedUrlResponse(String url) {
    public static PresignedUrlResponse of(String url){
        return new PresignedUrlResponse(url);
    }
}
