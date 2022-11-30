package com.lgt.controller;

import com.lgt.annotation.ResponseResult;
import com.lgt.beans.Chapter;
import com.lgt.beans.Novel;
import com.lgt.services.ChapterService;
import com.lgt.services.NovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseResult
@RequestMapping(value = "/novel")
public class NovelController {
    @Autowired
    NovelService novelService;
    @Autowired
    ChapterService chapterService;

    @PostMapping("/add")
    public boolean addNovel(@RequestBody Novel novel) {
        return novelService.addNovel(novel);
    }

    @GetMapping("/find")
    public Novel findNovel(@RequestParam("novelId") int novelId) {
        Novel novel = novelService.queryNovel(novelId);
        return novel;
    }

    @GetMapping("/findAll")
    public List<Novel> findAllNovel() {
        return novelService.queryAllNovels();
    }

    @PostMapping("/addChapter")
    public boolean addChapter(@RequestBody Chapter chapter) {
        return chapterService.addChapter(chapter);
    }

    @GetMapping("/findChapter")
    public Chapter findChapter(@RequestParam("novelId") int novelId, @RequestParam("chapterId") int chapterId) {
        Chapter chapter = chapterService.queryChapter(novelId, chapterId);
        return chapter;
    }

    @GetMapping("/findChaptersByNovelId")
    public List<Chapter> findChaptersByNovelId(@RequestParam("novelId") int novelId) {
        List<Chapter> chapters = chapterService.queryChaptersByNovelId(novelId);
        return chapters;
    }

    @GetMapping("/deleteChapter")
    public boolean deleteChapter(@RequestParam("novelId") int novelId, @RequestParam("chapterId") int chapterId) {
        return chapterService.deleteChapter(findChapter(novelId, chapterId));
    }

    @PostMapping("/updateChapter")
    public boolean updateChapter(@RequestBody Chapter chapter) {
        return chapterService.updateChapter(chapter);
    }

}
