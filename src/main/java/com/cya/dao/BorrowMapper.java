package com.cya.dao;

import com.cya.entity.Borrow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
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

    @Update("update borrow set status='已还' where id = #{id}")
    int updateBorrowStatus(Borrow borrow);

    @Select("select * from borrow where number = #{userId} and book_id = #{bookId} and status='借阅'")
    Borrow findBorrowByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") Integer bookId);

    @Select("select * from borrow where number = #{number} and book_id = #{bookId} and status='借阅'")
    Borrow findBorrowByUserIdAndBookId2(@Param("number") String number, @Param("bookId") Integer bookId);

    int updateBor(Map<String,Object> map);
    
    void deleteBorrowByBookId(Integer bookId);

    List<Borrow> searchBorrow(@Param("name") String name,@Param("isbn") String isbn,@Param("number") String number);

    List<Borrow> searchBackBorrow(@Param("name") String name,@Param("isbn") String isbn,@Param("number") String number);
}