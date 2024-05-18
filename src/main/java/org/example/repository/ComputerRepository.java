package org.example.repository;

import org.example.model.Computer;

public class ComputerRepository extends BaseRepository<Computer> {
    public ComputerRepository() {
        super.path = "src/main/resources/computers.json";
        super.type = Computer.class;
    }
}
