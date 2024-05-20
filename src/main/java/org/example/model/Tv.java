package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tv extends BaseModel{
    private String model;
    private String modelBy;
    private String price;
    private String dmu;
    private int count;
    private String fileId;
}
