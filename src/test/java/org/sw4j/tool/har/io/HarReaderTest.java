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
            "    },\n" +
            "    \"entries\": [\n" +
            "      {\n" +
            "        \"startedDateTime\": \"2017-12-23T14:15:02+01:00\",\n" +
            "        \"time\": 100.01,\n" +
            "        \"request\": {\n" +
            "          \"method\": \"GET\",\n" +
            "          \"url\": \"https://example.org/example1\",\n" +
            "          \"httpVersion\": \"HTTP/1.1\",\n" +
            "          \"cookies\": [\n" +
            "          ]\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"startedDateTime\": \"2017-12-23T14:15:03+01:00\",\n" +
            "        \"time\": 200.02,\n" +
            "        \"request\": {\n" +
            "          \"method\": \"POST\",\n" +
            "          \"url\": \"https://example.com/example2\",\n" +
            "          \"httpVersion\": \"http/2.0\",\n" +
            "          \"cookies\": [\n" +
            "            {\n" +
            "              \"name\": \"cookie1\",\n" +
            "              \"value\": \"value1\"\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"cookie2\",\n" +
            "              \"value\": \"value2\"\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      }\n" +
            "    ]\n" +
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
            "        },\n" +
            "        \"comment\": \"Page Comment 1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"startedDateTime\": \"2017-12-23T14:15:01+01:00\",\n" +
            "        \"id\": \"id1\",\n" +
            "        \"title\": \"Page 2\",\n" +
            "        \"pageTimings\": {\n" +
            "        }\n" +
            "      }\n" +
            "    ],\n" +
            "    \"entries\": [\n" +
            "      {\n" +
            "        \"pageref\": \"id0\",\n" +
            "        \"startedDateTime\": \"2017-12-23T14:15:02+01:00\",\n" +
            "        \"time\": 101.01,\n" +
            "        \"request\": {\n" +
            "          \"method\": \"GET\",\n" +
            "          \"url\": \"https://example.org/example1\",\n" +
            "          \"httpVersion\": \"HTTP/1.1\",\n" +
            "          \"cookies\": [\n" +
            "          ]\n" +
            "        }\n" +
            "      },\n" +
            "      {\n" +
            "        \"startedDateTime\": \"2017-12-23T14:15:03+01:00\",\n" +
            "        \"time\": 202.02,\n" +
            "        \"request\": {\n" +
            "          \"method\": \"POST\",\n" +
            "          \"url\": \"https://example.com/example2\",\n" +
            "          \"httpVersion\": \"http/2.0\",\n" +
            "          \"cookies\": [\n" +
            "            {\n" +
            "              \"name\": \"cookie1\",\n" +
            "              \"value\": \"value1\",\n" +
            "              \"path\": \"/\",\n" +
            "              \"domain\": \"example.org\",\n" +
            "              \"expires\": \"2019-12-23T15:15:03+01:00\",\n" +
            "              \"httpOnly\": true,\n" +
            "              \"secure\": true\n" +
            "            },\n" +
            "            {\n" +
            "              \"name\": \"cookie2\",\n" +
            "              \"value\": \"value2\",\n" +
            "              \"path\": \"/path\",\n" +
            "              \"domain\": \"example.org\",\n" +
            "              \"expires\": \"2018-12-23T15:15:03+01:00\",\n" +
            "              \"httpOnly\": false,\n" +
            "              \"secure\": false\n" +
            "            }\n" +
            "          ]\n" +
            "        }\n" +
            "      }\n" +
            "    ],\n" +
            "    \"comment\": \"Log Comment\"\n" +
            "  }\n" +
            "}\n";

    @Test
    public void testReaderCreation() {
        HarReader hr = new HarReader(new StringReader(emptyJson));
        Assert.assertNotNull(hr, "Expected a HAR reader object to be created.");
    }

    @Test
    public void testReadEmptyJsonWithCheck() {
        HarReader hr = new HarReader(new StringReader(emptyJson));
        try {
            Log log = hr.read(true);
            Assert.fail("Read should have thrown an exception.");
        } catch (AttributeRequiredException ex) {
            Assert.assertEquals(ex, new AttributeRequiredException("", "log"),
                    "Expected the object to be \"\" with the missing attribute to be \"log\"");
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
        Assert.assertEquals(log.getCreator().getName(), "HAR Test",
                "Expected the creator.name to be \"HAR Test\".");
    }

    @Test
    public void testReadLogCreatorVersion() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getCreator().getVersion(), "2.2",
                "Expected the creator.version to be \"2.2\".");
    }

    @Test
    public void testReadLogCreatorComment() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
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
        Assert.assertEquals(log.getBrowser().getName(), "HAR Test",
                "Expected the browser.name to be \"HAR Test\".");
    }

    @Test
    public void testReadLogBrowserVersion() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getBrowser().getVersion(), "2.3",
                "Expected the browser.version to be \"2.3\".");
    }

    @Test
    public void testReadLogBrowserComment() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getBrowser().getComment(), "browser's comment",
                "Expected the browser.version to be \"browser's comment\".");
    }

    @Test
    public void testReadLogPagesNull() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNull(log.getPages(),
                "Expected the pages to be null.");
    }

    @Test
    public void testReadLogPages() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getPagesSize(), 2,
                "Expected the pages size to be 2.");
    }

    @Test
    public void testReadLogPagesStartedDateTime() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(DateTimeFormatter.ISO_DATE_TIME.format(log.getPage(0).getStartedDateTime()),
                "2017-12-23T14:15:00+01:00",
                "Expected the pages[0].startedDateTime to be \"2017-12-23T14:15:00+01:00\".");
    }

    @Test
    public void testReadLogPagesId() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getPage(0).getId(), "id0",
                "Expected the pages[0].id to be \"id0\".");
    }

    @Test
    public void testReadLogPagesTitle() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
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
        Assert.assertEquals(log.getPage(0).getPageTimings().getOnContentLoad(), new BigDecimal("100.01"),
                "Expected the pages[0].pageTimings.onContentLoad to be 100.01.");
    }

    @Test
    public void testReadLogPagesPageTimimgsOnLoad() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getPage(0).getPageTimings().getOnLoad(), new BigDecimal("200.02"),
                "Expected the pages[0].pageTimings.onLoad to be 200.02.");
    }

    @Test
    public void testReadLogPagesPageTimimgsComment() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getPage(0).getPageTimings().getComment(), "Comment 1",
                "Expected the pages[0].pageTimings.comment to be \"Comment 1\".");
    }

    @Test
    public void testReadLogPagesComment() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getPage(0).getComment(), "Page Comment 1",
                "Expected the pages[0].comment to be \"Page Comment 1\".");
    }

    @Test
    public void testReadLogEntries() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntriesSize(), 2,
                "Expected the entries size to be 2.");
    }

    @Test
    public void testReadLogEntriesPageref() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(0).getPageref(), "id0",
                "Expected the entries[0].pageref to be \"id0\".");
    }

    @Test
    public void testReadLogEntriesStartedDateTime() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(0).getStartedDateTime().format(DateTimeFormatter.ISO_DATE_TIME),
                "2017-12-23T14:15:02+01:00",
                "Expected the entries[0].startedDateTime to be \"2017-12-23T14:15:02+01:00\".");
    }

    @Test
    public void testReadLogEntriesTime() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(0).getTime(), new BigDecimal("100.01"),
                "Expected the entries[0].startedDateTime to be 100.01.");
    }

    @Test
    public void testReadLogEntriesRequest() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getEntry(0).getRequest(),
                "Expected a nonnull log.entries[0].request object.");
    }

    @Test
    public void testReadLogEntriesRequestMethod() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(0).getRequest().getMethod(), "GET",
                "Expected the request.method to be \"GET\".");
    }

    @Test
    public void testReadLogEntriesRequestUrl() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(0).getRequest().getUrl(), "https://example.org/example1",
                "Expected the request.url to be \"https://example.org/example1\".");
    }

    @Test
    public void testReadLogEntriesRequestHttpVersion() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(0).getRequest().getHttpVersion(), "HTTP/1.1",
                "Expected the request.httpVersion to be \"HTTP/1.1\".");
    }

    @Test
    public void testReadLogEntriesRequestCookiesEmpty() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(0).getRequest().getCookiesSize(), 0,
                "Expected the request.cookies to be empty.");
    }

    @Test
    public void testReadLogEntriesRequestCookiesFilled() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(1).getRequest().getCookiesSize(), 2,
                "Expected the request.cookies not to be empty.");
    }

    @Test
    public void testReadLogEntriesRequestCookiesName() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(1).getRequest().getCookie(0).getName(), "cookie1",
                "Expected the cookie.name to be \"cookie1\".");
    }

    @Test
    public void testReadLogEntriesRequestCookiesValue() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(1).getRequest().getCookie(0).getValue(), "value1",
                "Expected the cookie.value to be \"value1\".");
    }

    @Test
    public void testReadLogEntriesRequestCookiesPath() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(1).getRequest().getCookie(0).getPath(), "/",
                "Expected the cookie.path to be \"/\".");
    }

    @Test
    public void testReadLogEntriesRequestCookiesDomain() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(1).getRequest().getCookie(0).getDomain(), "example.org",
                "Expected the cookie.domain to be \"domain.org\".");
    }

    @Test
    public void testReadLogEntriesRequestCookiesExpires() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(1).getRequest().getCookie(0).getExpires().format(
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME),
                "2019-12-23T15:15:03+01:00",
                "Expected the cookie.expires to be \"2019-12-23T15:15:03+01:00\".");
    }

    @Test
    public void testReadLogEntriesRequestCookiesHttpOnly() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(1).getRequest().getCookie(0).getHttpOnly(), Boolean.TRUE,
                "Expected the cookie.httpOnly to be true.");
    }

    @Test
    public void testReadLogEntriesRequestCookiesSecure() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getEntry(1).getRequest().getCookie(0).getSecure(), Boolean.TRUE,
                "Expected the cookie.secure to be true.");
    }

    @Test
    public void testReadLogComment() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertEquals(log.getComment(), "Log Comment",
                "Expected the log.comment to be \"Log Comment \".");
    }

}
