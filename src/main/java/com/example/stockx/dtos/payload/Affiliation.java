package com.example.stockx.dtos.payload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Affiliation {
    private String director_of;
    private String broker;
}
