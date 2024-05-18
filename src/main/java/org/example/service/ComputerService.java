package org.example.service;

import org.example.model.Computer;
import org.example.repository.ComputerRepository;

public class ComputerService extends BaseService<Computer, ComputerRepository> {
    private static final ComputerService computer = new ComputerService();

    public static ComputerService getInstance() {
        return computer;
    }
    private ComputerService() {
        super(new ComputerRepository());
    }
}
