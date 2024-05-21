package org.example.repository;

import org.example.model.Computer;
import org.example.model.Tv;

import java.util.Optional;
import java.util.UUID;

public class TvRepository extends BaseRepository<Tv>{
    public TvRepository() {
        super.path = "src/main/resources/tv.json";
        super.type = Tv.class;
    }
    public Optional<Tv> getById(UUID startedProduct) {
        return getAll().stream().filter(computer -> computer.getId().equals(startedProduct)).findFirst();
    }
}
