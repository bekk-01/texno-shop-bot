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

    public Computer(String model, String modelBy, String price, String ram, String cup, int count) {
        this.model = model;
        this.modelBy = modelBy;
        this.price = price;
        this.ram = ram;
        this.cup = cup;
        this.count = count;
    }
}
