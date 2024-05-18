package org.example.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Computer extends BaseModel {
    private String model;
    private String modelBy;
    private String price;
    private String ram;
    private String cup;
    private int count;
    private UUID userId;

}
