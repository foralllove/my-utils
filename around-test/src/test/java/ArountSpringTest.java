import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.spt.sptest.bead.AroundTest;
import pers.spt.sptest.SpringTestAround;
import pers.spt.sptest.bead.BeanTest;

/**
 * test
 *
 * @author: hh
 * @since: 2021/6/6 20:07
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringTestAround.class)
public class ArountSpringTest {

    @Autowired
    AroundTest aroundTest;

    @Autowired
    BeanTest beanTest;

    @Test
    public void test() throws Exception {
        beanTest.testSout();
    }
}
