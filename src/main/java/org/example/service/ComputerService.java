package org.example.service;

import org.example.exception.DataNotFoundException;
import org.example.model.Computer;
import org.example.model.User;
import org.example.repository.ComputerRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class ComputerService extends BaseService<Computer, ComputerRepository> {
    private static final ComputerService computer = new ComputerService();

    public static ComputerService getInstance() {
        return computer;
    }
    private ComputerService() {
        super(new ComputerRepository());
    }

    public Computer getById(UUID startedProduct) {
        return repository.getById(startedProduct).orElseThrow(() -> new RuntimeException());
    }
    public void update(Computer update){
        ArrayList<Computer> all = repository.getAll();
        int i = 0;
        for (Computer computer1 : all) {
            if(Objects.equals(computer1.getId(),update.getId())){
                all.set(i,update);
                break;
            }
            i++;
        }
        repository.writeFile(all);
    }
}
