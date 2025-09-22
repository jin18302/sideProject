package hairSalonReservation.sideProject.domain.designer.dto.response;

import com.fasterxml.jackson.core.type.TypeReference;
import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.designer.entity.Designer;

import java.util.List;

public record DesignerDetailResponse(
        Long id,
        Long shopId,
        String name,
        String profileImage,
        String introduction,
        List<String> imageUrlList,
        List<String> snsUrlList

) {

    public static DesignerDetailResponse from(Designer designer){
        return new DesignerDetailResponse(
                designer.getId(),
                designer.getShop().getId(),
                designer.getName(),
                designer.getProfileImage(),
                designer.getIntroduction(),
                JsonHelper.fromJsonToList(designer.getImageUrlList(), new TypeReference<>(){}),
                JsonHelper.fromJsonToList(designer.getSnsUrlList(), new TypeReference<>(){})
        );
    }
}
