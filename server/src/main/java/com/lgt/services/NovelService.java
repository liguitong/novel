package com.lgt.services;

import com.lgt.beans.Novel;
import com.lgt.pojo.BizException;
import com.lgt.repository.NovelRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class NovelService {
    private static final Logger logger = LoggerFactory.getLogger(NovelService.class);
    @Autowired
    private NovelRepository novelRepository;

    public boolean addNovel(Novel novel) {
        try {
            logger.info("Try to add novel " + novel);
            novelRepository.save(novel);
        } catch (Exception e) {
            logger.error("Failed to add novel {} because of :{}", novel, e);
            return false;
        }
        logger.info("add novel {} succeed!", novel);
        return true;
    }

    public Novel queryNovel(int novelId) {
        Optional<Novel> novel = novelRepository.findByNovelId(novelId);
        if (novel.isPresent()) {
            return novel.get();
        } else {
            throw new BizException("No this novel");
        }
    }

    public List<Novel> queryAllNovels() {
        log.info("Try to find all novels");
        List<Novel> novelList = novelRepository.findAll();
        log.info("End to find all novels {}.", novelList.size());
        return novelList;
    }
}
