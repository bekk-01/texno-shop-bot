package org.example.repository;

import org.example.model.Tv;

public class TvRepository extends BaseRepository<Tv>{
    public TvRepository() {
        super.path = "src/main/resources/tv.json";
        super.type = Tv.class;
    }
}
