package demo.elasticsearch.event;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;

/**
 * Created by taesu at : 2019-05-21
 *
 * ApplicationEventRepository 
 *
 * @author taesu
 * @version 1.0
 * @since 1.0
 */
public interface ApplicationEventRepository extends ElasticsearchCrudRepository<ApplicationEvent, String> {
}
