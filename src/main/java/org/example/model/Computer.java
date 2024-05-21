package org.example.model;

import lombok.*;

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
    private String count;
    private String fileId;


}
