package com.lgt.repository;

import com.lgt.beans.Chapter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends MongoRepository<Chapter, Integer> {
    Optional<Chapter> findByNovelIdAndChapterId(int novelId, int chapterId);

    @Query(fields = "{'chapterId':1,'chapterName':1,'novelId':1}")
    List<Chapter> findByNovelId(int novelId);

}
