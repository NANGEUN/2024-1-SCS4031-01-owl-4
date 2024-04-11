package org.dgu.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dgu.backend.domain.Market;
import org.dgu.backend.dto.response.MarketResponse;
import org.dgu.backend.repository.MarketRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class MarketServiceImpl implements MarketService {

    private final RestTemplate restTemplate;
    private final MarketRepository marketRepository;

    // 모든 가상화폐 데이터를 가져와 저장하는 메서드
    @Override
    public void getAllMarkets() {
        String url = "https://api.upbit.com/v1/market/all?isDetails=false";

        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", MediaType.APPLICATION_JSON_VALUE);

        ResponseEntity<MarketResponse[]> responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                MarketResponse[].class
        );

        MarketResponse[] responseBody = responseEntity.getBody();
        if (responseBody != null) {
            for (MarketResponse marketResponse : responseBody) {
                Market market = Market.toEntity(marketResponse);
                marketRepository.save(market);
            }
        } else {
            log.error("Failed to receive market info");
        }
    }
}