package org.example.repository;

import org.example.model.Computer;
import org.example.model.Phone;

import java.util.Optional;
import java.util.UUID;

public class PhoneRepository extends BaseRepository<Phone> {
    public PhoneRepository() {
        super.path = "src/main/resources/phone.json";
        super.type = Phone.class;
    }
    public Optional<Phone> getById(UUID startedProduct) {
        return getAll().stream().filter(computer -> computer.getId().equals(startedProduct)).findFirst();
    }
}
