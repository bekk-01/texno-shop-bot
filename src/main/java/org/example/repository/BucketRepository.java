package org.example.repository;

import org.example.model.Bucket;

public class BucketRepository extends BaseRepository<Bucket>{
    public BucketRepository() {
        super.path = "src/main/resources/buckets.json";
        super.type = Bucket.class;
    }
}
