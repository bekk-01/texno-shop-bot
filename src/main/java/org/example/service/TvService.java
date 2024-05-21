package org.example.service;

import org.example.model.Computer;
import org.example.model.Tv;
import org.example.repository.TvRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class TvService extends BaseService<Tv, TvRepository> {
    private static final TvService tvService = new TvService();

    public static TvService getInstance() {
        return tvService;
    }
    private TvService() {
        super(new TvRepository());
    }
    public Tv getById(UUID startedProduct) {
        return repository.getById(startedProduct).orElseThrow(() -> new RuntimeException());
    }
    public void update(Tv update){
        ArrayList<Tv> all = repository.getAll();
        int i = 0;
        for (Tv tv : all) {
            if(Objects.equals(tv.getId(),update.getId())){
                all.set(i,update);
                break;
            }
            i++;
        }
        repository.writeFile(all);
    }
    public ArrayList<Tv> searchByTitle(String text){
        ArrayList<Tv> list = new ArrayList<>();
        for (Tv tv : getAll()) {
            if(tv.getModel().contains(text)){
                list.add(tv);
            }
        }
        return list;
    }
}
