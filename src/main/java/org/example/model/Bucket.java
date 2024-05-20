package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Bucket extends BaseModel{
    private List<Computer> computers;
    private List<Phone> phones;
    private List<Tv> tvs;
}
