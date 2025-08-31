package com.javarush.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.type.NumericBooleanConverter;

import java.math.BigDecimal;

@Entity
@Table(schema = "world", name = "country_language")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryLanguage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    private String language;

    @Column(name = "is_official", columnDefinition = "BIT")
    @Convert(converter = NumericBooleanConverter.class)
    private Boolean isOfficial;

    private BigDecimal percentage;

}