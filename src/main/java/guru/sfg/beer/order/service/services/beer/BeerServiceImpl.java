package guru.sfg.beer.order.service.services.beer;

import guru.sfg.brewery.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@ConfigurationProperties(prefix="sfg.brewery", ignoreUnknownFields = false)
@Component
public class BeerServiceImpl implements BeerService{

    public final static String BEER_PATH_V1 = "/api/v1/beer/";
    public final static String BEER_UPC_PATH_V1 = "/api/v1/beerUPC/";

    private final RestTemplate restTemplate;

    private String beerServiceHost;

    public BeerServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();;
    }

    public void setBeerServiceHost(String beerServiceHost) {
        this.beerServiceHost = beerServiceHost;
    }

    @Override
    public Optional<BeerDto> getBeerById(UUID id) {
        return Optional.ofNullable(restTemplate.getForObject(beerServiceHost+BEER_PATH_V1+id.toString(), BeerDto.class));
    }

    @Override
    public Optional<BeerDto> getBeerByUpc(String upc) {
        return Optional.ofNullable(restTemplate.getForObject(beerServiceHost+BEER_UPC_PATH_V1+upc, BeerDto.class));
    }
}
