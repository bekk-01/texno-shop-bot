package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phone extends BaseModel{
    private String model;
    private String modelBy;
    private String price;
    private String battery;
    private String displayHrz;
    private String count;
    private String fileId;
}
