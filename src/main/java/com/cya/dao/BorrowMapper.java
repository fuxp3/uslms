package com.cya.dao;

import com.cya.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description 借阅管理
 * @Date 2022/9/4 16:35
 *
 */
@Mapper
@Component
public interface BorrowMapper {

    @Update("update borrow set user_id = #{userId},book_id = #{bookId},update_time = #{updateTime} where id = #{id}")
    int updateBorrow(Borrow borrow);

    @Select("select * from borrow where user_id = #{userId} and book_id = #{bookId}")
    Borrow findBorrowByUserIdAndBookId(@Param("userId") Integer userId, @Param("bookId") Integer bookId);

    @Select("select * from borrow where number = #{number} and book_id = #{bookId}")
    Borrow findBorrowByUserIdAndBookId2(@Param("number") String number, @Param("bookId") Integer bookId);

    int updateBor(Map<String,Object> map);
    
    void deleteBorrowByBookId(Integer bookId);
}