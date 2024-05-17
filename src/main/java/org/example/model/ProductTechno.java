package org.example.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductTechno extends BaseModel {
    protected  String model;
    protected String modelBy;
    protected  String price;
}
