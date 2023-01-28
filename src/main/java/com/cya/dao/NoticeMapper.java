package com.cya.dao;

import com.cya.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Date 2022/9/4 16:35
 *
 */
@Mapper
@Component
public interface NoticeMapper {

    /**
     * 模糊分页查询公告
     * @param keyword 关键字
     * @return
     */
    List<Notice> findListByLike(String keyword);

    /**
      * 编辑公告
     * @param map
     * @return
     */
    int updateNotice(Map<String,Object> map);
}
