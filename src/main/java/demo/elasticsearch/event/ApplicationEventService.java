package demo.elasticsearch.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by taesu at : 2019-05-21
 *
 * ApplicationEventService 
 *
 * @author taesu
 * @version 1.0
 * @since 1.0
 */
@Service @RequiredArgsConstructor
public class ApplicationEventService {
    private final ApplicationEventRepository applicationEventRepository;
    
    public ApplicationEvent save(ApplicationEvent applicationEvent){
        return this.applicationEventRepository.save(applicationEvent);
    }
    
    public ApplicationEvent find(String id){
        return this.applicationEventRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
