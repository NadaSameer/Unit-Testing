package org.jfree.data.test;
import org.jfree.data.Range;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class RangeTest {
    @Test
    public void test1glb(){
        Range range = new Range(0.8,6.6);
        Assertions.assertEquals(0.8,range.getLowerBound());
        Assertions.assertNotEquals(0.4,range.getLowerBound());
    }
    @Test
    public void test2gub(){
        Range range = new Range(0.8,6.6);
        Assertions.assertEquals(6.6,range.getUpperBound());
        Assertions.assertNotEquals(7.6,range.getUpperBound());
    }
    @Test
    public void test3gl(){
        Range range = new Range(2,10);
        Assertions.assertEquals(8,range.getLength());
        Range range1 = new Range(10.73,20.65);
//        Assertions.assertEquals(9.92,range1.getLength(),0.05);// fail where it shouldn't in that range case
        Assertions.assertEquals(9.92,range1.getLength(),0.05);

    }
    @Test
    public void test4gcv(){
        Range range = new Range(2,10);
        Assertions.assertEquals(6,range.getCentralValue());
        Assertions.assertNotEquals(10,range.getCentralValue());
    }
    @Test
    public void test5contains(){
        Range range = new Range(2.4,7.6);
        Assertions.assertTrue(range.contains(3.4)); // passed
        Assertions.assertTrue(range.contains(2.4)); // fail where it shouldn't
        Assertions.assertTrue(range.contains(10.8)); // passed
        Assertions.assertFalse(range.contains(10.8)); // passed
        Assertions.assertFalse(range.contains(2.4)); // passed
    }
    @Test
    public void test6intersects(){
        //Always return true
        Range range = new Range(2.4,7.6);
        Assertions.assertTrue(range.intersects(3.5,5.5));
        Assertions.assertTrue(range.intersects(10.8,15.8)); // didn't fail where it must
        Assertions.assertFalse(range.intersects(10.8,15.8)); // fail where it shouldn't
    }
    @Test
    public void test7constrain(){
        Range range = new Range(10.5,20.5);
        Assertions.assertEquals(10.5,range.constrain(5.5));
        Assertions.assertEquals(11.5,range.constrain(11.5));
        Assertions.assertEquals(20.5,range.constrain(21.5));
    }
    @Test
    public void test8combine(){
        //fail when passing two ranges [return the second range] don't combine them
        Range range1 = new Range(20,30);
        Range range2 = new Range(5,10);
        Range expected = new Range(5,30);
        Assertions.assertSame(expected,Range.combine(range1,range2)); // fail where it shouldn't
        Assertions.assertSame(range1,Range.combine(range1,null));
        Assertions.assertSame(range2,Range.combine(null,range2));
        Assertions.assertSame(null,Range.combine(null,null));
    }
    @Test
    public void test9expandtoinclude(){
        Range range = new Range(20,30);
        Assertions.assertEquals(new Range(10,30),Range.expandToInclude(range,10));
        Assertions.assertEquals(new Range(20,40),Range.expandToInclude(range,40));
        Assertions.assertNotEquals(new Range(10,40),Range.expandToInclude(range,40));
    }
    @Test
    public void test10expand(){
        Range range = new Range(2.0,6.0);
        Assertions.assertEquals(new Range(1.0,8.0),Range.expand(range,0.25,0.5)); // fail where it shouldn't
        Assertions.assertThrows(IllegalArgumentException.class, () -> Range.expand(null,0.25,0.5));
    }
    @Test
    public void test11shift(){
        Range range = new Range(2.0,10.0);
        Assertions.assertEquals(new Range(4.0,12.0),Range.shift(range,2));
        Assertions.assertNotEquals(new Range(4.0,10.0),Range.shift(range,2));
        Assertions.assertEquals(new Range(5.0,13.0),Range.shift(range,3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Range.shift(null,2));
    }
    @Test
    public void test12shift() {
        Range range = new Range(-2.0, 10.0);
        Assertions.assertEquals(new Range(0.0, 12.0), Range.shift(range, 2, false));
        Assertions.assertNotEquals(new Range(1.0, 13.0), Range.shift(range, 2, false));
        Assertions.assertEquals(new Range(0.0, 13.0), Range.shift(range, 3, false));
        Assertions.assertNotEquals(new Range(1.0, 13.0), Range.shift(range, 3, false));
        Assertions.assertThrows(IllegalArgumentException.class, () -> Range.shift(null,2,false));
    }









}
