package org.sw4j.tool.har.reader;

import org.sw4j.tool.har.model.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.StringReader;

public class HarReaderTest {

    private String emptyJson = "{}";

    /** Test JSON containing all required attributes. */
    private String requiredJson =
            "{" +
            "  \"log\": {" +
            "    \"version\": \"1.2\"," +
            "    \"creator\": {" +
            "      \"name\": \"HAR Test\"" +
            "    }" +
            "  }" +
            "}"
    ;

    /** Test JSON containing all attributes (required and optional). */
    private String optionalJson =
            "{" +
            "  \"log\": {" +
            "    \"version\": \"1.2\"," +
            "    \"creator\": {" +
            "      \"name\": \"HAR Test\"" +
            "    }," +
            "    \"browser\": {" +
            "      \"name\": \"HAR Test\"" +
            "    }" +
            "  }" +
            "}"
            ;

    @Test
    public void testReaderCreation() {
        HarReader hr = new HarReader(new StringReader(emptyJson));
    }

    @Test
    public void testReadEmptyJson() {
        HarReader hr = new HarReader(new StringReader(emptyJson));
        Log log = hr.read();
        Assert.assertNull(log, "Expected a null log object.");
    }

    @Test
    public void testReadLog() {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read();
        Assert.assertNotNull(log, "Expected a nonnull log object.");
    }

    @Test
    public void testReadLogVersion() {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read();
        Assert.assertNotNull(log.getVersion(), "Expected a nonnull log.version object.");
        Assert.assertEquals("1.2", log.getVersion(), "Expected the version to be \"1.2\".");
    }

    @Test
    public void testReadLogCreator() {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read();
        Assert.assertNotNull(log.getCreator(), "Expected a nonnull log.creator object.");
    }

    @Test
    public void testReadLogCreatorName() {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read();
        Assert.assertNotNull(log.getCreator().getName(), "Expected a nonnull log.creator.name object.");
        Assert.assertEquals("HAR Test", log.getCreator().getName(),
                "Expected the creator.name to be \"HAR Test\".");
    }

    @Test
    public void testReadLogNoBrowser() {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read();
        Assert.assertNull(log.getBrowser(), "Expected a null log.browser object.");
    }

    @Test
    public void testReadLogBrowser() {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read();
        Assert.assertNotNull(log.getBrowser(), "Expected a nonnull log.browser object.");
    }

    @Test
    public void testReadLogBrowserName() {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read();
        Assert.assertNotNull(log.getBrowser().getName(), "Expected a nonnull log.browser.name object.");
        Assert.assertEquals("HAR Test", log.getBrowser().getName(),
                "Expected the browser.name to be \"HAR Test\".");
    }

}
