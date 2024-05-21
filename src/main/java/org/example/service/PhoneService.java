package org.example.service;

import org.example.model.Computer;
import org.example.model.Phone;
import org.example.repository.PhoneRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class PhoneService extends BaseService<Phone, PhoneRepository> {
    private static final PhoneService phoneService = new PhoneService();

    public static PhoneService getInstance() {
        return phoneService;
    }
    private PhoneService() {
        super(new PhoneRepository());
    }
    public Phone getById(UUID startedProduct) {
        return repository.getById(startedProduct).orElseThrow(() -> new RuntimeException());
    }
    public void update(Phone update){
        ArrayList<Phone> all = repository.getAll();
        int i = 0;
        for (Phone phone : all) {
            if(Objects.equals(phone.getId(),update.getId())){
                all.set(i,update);
                break;
            }
            i++;
        }
        repository.writeFile(all);
    }
    public ArrayList<Phone> searchByTitle(String text){
        ArrayList<Phone> list = new ArrayList<>();
        for (Phone phone : getAll()) {
            if(phone.getModel().contains(text)){
                list.add(phone);
            }
        }
        return list;
    }
}
