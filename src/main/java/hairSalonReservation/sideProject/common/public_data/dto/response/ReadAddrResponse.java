package hairSalonReservation.sideProject.common.public_data.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ReadAddrResponse(String cd, @JsonProperty("addr_name")String address, @JsonProperty("full_addr")String fullAddress) {

}
