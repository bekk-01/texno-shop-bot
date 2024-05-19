package org.example.service;

import org.example.model.Phone;
import org.example.repository.PhoneRepository;

public class PhoneService extends BaseService<Phone, PhoneRepository> {
    private static final PhoneService phoneService = new PhoneService();

    public static PhoneService getInstance() {
        return phoneService;
    }
    private PhoneService() {
        super(new PhoneRepository());
    }
}
