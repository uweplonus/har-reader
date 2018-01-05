/*
 * Copyright 2017 Uwe Plonus
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sw4j.tool.har.io;

import java.io.StringReader;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import org.sw4j.tool.har.model.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HarReaderTest {

    private String emptyJson = "{}";

    private String requiredJson =
            "{\n" +
            "  \"log\": {\n" +
            "    \"version\": \"1.2\",\n" +
            "    \"creator\": {\n" +
            "      \"name\": \"HAR Test\",\n" +
            "      \"version\": \"2.2\"\n" +
            "    }\n" +
            "  }\n" +
            "}\n";

    private String optionalJson =
            "{\n" +
            "  \"log\": {\n" +
            "    \"version\": \"1.2\",\n" +
            "    \"creator\": {\n" +
            "      \"name\": \"HAR Test\",\n" +
            "      \"version\": \"2.2\",\n" +
            "      \"comment\": \"creator's comment\"\n" +
            "    },\n" +
            "    \"browser\": {\n" +
            "      \"name\": \"HAR Test\",\n" +
            "      \"version\": \"2.3\",\n" +
            "      \"comment\": \"browser's comment\"\n" +
            "    },\n" +
            "    \"pages\": [\n" +
            "      {\n" +
            "        \"startedDateTime\": \"2017-12-23T14:15:00+01:00\",\n" +
            "        \"id\": \"id0\",\n" +
            "        \"title\": \"Page 1\",\n" +
            "        \"pageTimings\": {\n" +
            "          \"onContentLoad\": 100.01,\n" +
            "          \"onLoad\": 200.02,\n" +
            "          \"comment\": \"Comment 1\"\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"startedDateTime\": \"2017-12-23T14:15:01+01:00\",\n" +
            "        \"id\": \"id1\",\n" +
            "        \"title\": \"Page 2\",\n" +
            "        \"pageTimings\": {\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "}\n";

    @Test
    public void testReaderCreation() {
        HarReader hr = new HarReader(new StringReader(emptyJson));
    }

    @Test
    public void testReadEmptyJsonWithCheck() {
        HarReader hr = new HarReader(new StringReader(emptyJson));
        try {
            Log log = hr.read(true);
            Assert.fail("Read should have thrown an exception.");
        } catch (AttributeRequiredException ex) {
            Assert.assertEquals("", ex.getObject(), "Expected the object with the missing attribute to be \"\"");
            Assert.assertEquals("log", ex.getAttribute(), "Expected the missing attribute to be \"log\".");
        }
    }

    @Test
    public void testReadEmptyJsonNoCheck() {
        HarReader hr = new HarReader(new StringReader(emptyJson));
        try {
            Log log = hr.read(false);
            Assert.assertNull(log, "Expected to get a null object when not checking.");
        } catch (AttributeRequiredException ex) {
            Assert.fail("Did not expect the reading to fail.");
        }
    }

    @Test
    public void testReadLogNoCheck() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(false);
        Assert.assertNotNull(log, "Expected a nonnull log object.");
    }

    @Test
    public void testReadLogWithCheck() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log, "Expected a nonnull log object.");
    }

    @Test
    public void testReadLogVersion() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getVersion(), "Expected a nonnull log.version object.");
        Assert.assertEquals(log.getVersion(), "1.2", "Expected the version to be \"1.2\".");
    }

    @Test
    public void testReadLogCreator() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getCreator(), "Expected a nonnull log.creator object.");
    }

    @Test
    public void testReadLogCreatorName() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getCreator().getName(), "Expected a nonnull log.creator.name object.");
        Assert.assertEquals(log.getCreator().getName(), "HAR Test",
                "Expected the creator.name to be \"HAR Test\".");
    }

    @Test
    public void testReadLogCreatorVersion() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getCreator().getVersion(), "Expected a nonnull log.creator.version object.");
        Assert.assertEquals(log.getCreator().getVersion(), "2.2",
                "Expected the creator.version to be \"2.2\".");
    }

    @Test
    public void testReadLogCreatorComment() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getCreator().getComment(), "Expected a nonnull log.creator.comment object.");
        Assert.assertEquals(log.getCreator().getComment(), "creator's comment",
                "Expected the creator.comment to be \"creator's comment\".");
    }

    @Test
    public void testReadLogNoBrowser() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNull(log.getBrowser(), "Expected a null log.browser object.");
    }

    @Test
    public void testReadLogBrowser() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getBrowser(), "Expected a nonnull log.browser object.");
    }

    @Test
    public void testReadLogBrowserName() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getBrowser().getName(), "Expected a nonnull log.browser.name object.");
        Assert.assertEquals(log.getBrowser().getName(), "HAR Test",
                "Expected the browser.name to be \"HAR Test\".");
    }

    @Test
    public void testReadLogBrowserVersion() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getBrowser().getVersion(), "Expected a nonnull log.browser.version object.");
        Assert.assertEquals(log.getBrowser().getVersion(), "2.3",
                "Expected the browser.version to be \"2.3\".");
    }

    @Test
    public void testReadLogBrowserComment() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getBrowser().getComment(), "Expected a nonnull log.browser.comment object.");
        Assert.assertEquals(log.getBrowser().getComment(), "browser's comment",
                "Expected the browser.version to be \"browser's comment\".");
    }

    @Test
    public void testReadLogPages() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getPages(), "Expected a nonnull log.pages object.");
        Assert.assertEquals(log.getPagesSize(), 2,
                "Expected the pages size to be 2.");
    }

    @Test
    public void testReadLogPagesStartedDateTime() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getPage(0).getStartedDateTime(),
                "Expected a nonnull log.pages[0].startedDateTime object.");
        Assert.assertEquals(log.getPage(0).getStartedDateTime().format(DateTimeFormatter.ISO_DATE_TIME),
                "2017-12-23T14:15:00+01:00",
                "Expected the pages[0].startedDateTime to be \"2017-12-23T14:15:00+01:00\".");
    }

    @Test
    public void testReadLogPagesId() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getPage(0).getId(),
                "Expected a nonnull log.pages[0].id object.");
        Assert.assertEquals(log.getPage(0).getId(), "id0",
                "Expected the pages[0].id to be \"id0\".");
    }

    @Test
    public void testReadLogPagesTitle() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getPage(0).getTitle(),
                "Expected a nonnull log.pages[0].title object.");
        Assert.assertEquals(log.getPage(0).getTitle(), "Page 1",
                "Expected the pages[0].title to be \"Page 1\".");
    }

    @Test
    public void testReadLogPagesPageTimings() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getPage(0).getPageTimings(),
                "Expected a nonnull log.pages[0].pageTimings object.");
    }

    @Test
    public void testReadLogPagesPageTimimgsOnContentLoad() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getPage(0).getPageTimings().getOnContentLoad(),
                "Expected a nonnull log.pages[0].pageTimings.onContentLoad object.");
        Assert.assertEquals(log.getPage(0).getPageTimings().getOnContentLoad(), new BigDecimal("100.01"),
                "Expected the pages[0].pageTimings.onContentLoad to be 100.01.");
    }

    @Test
    public void testReadLogPagesPageTimimgsOnLoad() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getPage(0).getPageTimings().getOnLoad(),
                "Expected a nonnull log.pages[0].pageTimings.onLoad object.");
        Assert.assertEquals(log.getPage(0).getPageTimings().getOnLoad(), new BigDecimal("200.02"),
                "Expected the pages[0].pageTimings.onLoad to be 200.02.");
    }

    @Test
    public void testReadLogPagesPageTimimgsComment() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getPage(0).getPageTimings().getComment(),
                "Expected a nonnull log.pages[0].pageTimings.comment object.");
        Assert.assertEquals(log.getPage(0).getPageTimings().getComment(), "Comment 1",
                "Expected the pages[0].pageTimings.comment to be \"Comment 1\".");
    }

}
