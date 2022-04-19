package cn.edu.gxmzu.ai.mickhu.service.test;

import cn.edu.gxmzu.ai.mickhu.entity.test.Member;

import java.util.List;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 0:46
 */
public interface MemberService {
    int insert(Member member);

    int save(Member member);

    List<Member> selectAll();

    String getToken(String appId);
}
