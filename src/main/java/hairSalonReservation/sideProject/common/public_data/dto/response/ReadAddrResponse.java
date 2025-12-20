package hairSalonReservation.sideProject.common.public_data.dto.response;

import com.fasterxml.jackson.annotation.JsonAlias;

public record ReadAddrResponse(String cd, @JsonAlias("addr_name")String address, @JsonAlias("full_addr")String fullAddress) {

    public static ReadAddrResponse from(String cd, String address, String fullAddress){
        return new ReadAddrResponse(cd, address, fullAddress);
    }

}
