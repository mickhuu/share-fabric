package cn.edu.gxmzu.ai.mickhu.service.impl.test;

import cn.edu.gxmzu.ai.mickhu.entity.test.Member;
import cn.edu.gxmzu.ai.mickhu.annotation.Master;
import cn.edu.gxmzu.ai.mickhu.service.test.MemberService;
import cn.edu.gxmzu.ai.mickhu.mapper.test.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 0:43
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Transactional
    @Override
    public int insert(Member member) {
        return memberMapper.insert(member);
    }

    @Master
    @Override
    public int save(Member member) {
        return memberMapper.insert(member);
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

    @Master
    @Override
    public String getToken(String appId) {
        //  有些读操作必须读主数据库
        //  比如，获取微信access_token，因为高峰时期主从同步可能延迟
        //  这种情况下就必须强制从主数据读
        return null;
    }
}