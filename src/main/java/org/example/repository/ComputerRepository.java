package org.example.repository;

import org.example.model.Computer;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class ComputerRepository extends BaseRepository<Computer> {
    public ComputerRepository() {
        super.path = "src/main/resources/computers.json";
        super.type = Computer.class;
    }
    public Optional<Computer> getById(UUID startedProduct) {
        return getAll().stream().filter(computer -> computer.getId().equals(startedProduct)).findFirst();
    }

}
