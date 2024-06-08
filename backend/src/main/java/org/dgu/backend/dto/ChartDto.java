package org.dgu.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.dgu.backend.domain.CandleInfo;
import org.dgu.backend.util.BigDecimalSerializer;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ChartDto {
    @Builder
    @Getter
    @AllArgsConstructor
    @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class OHLCVResponse {
        private String date;
        @JsonSerialize(using = BigDecimalSerializer.class)
        private BigDecimal openingPrice;
        @JsonSerialize(using = BigDecimalSerializer.class)
        private BigDecimal highPrice;
        @JsonSerialize(using = BigDecimalSerializer.class)
        private BigDecimal lowPrice;
        @JsonSerialize(using = BigDecimalSerializer.class)
        private BigDecimal closePrice;
        @JsonSerialize(using = BigDecimalSerializer.class)
        private BigDecimal volume;

        public static ChartDto.OHLCVResponse of(CandleInfo candleInfo) {
            return OHLCVResponse.builder()
                    .date(String.valueOf(candleInfo.getDateTime()))
                    .openingPrice(BigDecimal.valueOf(candleInfo.getOpeningPrice()))
                    .lowPrice(BigDecimal.valueOf(candleInfo.getLowPrice()))
                    .highPrice(BigDecimal.valueOf(candleInfo.getHighPrice()))
                    .closePrice(BigDecimal.valueOf(candleInfo.getTradePrice()))
                    .volume(BigDecimal.valueOf(candleInfo.getAccTradeVolume()).setScale(3, RoundingMode.HALF_UP))
                    .build();
        }
    }
}