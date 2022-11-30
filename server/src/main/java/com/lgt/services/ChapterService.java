package com.lgt.services;

import com.lgt.beans.Chapter;
import com.lgt.pojo.BizException;
import com.lgt.repository.ChapterRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

@Service
@Slf4j
public class ChapterService {
    @Autowired
    private ChapterRepository chapterRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public boolean addChapter(Chapter chapter) {
        try {
            log.info("Try to add chapter with name " + chapter.getChapterName());
            chapterRepository.save(chapter);
        } catch (Exception e) {
            log.error("Failed to add chapter {} because of :{}", chapter, e);
            return false;
        }
        log.info("add chapter {} succeed! ", chapter.getChapterName());
        return true;
    }

    public boolean deleteChapter(Chapter chapter) {
        try {
            log.info("Try to delete chapter {}　", chapter.getChapterName());
            chapterRepository.delete(chapter);
        } catch (Exception e) {
            log.error("Failed to delete chapter {} because of: {}", chapter, e);
        }
        log.info("delete chapter {} succeed !", chapter.getChapterName());
        return true;
    }


    public Chapter queryChapter(int novelId, int chapterId) {
        Optional<Chapter> chapter = chapterRepository.findByNovelIdAndChapterId(novelId, chapterId);
        if (chapter.isPresent()) {
            return chapter.get();
        } else {
            throw new BizException("No this chapter");
        }
    }

    public List<Chapter> queryChaptersByNovelId(int novelId) {
        log.info("Try to find chapters by novelId {}", novelId);
        List<Chapter> chapters = chapterRepository.findByNovelId(novelId);
        log.info("End to find chapters by novelId {},get {} chapters.", novelId, chapters.size());
        return chapters;
    }


    public boolean updateChapter(Chapter chapter) {
        UpdateResult result = mongoTemplate.upsert(query(where("novelId").is(chapter.getNovelId()).and("chapterId").is(chapter.getChapterId())),
                update("content", chapter.getContent()), Chapter.class);
        return result.getModifiedCount() == 1;
    }
}
