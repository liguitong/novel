db.chapter.find({"novelId":1})
db.chapter.find()
db.chapter.remove({"chapterId":3})
db.chapter.insert({"_id":2,"chapterId":3,"chapterName":"第三章","novelId":1,"content":"所有都已经线束！","_class":"com.lgt.beans.Chapter"})