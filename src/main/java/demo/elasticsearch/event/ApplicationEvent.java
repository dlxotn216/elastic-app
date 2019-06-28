package demo.elasticsearch.event;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

/**
 * Created by taesu at : 2019-05-21
 *
 * ApplicationEvent
 *
 * @author taesu
 * @version 1.0
 * @since 1.0
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Document(indexName = "appdemo", type = "applicationevent")
public class ApplicationEvent {

    @Id
    private String id;
    private String applicationName;
    private String hostIp;
    private LocalDateTime eventAt;
}
