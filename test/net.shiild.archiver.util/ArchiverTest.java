package net.shiild.archiver.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Archiver.java.
 * 
 * @author StephenHildebrand
 */
public class ArchiverTest {

    private Archiver arch, arch2, arch3, arch4;

    private String small = "LOVE, LOVE ME DO!  YOU KNOW I LOVE YOU, YOU, YOU.\n";
    private String smallExpected = "LOVE, 1 ME DO!  YOU KNOW I 6 4, 1, 1.\n";

    private String medium = "On the future!  How it tells\nof the rapture that impels\nto the swinging and the ringing of the bells, bells, bells,\nof the bells, bells, bells, bells, bells, bells, bells,\nto the rhyming and the chiming of the bells.\n    - Edgar Allan Poe\n";
    private String mediumExpected = "On the future!  How it tells\nof 6 rapture that impels\nto 5 swinging and 3 ringing 9 3 bells, 1, 1,\n3 3 3, 1, 1, 1, 1, 1, 1,\n7 3 rhyming 7 3 chiming 7 3 7.\n    - Edgar Allan Poe\n";

    @Before
    public void setUp() throws Exception {
        arch = new Archiver(small);
        arch2 = new Archiver(medium);
        arch3 = new Archiver(smallExpected);
        arch4 = new Archiver(mediumExpected);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void archive() throws Exception {
        arch.archive();
        assertEquals(smallExpected, arch.getOutput());
        arch2.archive();
        assertEquals(mediumExpected, arch2.getOutput());
        assertEquals(37, arch.getArchiveCharCount());
        assertEquals(171, arch2.getArchiveCharCount());
    }

    @Test
    public void extract() throws Exception {
        arch3.extract();
        assertEquals(small, arch3.getOutput());
        arch4.extract();
        assertEquals(medium, arch4.getOutput());
    }

    @Test
    public void getOutput() throws Exception {
    }

    @Test
    public void getTextCharCount() throws Exception {
    }

    @Test
    public void getArchiveCharCount() throws Exception {
    }
}
