package cn.edu.gxmzu.ai.mickhu.mapper.test;

import cn.edu.gxmzu.ai.mickhu.entity.test.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 0:48
 */

@Mapper
public interface MemberMapper {
    int insert(@Param("member") Member member);

    List<Member> selectAll();
}
