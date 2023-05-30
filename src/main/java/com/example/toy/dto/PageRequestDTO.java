package com.example.toy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;

    // db의 limit 속성을 활용하기 위해
    // 1page에 있으면 처음부터 10개
    // 2page에 있으면 10개 가져오고, 10개
    // 3page 에 있으면 처음에서 20개 뛰어넘고 10개
    public int getSkip(){
        return (page - 1) * 10;
    }

}
