package com.lgt.repository;

import com.lgt.beans.Novel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NovelRepository extends MongoRepository<Novel, Integer> {

    Optional<Novel> findByNovelId(int novelId);
}
