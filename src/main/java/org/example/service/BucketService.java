package org.example.service;

import org.example.model.Bucket;
import org.example.repository.BucketRepository;

public class BucketService extends BaseService<Bucket, BucketRepository> {
    private final static BucketService bucketService = new BucketService();
    public BucketService() {
        super(new BucketRepository());
    }

    public static BucketService getInstance() {
        return bucketService;
    }
}
