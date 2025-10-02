package hairSalonReservation.sideProject.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@Setter @Getter
@ConfigurationProperties(prefix = "query")
public class QueryProperties {
    private int limit;
}
