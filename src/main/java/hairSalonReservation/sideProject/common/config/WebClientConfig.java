package hairSalonReservation.sideProject.common.config;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {

    @Value("${webclient.connect.timeout}")
    private int timeOutMillis;

    private final String API_CONNECTION_POOL = "api-pool";

    @Bean
    public HttpClient httpClient(ConnectionProvider connectionProvider){

        return HttpClient.create(connectionProvider)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, timeOutMillis);
    }

    @Bean
    public WebClient webClient(HttpClient httpClient){
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    public ConnectionProvider connectionProvider(){

        return ConnectionProvider
                .builder(API_CONNECTION_POOL)
                .maxConnections(50)
                .build();
    }
}
