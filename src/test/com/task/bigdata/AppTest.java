package com.task.bigdata;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class AppTest
{
    private final OwnHashMap ownHashMap = new OwnHashMap();
    private final OwnHashMap collisionOwnHashMap = new OwnHashMap();
    @Before
    public void fillWithData() {
        for (int i=0;i<200;i++)
        ownHashMap.put(i, String.valueOf(i));
    }

    @Test
    public void testSize() {
        assertEquals(ownHashMap.size(), 200);
        assertEquals(0, collisionOwnHashMap.size());
    }
    @Test
    public void testPutEntryWithExistingKey() {
        //inserting mapping for existing key (expected=old value('145'))
        assertEquals("145", ownHashMap.put(145, "NEW VALUE 1"));
        //testing, that value is overwritten from 145 to 'NEW VALUE 1'
        assertEquals("NEW VALUE 1", ownHashMap.put(145, "NEW VALUE 2"));
    }

    @Test
    public void testPutEntryWithNewKey() {
        //no previous value was for this key
        assertNull(ownHashMap.put(-100500, "-100500"));
        //now - present
        assertEquals("-100500", ownHashMap.get(-100500));
    }

    /*generating 10 elements to put so that all of them claim to go to the place №3
        this will cause collision and each next element has to find another place
    */
    @Test
    public void testPutWithCollision() {
        for (int i=3;i<256*10;i+=256)
            collisionOwnHashMap.put(i, String.valueOf(i));
        assertEquals(10, collisionOwnHashMap.size());
    }

    @Test
    public void testGet() {
            //key exists
            assertEquals("101", ownHashMap.get(101));
            //key doesn't exist
            assertNull(ownHashMap.get(201));
        }

}
