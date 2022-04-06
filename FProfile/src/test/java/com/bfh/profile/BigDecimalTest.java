package com.bfh.profile;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @author benfeihu
 */
public class BigDecimalTest {

    @Test
    public void test01() {
        BigDecimal decimal = new BigDecimal(100);
        BigDecimal r = decimal.remainder(new BigDecimal(9));
        System.out.println(r.intValue());
    }
}
