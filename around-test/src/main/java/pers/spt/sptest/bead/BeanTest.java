package pers.spt.sptest.bead;

import org.springframework.stereotype.Component;

/**
 * BeanTest
 *
 * @author: hh
 * @since: 2021/6/6 20:24
 */
@Component
public class BeanTest {
    public void testSout() throws Exception {

        System.out.println("业务");
        throw new Exception("");
    }
}
