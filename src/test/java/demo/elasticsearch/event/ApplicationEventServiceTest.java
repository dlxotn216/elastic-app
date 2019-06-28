package demo.elasticsearch.event;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Created by Lee Tae Su on 2019-05-21.
 */
@Slf4j @RunWith(SpringRunner.class) @SpringBootTest
public class ApplicationEventServiceTest {

    @Autowired
    private ApplicationEventService applicationEventService;

    @Test
    public void 저장_테스트() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        ApplicationEvent saved = this.applicationEventService
                .save(new ApplicationEvent(null, "CTMS", "192.168.11.56", now));
        ApplicationEvent applicationEvent = this.applicationEventService.find(saved.getId());

        assertThat(applicationEvent.getId()).isNotNull();
        assertThat(applicationEvent.getApplicationName()).isEqualTo("CTMS");
        assertThat(applicationEvent.getHostIp()).isEqualTo("192.168.11.56");
        assertThat(applicationEvent.getEventAt()).isEqualTo(now);
        log.info("check {}", applicationEvent.toString());
    }
}