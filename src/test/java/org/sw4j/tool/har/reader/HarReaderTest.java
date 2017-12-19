package org.sw4j.tool.har.reader;

import org.sw4j.tool.har.model.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;

public class HarReaderTest {

    @Test
    public void testReaderCreation() throws UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("empty.json"), "UTF-8"));
    }

    @Test
    public void testReadEmptyJson() throws UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("empty.json"), "UTF-8"));
        try {
            Log log = hr.read(true);
            Assert.fail("Read should have thrown an exception.");
        } catch (AttributeRequiredException ex) {
            Assert.assertEquals(ex.getObject(), "har", "Expected the object with the missing attribute to be \"har\"");
            Assert.assertEquals(ex.getAttribute(), "log", "Expected the missing attribute to be \"log\".");
        }
    }

    @Test
    public void testReadLog() throws AttributeRequiredException, UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("required.json"), "UTF-8"));
        Log log = hr.read(false);
        Assert.assertNotNull(log, "Expected a nonnull log object.");
    }

    @Test
    public void testReadLogVersion() throws AttributeRequiredException, UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("required.json"), "UTF-8"));
        Log log = hr.read(false);
        Assert.assertNotNull(log.getVersion(), "Expected a nonnull log.version object.");
        Assert.assertEquals("1.2", log.getVersion(), "Expected the version to be \"1.2\".");
    }

    @Test
    public void testReadLogCreator() throws AttributeRequiredException, UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("required.json"), "UTF-8"));
        Log log = hr.read(false);
        Assert.assertNotNull(log.getCreator(), "Expected a nonnull log.creator object.");
    }

    @Test
    public void testReadLogCreatorName() throws AttributeRequiredException, UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("required.json"), "UTF-8"));
        Log log = hr.read(false);
        Assert.assertNotNull(log.getCreator().getName(), "Expected a nonnull log.creator.name object.");
        Assert.assertEquals("HAR Test", log.getCreator().getName(),
                "Expected the creator.name to be \"HAR Test\".");
    }

    @Test
    public void testReadLogNoBrowser() throws AttributeRequiredException, UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("required.json"), "UTF-8"));
        Log log = hr.read(false);
        Assert.assertNull(log.getBrowser(), "Expected a null log.browser object.");
    }

    @Test
    public void testReadLogBrowser() throws AttributeRequiredException, UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("optional.json"), "UTF-8"));
        Log log = hr.read(false);
        Assert.assertNotNull(log.getBrowser(), "Expected a nonnull log.browser object.");
    }

    @Test
    public void testReadLogBrowserName() throws AttributeRequiredException, UnsupportedEncodingException {
        HarReader hr = new HarReader(new InputStreamReader(getClass().getResourceAsStream("optional.json"), "UTF-8"));
        Log log = hr.read(false);
        Assert.assertNotNull(log.getBrowser().getName(), "Expected a nonnull log.browser.name object.");
        Assert.assertEquals("HAR Test", log.getBrowser().getName(),
                "Expected the browser.name to be \"HAR Test\".");
    }

}
