package com.cya.dao;

import com.cya.entity.Book;
import com.cya.util.ro.BookPageIn;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description 图书
 */
@Mapper
@Component
public interface BookMapper {

    List<Book> findBookListByLike(BookPageIn book);

    /**
     * 编辑用户
     * @param map
     * @return
     */
    int updateBook(Map<String, Object> map);
}
