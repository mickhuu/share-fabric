package cn.edu.gxmzu.ai.mickhu.mapper;

import cn.edu.gxmzu.ai.mickhu.entity.test.Member;
import cn.edu.gxmzu.ai.mickhu.service.test.MemberService;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 0:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceDemoApplicationTests {
    @Autowired
    private MemberService memberService;

    @Test
    public void testWrite() {
        Member member = new Member();
        member.setName("zhangsan");
        memberService.insert(member);
    }

    @Test
    public void testRead() {
        for (int i = 0; i < 4; i++) {
            memberService.selectAll();
        }
    }

    @Test
    public void testSave() {
        Member member = new Member();
        member.setName("wangwu");
        memberService.save(member);
    }

    @Test
    public void testReadFromMaster() {
        memberService.getToken("1234");
    }

}
