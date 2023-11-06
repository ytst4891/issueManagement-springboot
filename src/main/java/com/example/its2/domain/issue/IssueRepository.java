package com.example.its2.domain.issue;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface IssueRepository {

    @Select("select * from issues")
    List<IssueEntity> findAll();

    @Select("select * from issues where id = #{issueId}")
    IssueEntity findById(long issueId);

    @Insert("insert into issues (summary, description) values (#{summary}, #{description})")
    void insert(String summary, String description);

}
