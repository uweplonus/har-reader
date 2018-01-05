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
import java.util.LinkedList;
import java.util.List;
import org.sw4j.tool.har.model.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HarValidatorTest {

    private Har model;

    private List<Page> pages;

    private List<Entry> entries;

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

        createPages();
        createEntries();

        log.setComment("Log Comment");
    }

    private void createPages() {
        pages = new LinkedList<>();

        Page page1 = new Page();
        pages.add(page1);
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
        pages.add(page2);
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
        pages.add(null);
    }

    private void addPagesToLog() {
        for (Page page: pages) {
            model.getLog().addPage(page);
        }
    }

    private void createEntries() {
        entries = new LinkedList<>();

        Entry entry1 = new Entry();
        entries.add(entry1);
        entry1.setPageref("id0");
        entry1.setStartedDateTime(OffsetDateTime.now().plusSeconds(1));
        entry1.setTime(new BigDecimal("100.01"));
        Request request = new Request();
        entry1.setRequest(request);
        request.setMethod("GET");

        Entry entry2 = new Entry();
        entries.add(entry2);
        entry2.setPageref("id1");
        entry2.setStartedDateTime(entry1.getStartedDateTime().plusSeconds(5));
        entry2.setTime(new BigDecimal("200.02"));
        request = new Request();
        entry2.setRequest(request);
        request.setMethod("POST");

        // Gson reads a null element if the array ends with a comma.
        entries.add(null);
    }

    private void addEntriesToLog() {
        for (Entry entry: entries) {
            model.getLog().addEntry(entry);
        }
    }

    @Test
    public void testHarNull() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(null);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected a single attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "", "Expected the parent to be \"\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "har", "Expected the attribute to be \"har\"");
    }

    @Test
    public void testLogNull() {
        model.setLog(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected a single attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "", "Expected the parent to be \"\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "log", "Expected the attribute to be \"log\"");
    }

    @Test
    public void testNoMissing() {
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testLogVersionMissing() {
        addEntriesToLog();
        model.getLog().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log", "Expected the parent to be \"log\".");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "version",
                "Expected the attribute to be \"version\".");
    }

    @Test
    public void testCreatorMissing() {
        addEntriesToLog();
        model.getLog().setCreator(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log", "Expected the parent to be \"log\".");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "creator",
                "Expected the attribute to be \"creator\".");
    }

    @Test
    public void testCreatorNameMissing() {
        addEntriesToLog();
        model.getLog().getCreator().setName(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.creator",
                "Expected the parent to be \"log.creator\".");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "name",
                "Expected the attribute to be \"name\".");
    }

    @Test
    public void testCreatorVersionMissing() {
        addEntriesToLog();
        model.getLog().getCreator().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.creator",
                "Expected the parent to be \"log.creator\".");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "version",
                "Expected the attribute to be \"version\".");
    }

    @Test
    public void testCreatorCommentMissing() {
        addEntriesToLog();
        model.getLog().getCreator().setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testBrowserMissing() {
        addEntriesToLog();
        model.getLog().setBrowser(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testBrowserNameMissing() {
        addEntriesToLog();
        model.getLog().getBrowser().setName(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.browser",
                "Expected the parent to be \"log.browser\".");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "name",
                "Expected the attribute to be \"name\".");
    }

    @Test
    public void testBrowserVersionMissing() {
        addEntriesToLog();
        model.getLog().getBrowser().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.browser",
                "Expected the parent to be \"log.browser\".");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "version",
                "Expected the attribute to be \"version\".");
    }

    @Test
    public void testBrowserCommentMissing() {
        addEntriesToLog();
        model.getLog().getBrowser().setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesMissing() {
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
        Assert.assertEquals(model.getLog().getPagesSize(), 0,
                "Expected the number of pages to be 0.");
        Assert.assertNull(model.getLog().getPages(), "Expected the pages to be null.");
        Assert.assertNull(model.getLog().getPage(0), "Expected the page #0 to be null.");
        Assert.assertNull(model.getLog().getPage(-1), "Expected the page #-1 to be null.");
        Assert.assertNull(model.getLog().getPage(Integer.MAX_VALUE), "Expected the page #MAX_VAL to be null.");
    }

    @Test
    public void testPagesAvailable() {
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
        Assert.assertEquals(model.getLog().getPagesSize(), pages.size(),
                "Expected the number of pages to be greater than 0.");
        Assert.assertNotNull(model.getLog().getPages(), "Expected the pages not to be null.");
        Assert.assertEquals(model.getLog().getPage(0), pages.get(0),
                "Expected the page #0 not to be null.");
        Assert.assertNull(model.getLog().getPage(-1), "Expected the page #-1 to be null.");
        Assert.assertNull(model.getLog().getPage(Integer.MAX_VALUE), "Expected the page #MAX_VAL to be null.");
    }

    @Test
    public void testPagesStartedDateTimeMissing() {
        pages.get(0).setStartedDateTime(null);
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.pages[0]",
                "Expected the parent to be \"log.pages[0]\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "startedDateTime",
                "Expected the attribute to be \"startedDateTime\"");
    }

    @Test
    public void testPagesIdMissing() {
        pages.get(0).setId(null);
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.pages[0]",
                "Expected the parent to be \"log.pages[0]\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "id",
                "Expected the attribute to be \"id\"");
    }

    @Test
    public void testPagesTitleMissing() {
        pages.get(0).setTitle(null);
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.pages[0]",
                "Expected the parent to be \"log.pages[0]\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "title",
                "Expected the attribute to be \"title\"");
    }

    @Test
    public void testPagesPageTimingsMissing() {
        pages.get(0).setPageTimings(null);
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.pages[0]",
                "Expected the parent to be \"log.pages[0]\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "pageTimings",
                "Expected the attribute to be \"pageTimings\"");
    }

    @Test
    public void testPagesPageTimingsOnContentLoadMissing() {
        pages.get(0).getPageTimings().setOnContentLoad(null);
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesPageTimingsOnLoadMissing() {
        pages.get(0).getPageTimings().setOnLoad(null);
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesPageTimingsCommentMissing() {
        pages.get(0).getPageTimings().setComment(null);
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesCommentMissing() {
        pages.get(0).setComment(null);
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testEntriesMissing() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertFalse(missingAttributes.isEmpty(), "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log",
                "Expected the parent to be \"log\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "entries",
                "Expected the attribute to be \"entries\"");
    }

    @Test
    public void testEntriesAvailable() {
        addPagesToLog();
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
        Assert.assertEquals(model.getLog().getEntriesSize(), entries.size(),
                "Expected the number of entries to be greater than 0.");
        Assert.assertNotNull(model.getLog().getEntries(), "Expected the entries not to be null.");
        Assert.assertEquals(model.getLog().getEntry(0), entries.get(0),
                "Expected the entry #0 not to be null.");
        Assert.assertNull(model.getLog().getEntry(-1), "Expected the entry #-1 to be null.");
        Assert.assertNull(model.getLog().getEntry(Integer.MAX_VALUE), "Expected the entry #MAX_VAL to be null.");
    }

    @Test
    public void testEntriesPagerefMissing() {
        entries.get(0).setPageref(null);
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testEntriesStartedDateTimeMissing() {
        entries.get(0).setStartedDateTime(null);
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.entries[0]",
                "Expected the parent to be \"log.entries[0]\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "startedDateTime",
                "Expected the attribute to be \"startedDateTime\"");
    }

    @Test
    public void testEntriesTimeMissing() {
        entries.get(0).setTime(null);
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.entries[0]",
                "Expected the parent to be \"log.entries[0]\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "time",
                "Expected the attribute to be \"time\"");
    }

    @Test
    public void testEntriesRequestMissing() {
        entries.get(0).setRequest(null);
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.entries[0]",
                "Expected the parent to be \"log.entries[0]\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "request",
                "Expected the attribute to be \"request\"");
    }

    @Test
    public void testEntriesRequestMethodMissing() {
        entries.get(0).getRequest().setMethod(null);
        addEntriesToLog();

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log.entries[0].request",
                "Expected the parent to be \"log.entries[0].method\"");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "method",
                "Expected the attribute to be \"method\"");
    }

    @Test
    public void testLogCommentMissing() {
        addEntriesToLog();
        model.getLog().setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

}
