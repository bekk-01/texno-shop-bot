package org.example.service;

import org.example.model.Tv;
import org.example.repository.TvRepository;

public class TvService extends BaseService<Tv, TvRepository> {
    private static final TvService tvService = new TvService();

    public static TvService getInstance() {
        return tvService;
    }
    private TvService() {
        super(new TvRepository());
    }
}
