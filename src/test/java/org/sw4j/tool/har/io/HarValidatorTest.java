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

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import org.sw4j.tool.har.model.Browser;
import org.sw4j.tool.har.model.Cookie;
import org.sw4j.tool.har.model.Creator;
import org.sw4j.tool.har.model.Entry;
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Log;
import org.sw4j.tool.har.model.Page;
import org.sw4j.tool.har.model.PageTimings;
import org.sw4j.tool.har.model.Request;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HarValidatorTest {

    private Har model;

    @BeforeMethod
    public void setUp() {
        model = new Har();
        Log log = new Log();
        model.setLog(log);
        log.setVersion("1.2");
        Creator creator = new Creator();
        log.setCreator(creator);
        creator.setName("creator");
        creator.setVersion("2.1");
        creator.setComment("creator's comment");
        Browser browser = new Browser();
        log.setBrowser(browser);
        browser.setName("browser");
        browser.setVersion("2.2");
        browser.setComment("browser's comment");

        createPages(log);
        createEntries(log);

        log.setComment("Log Comment");
    }

    private void createPages(Log log) {
        Page page1 = new Page();
        log.addPage(page1);
        page1.setStartedDateTime(OffsetDateTime.now());
        page1.setId("id0");
        page1.setTitle("Page 1");
        PageTimings pageTimings = new PageTimings();
        pageTimings.setOnContentLoad(new BigDecimal("15.01"));
        pageTimings.setOnLoad(new BigDecimal("20.02"));
        pageTimings.setComment("Comment 1");
        page1.setPageTimings(pageTimings);
        page1.setComment("Page Comment 1");

        Page page2 = new Page();
        log.addPage(page2);
        page2.setStartedDateTime(page1.getStartedDateTime().plusSeconds(5));
        page2.setId("id1");
        page2.setTitle("Page 2");
        pageTimings = new PageTimings();
        pageTimings.setOnContentLoad(new BigDecimal("17.37"));
        pageTimings.setOnLoad(new BigDecimal("22.03"));
        pageTimings.setComment("Comment 2");
        page2.setPageTimings(pageTimings);
        page2.setComment("Page Comment 2");

        // Gson reads a null element if the array ends with a comma.
        log.addPage(null);
    }

    private void createEntries(Log log) {
        Entry entry1 = new Entry();
        log.addEntry(entry1);
        entry1.setPageref("id0");
        entry1.setStartedDateTime(OffsetDateTime.now().plusSeconds(1));
        entry1.setTime(new BigDecimal("100.01"));
        Request request = new Request();
        entry1.setRequest(request);
        request.setMethod("GET");
        request.setUrl("https://example.org/example1");
        request.setHttpVersion("HTTP/1.1");
        request.createEmptyCookies();

        Entry entry2 = new Entry();
        log.addEntry(entry2);
        entry2.setPageref("id1");
        entry2.setStartedDateTime(entry1.getStartedDateTime().plusSeconds(5));
        entry2.setTime(new BigDecimal("200.02"));
        request = new Request();
        entry2.setRequest(request);
        request.setMethod("POST");
        request.setUrl("https://example.com/example2");
        request.setHttpVersion("http/2.0");
        createCookies(request);

        // Gson reads a null element if the array ends with a comma.
        log.addEntry(null);
    }

    private void createCookies(Request request) {
        Cookie cookie1 = new Cookie();
        request.addCookie(cookie1);
        cookie1.setName("cookie1");

        Cookie cookie2 = new Cookie();
        request.addCookie(cookie2);
        cookie2.setName("cookie2");

        // Gson reads a null element if the array ends with a comma.
        request.addCookie(null);
    }

    @Test
    public void testHarNullMissingSize() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(null);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected a single attribute to be missing.");
    }

    @Test
    public void testHarNullMissingValue() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(null);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("", "har"),
                "Expected the parent to be \"\" and the attribute to be \"har\"");
    }

    @Test
    public void testLogNullMissingSize() {
        model.setLog(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected a single attribute to be missing.");
    }

    @Test
    public void testLogNullMissingValue() {
        model.setLog(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("", "log"),
                "Expected the parent to be \"\" and the attribute to be \"log\"");
    }

    @Test
    public void testNoMissing() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testLogVersionMissingSize() {
        model.getLog().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testLogVersionMissingValue() {
        model.getLog().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log", "version"),
                "Expected the parent to be \"log\" and the attribute to be \"version\".");
    }

    @Test
    public void testCreatorMissingSize() {
        model.getLog().setCreator(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testCreatorMissingValue() {
        model.getLog().setCreator(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log", "creator"),
                "Expected the parent to be \"log\" and the attribute to be \"creator\".");
    }

    @Test
    public void testCreatorNameMissingSize() {
        model.getLog().getCreator().setName(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testCreatorNameMissingValue() {
        model.getLog().getCreator().setName(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.creator", "name"),
                "Expected the parent to be \"log.creator\" and the attribute to be \"name\".");
    }

    @Test
    public void testCreatorVersionMissingSize() {
        model.getLog().getCreator().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testCreatorVersionMissingValue() {
        model.getLog().getCreator().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.creator", "version"),
                "Expected the parent to be \"log.creator\" and the attribute to be \"version\".");
    }

    @Test
    public void testCreatorCommentMissing() {
        model.getLog().getCreator().setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testBrowserMissing() {
        model.getLog().setBrowser(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testBrowserNameMissingSize() {
        model.getLog().getBrowser().setName(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testBrowserNameMissingValue() {
        model.getLog().getBrowser().setName(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.browser", "name"),
                "Expected the parent to be \"log.browser\" and the attribute to be \"name\".");
    }

    @Test
    public void testBrowserVersionMissingSize() {
        model.getLog().getBrowser().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testBrowserVersionMissingValue() {
        model.getLog().getBrowser().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.browser", "version"),
                "Expected the parent to be \"log.browser\" and the attribute to be \"version\".");
    }

    @Test
    public void testBrowserCommentMissing() {
        model.getLog().getBrowser().setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesMissing() {
        model.getLog().clearPages();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesAvailable() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesStartedDateTimeMissingSize() {
        model.getLog().getPage(0).setStartedDateTime(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testPagesStartedDateTimeMissingValue() {
        model.getLog().getPage(0).setStartedDateTime(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.pages[0]",
                "startedDateTime"),
                "Expected the parent to be \"log.pages[0]\" and the attribute to be \"startedDateTime\"");
    }

    @Test
    public void testPagesIdMissingSize() {
        model.getLog().getPage(0).setId(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testPagesIdMissingValue() {
        model.getLog().getPage(0).setId(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.pages[0]", "id"),
                "Expected the parent to be \"log.pages[0]\" and the attribute to be \"id\"");
    }

    @Test
    public void testPagesTitleMissingSize() {
        model.getLog().getPage(0).setTitle(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testPagesTitleMissingValue() {
        model.getLog().getPage(0).setTitle(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.pages[0]", "title"),
                "Expected the parent to be \"log.pages[0]\" and the attribute to be \"title\"");
    }

    @Test
    public void testPagesPageTimingsMissingSize() {
        model.getLog().getPage(0).setPageTimings(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testPagesPageTimingsMissingValue() {
        model.getLog().getPage(0).setPageTimings(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0),
                new HarValidator.RequiredAttribute("log.pages[0]", "pageTimings"),
                "Expected the parent to be \"log.pages[0]\" and the attribute to be \"pageTimings\"");
    }

    @Test
    public void testPagesPageTimingsOnContentLoadMissing() {
        model.getLog().getPage(0).getPageTimings().setOnContentLoad(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesPageTimingsOnLoadMissing() {
        model.getLog().getPage(0).getPageTimings().setOnLoad(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesPageTimingsCommentMissing() {
        model.getLog().getPage(0).getPageTimings().setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesCommentMissing() {
        model.getLog().getPage(0).setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testEntriesMissingSize() {
        model.getLog().clearEntries();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertFalse(missingAttributes.isEmpty(), "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesMissingValue() {
        model.getLog().clearEntries();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log", "entries"),
                "Expected the parent to be \"log\" and the attribute to be \"entries\"");
    }

    @Test
    public void testEntriesAvailable() {
        model.getLog().createEmptyEntries();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testEntriesPagerefMissing() {
        model.getLog().getEntry(0).setPageref(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testEntriesStartedDateTimeMissingSize() {
        model.getLog().getEntry(0).setStartedDateTime(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesStartedDateTimeMissingValue() {
        model.getLog().getEntry(0).setStartedDateTime(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0),
                new HarValidator.RequiredAttribute("log.entries[0]", "startedDateTime"),
                "Expected the parent to be \"log.entries[0]\" and the attribute to be \"startedDateTime\"");
    }

    @Test
    public void testEntriesTimeMissingSize() {
        model.getLog().getEntry(0).setTime(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesTimeMissingValue() {
        model.getLog().getEntry(0).setTime(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.entries[0]", "time"),
                "Expected the parent to be \"log.entries[0]\" and the attribute to be \"time\"");
    }

    @Test
    public void testEntriesRequestMissingSize() {
        model.getLog().getEntry(0).setRequest(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesRequestMissingValue() {
        model.getLog().getEntry(0).setRequest(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0), new HarValidator.RequiredAttribute("log.entries[0]", "request"),
                "Expected the parent to be \"log.entries[0]\" and the attribute to be \"request\"");
    }

    @Test
    public void testEntriesRequestMethodMissingSize() {
        model.getLog().getEntry(0).getRequest().setMethod(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesRequestMethodMissingValue() {
        model.getLog().getEntry(0).getRequest().setMethod(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0),
                new HarValidator.RequiredAttribute("log.entries[0].request", "method"),
                "Expected the parent to be \"log.entries[0].request\" and the attribute to be \"method\"");
    }

    @Test
    public void testEntriesRequestUrlMissingSize() {
        model.getLog().getEntry(0).getRequest().setUrl(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesRequestUrlMissingValue() {
        model.getLog().getEntry(0).getRequest().setUrl(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0),
                new HarValidator.RequiredAttribute("log.entries[0].request", "url"),
                "Expected the parent to be \"log.entries[0].request\" and the attribute to be \"url\"");
    }

    @Test
    public void testEntriesRequestHttpVersionMissingSize() {
        model.getLog().getEntry(0).getRequest().setHttpVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesRequestHttpVersionMissingValue() {
        model.getLog().getEntry(0).getRequest().setHttpVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0),
                new HarValidator.RequiredAttribute("log.entries[0].request", "httpVersion"),
                "Expected the parent to be \"log.entries[0].request\" and the attribute to be \"httpVersion\"");
    }

    @Test
    public void testEntriesCookiesMissingSize() {
        model.getLog().getEntry(0).getRequest().clearCookies();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertFalse(missingAttributes.isEmpty(), "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesCookiesMissingValue() {
        model.getLog().getEntry(0).getRequest().clearCookies();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0),
                new HarValidator.RequiredAttribute("log.entries[0].request", "cookies"),
                "Expected the parent to be \"log.entries[0].request\" and the attribute to be \"cookies\"");
    }

    @Test
    public void testEntriesCookiesEmpty() {
        model.getLog().getEntry(0).getRequest().createEmptyCookies();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testEntriesCookiesNameMissingSize() {
        model.getLog().getEntry(1).getRequest().getCookie(0).setName(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertFalse(missingAttributes.isEmpty(), "Expected an attribute to be missing.");
    }

    @Test
    public void testEntriesCookiesNameMissingValue() {
        model.getLog().getEntry(1).getRequest().getCookie(0).setName(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.get(0),
                new HarValidator.RequiredAttribute("log.entries[1].request.cookies[0]", "name"),
                "Expected the parent to be \"log.entries[1].request.cookies[0]\" and the attribute to be \"name\"");
    }

    @Test
    public void testLogCommentMissing() {
        model.getLog().setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

}
