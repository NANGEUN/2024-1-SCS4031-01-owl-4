package org.dgu.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.dgu.backend.dto.UpbitDto;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "candle_infos")
public class CandleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candle_infos_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "markets_id", foreignKey = @ForeignKey(name = "candle_infos_fk_markets_id"))
    private Market market;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candles_id", foreignKey = @ForeignKey(name = "candle_infos_fk_candles_id"))
    private Candle candle;

    @Column(name = "markets_korean_name")
    private String marketKoreanName;

    @Column(name = "candles_korean_name")
    private String candleKoreanName;

    @Column(name = "date_time_kst")
    private String dateTime;

    @Column(name = "opening_price")
    private Double openingPrice;

    @Column(name = "high_price")
    private Double highPrice;

    @Column(name = "low_price")
    private Double lowPrice;

    @Column(name = "trade_price")
    private Double tradePrice;

    @Column(name = "timestamp")
    private Long timestamp;

    @Column(name = "acc_trade_price")
    private Double accTradePrice;

    @Column(name = "acc_trade_volume")
    private Double accTradeVolume;

    @Column(name = "prev_closing_price")
    private Double prevClosingPrice;

    @Column(name = "change_price")
    private Double changePrice;

    @Column(name = "change_rate")
    private Double changeRate;

    public static CandleInfo toEntity(UpbitDto.CandleInfoResponse response, Market market, Candle candle) {
        return CandleInfo.builder()
                .market(market)
                .candle(candle)
                .marketKoreanName(market.getKoreanName())
                .candleKoreanName(candle.getKoreanName())
                .dateTime(response.getDateTime())
                .openingPrice(response.getOpeningPrice())
                .highPrice(response.getHighPrice())
                .lowPrice(response.getLowPrice())
                .tradePrice(response.getTradePrice())
                .timestamp(response.getTimestamp())
                .accTradePrice(response.getAccTradePrice())
                .accTradeVolume(response.getAccTradeVolume())
                .prevClosingPrice(response.getPrevClosingPrice())
                .changePrice(response.getChangePrice())
                .changeRate(response.getChangeRate())
                .build();
    }
}