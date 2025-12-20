package hairSalonReservation.sideProject.common.public_data.dto.response;

import java.util.ArrayList;
import java.util.List;

public record ReadAddrApiResponse(List<ReadAddrResponse> result) {

    public ReadAddrApiResponse(List<ReadAddrResponse> result){

        List<ReadAddrResponse> resultList = new ArrayList<>();
        resultList.add(0, ReadAddrResponse.from("0", "전체", "전체"));
        resultList.addAll(result);
       this.result = resultList;
    }
}
