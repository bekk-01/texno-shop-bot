package org.example.repository;

import org.example.model.Phone;

public class PhoneRepository extends BaseRepository<Phone> {
    public PhoneRepository() {
        super.path = "src/main/resources/phone.json";
        super.type = Phone.class;
    }
}
