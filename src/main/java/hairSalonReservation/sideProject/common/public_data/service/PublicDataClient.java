package hairSalonReservation.sideProject.common.public_data.service;

import hairSalonReservation.sideProject.common.public_data.dto.response.ReadAddrResponse;
import hairSalonReservation.sideProject.common.public_data.dto.response.ReadAddrApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublicDataClient {

    @Value("${address.api.token}")
    private String token;

    private final WebClient apiClient;


    public List<ReadAddrResponse> sendRequest(String code){


        //TODO: 왜인지 모르겠는데 속도가 오래걸림 평균 2~3초 정도 최적화 필요할 듯 이라고 생각

        return apiClient.get().uri(uriBuilder
                -> uriBuilder.queryParam("accessToken", token)
                .queryParamIfPresent("cd", Optional.ofNullable(code)).build())//null이라면
                .retrieve()
                .onStatus(
                        status -> status.is4xxClientError() || status.is5xxServerError(),
                        res -> res.bodyToMono(String.class).flatMap(errorBody -> {
                            log.info("지역 데이터를 읽어오는 중 문제 발생 : {}", errorBody);
                            return Mono.error(new RuntimeException("지역 데이터 로드 실패: " + errorBody));
                        })
                ).bodyToMono(ReadAddrApiResponse.class)
                .block()
                .result();
    }
}
