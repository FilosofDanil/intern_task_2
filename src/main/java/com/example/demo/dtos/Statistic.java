package com.example.demo.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Statistic {
    int successfullyWrittenRows;
    int unsuccessfullyWrittenRows;
}
