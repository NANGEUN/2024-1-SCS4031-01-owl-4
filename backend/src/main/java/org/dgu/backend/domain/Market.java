package org.dgu.backend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.dgu.backend.dto.UpbitDto;

import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@Table(name = "markets")
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "markets_id")
    private Long id;

    @Column(name = "markets_name")
    private String name;

    @Column(name = "korean_name")
    private String koreanName;

    @Column(name = "english_name")
    private String englishName;

    @OneToMany(mappedBy = "market", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandleInfo> candleInfos;

    public static Market toEntity(UpbitDto.MarketResponse response) {
        return Market.builder()
                .name(response.getName())
                .koreanName(response.getKoreanName())
                .englishName(response.getEnglishName())
                .build();
    }
}
