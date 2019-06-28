 
 
 
 ### Run Docker 
 docker run -d --name elasticsearch -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" elasticsearch:6.6.2
 
 
 ### Elastic search ID는 String이면 자동 생성
 https://stackoverflow.com/questions/45431574/not-auto-generating-id-for-long-type-but-for-string-type-field-in-spring-data-el
 
 
 ### LocalDateTime type이 존재할 때 에러가 난다면
 CustomObjectMapper Bean으로 등록 필요
  ```xml
 <dependency>
    <groupId>com.fasterxml.jackson.datatype</groupId>
    <artifactId>jackson-datatype-jsr310</artifactId>
    <version>2.6.5</version>
</dependency>
```
```java
public class ElasticSearchConfiguration {

    @Bean
    public ElasticsearchTemplate elasticsearchTemplate(Client client) {
        return new ElasticsearchTemplate(client, new CustomEntityMapper());
    }

    public static class CustomEntityMapper implements EntityMapper {

        private final ObjectMapper objectMapper;

        public CustomEntityMapper() {
            objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.registerModule(new CustomGeoModule());
            //objectMapper.registerModule(new JavaTimeModule()); //LocalDataTime이 배열형태로 저장 됨 [2019, 5, 21 ...] 
            
            //위으 maven dependency를 추가후 아래 구문도 실행
            objectMapper.findAndRegisterModules();
            
        }

        @Override
        public String mapToString(Object object) throws IOException {
            return objectMapper.writeValueAsString(object);
        }

        @Override
        public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
            return objectMapper.readValue(source, clazz);
        }
    }
}
```