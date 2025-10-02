package hairSalonReservation.sideProject.common.kakao;

import hairSalonReservation.sideProject.common.util.JsonHelper;
import hairSalonReservation.sideProject.domain.reservation.dto.request.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoClient {

    @Value("${kakao.access.token}")
    private String token;

    @Value(("${kakao.uri}"))
    private String sendUri;

    private final WebClient webClient;
    private final String Authorization = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    private final String TEMPLATE_ID = "template_id";
    private final String TEMPLATE_ARGS = "template_args";

    public void sendMessage(MessageRequest request){


        webClient.post()
                .uri(sendUri)
                .header(Authorization, TOKEN_PREFIX  + token)
                .body(BodyInserters.fromFormData(buildMessageBody(request)))
                .retrieve()
                .bodyToMono(String.class)
                .doOnNext(response -> System.out.println("카카오 응답: " + response))
                .subscribe(
                        response -> log.info("✅ 카카오 응답 성공: {}", response),
                        error -> {
                            if (error instanceof WebClientResponseException e) {
                                log.warn("❌ 카카오 응답 실패: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
                            } else {
                                log.error("❌ 카카오 메시지 전송 중 알 수 없는 오류 발생", error);
                            }
                        }
                );
    }

    private MultiValueMap<String, String> buildMessageBody(MessageRequest request){

        MultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.add(TEMPLATE_ID, request.template_id());
        valueMap.add(TEMPLATE_ARGS, JsonHelper.toJson(request.template_args()));

        return valueMap;
    }
}
