package hairSalonReservation.sideProject.domain.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class AddressSearchService {

    @Value("${address.api.url}")
    private String uri;

    @Value("${address.api.token}")
    private String token;

    private final WebClient webClient;


    public void sendRequest(){

        //api에 uri와 token, cd를 담아 요청을 보낸다
        //응답을 가지고와서 객체타입의 배열로 변환한다.
        //map으로 주소이름과 cd 필드만 뽑아내서 객체로 만들어 반환한다.
    }


}
