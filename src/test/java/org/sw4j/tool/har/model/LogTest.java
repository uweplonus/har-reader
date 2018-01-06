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
package org.sw4j.tool.har.model;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogTest {

    @Test
    public void testLogNoPages() {
        Log log = new Log();
        log.clearPages();
        Assert.assertNull(log.getPages(), "Expected the pages to be null.");
    }

    @Test
    public void testLogNoPagesSize() {
        Log log = new Log();
        log.clearPages();
        Assert.assertEquals(log.getPagesSize(), 0, "Expected the number of pages to be 0.");
    }

    @Test
    public void testLogNoPagesPageNull() {
        Log log = new Log();
        log.clearPages();
        Assert.assertNull(log.getPage(0), "Expected the page #0 to be null.");
    }

    @Test
    public void testLogNoPagesNegIndex() {
        Log log = new Log();
        log.clearPages();
        Assert.assertNull(log.getPage(-1), "Expected the page #-1 to be null.");
    }

    @Test
    public void testLogNoPagesLargeIndex() {
        Log log = new Log();
        log.clearPages();
        Assert.assertNull(log.getPage(Integer.MAX_VALUE), "Expected the page #MAX_VAL to be null.");
    }

    @Test
    public void testLogEmptyPages() {
        Log log = new Log();
        log.createEmptyPages();
        Assert.assertNotNull(log.getPages(), "Expected the pages not to be null.");
    }

    @Test
    public void testLogEmptyPagesSize() {
        Log log = new Log();
        log.createEmptyPages();
        Assert.assertEquals(log.getPagesSize(), 0, "Expected the number of pages to be 0.");
    }

    @Test
    public void testLogEmptyPagesPageNull() {
        Log log = new Log();
        log.createEmptyPages();
        Assert.assertNull(log.getPage(0), "Expected the page #0 to be null.");
    }

    @Test
    public void testLogEmptyPagesNegIndex() {
        Log log = new Log();
        log.createEmptyPages();
        Assert.assertNull(log.getPage(-1), "Expected the page #-1 to be null.");
    }

    @Test
    public void testLogEmptyPagesLargeIndex() {
        Log log = new Log();
        log.createEmptyPages();
        Assert.assertNull(log.getPage(Integer.MAX_VALUE), "Expected the page #MAX_VAL to be null.");
    }

    @Test
    public void testLogPages() {
        Log log = new Log();
        Page page = new Page();
        log.addPage(page);
        Assert.assertNotNull(log.getPages(), "Expected the pages not to be null.");
    }

    @Test
    public void testLogPagesSize() {
        Log log = new Log();
        Page page = new Page();
        log.addPage(page);
        Assert.assertEquals(log.getPagesSize(), 1, "Expected the number of pages to be 1.");
    }

    @Test
    public void testLogPagesPageNotNull() {
        Log log = new Log();
        Page page = new Page();
        log.addPage(page);
        Assert.assertNotNull(log.getPage(0), "Expected the page #0 not to be null.");
    }

    @Test
    public void testLogPages2ndPageNotNull() {
        Log log = new Log();
        Page page = new Page();
        log.addPage(page);
        page = new Page();
        log.addPage(page);
        Assert.assertSame(log.getPage(1), page,"Expected the page #1 to be the same as set.");
    }

    @Test
    public void testLogPagesNegIndex() {
        Log log = new Log();
        Page page = new Page();
        log.addPage(page);
        Assert.assertNull(log.getPage(-1), "Expected the page #-1 to be null.");
    }

    @Test
    public void testLogPagesLargeIndex() {
        Log log = new Log();
        Page page = new Page();
        log.addPage(page);
        Assert.assertNull(log.getPage(Integer.MAX_VALUE), "Expected the page #MAX_VAL to be null.");
    }

    @Test
    public void testLogNoEntries() {
        Log log = new Log();
        log.clearEntries();
        Assert.assertNull(log.getEntries(), "Expected the entries to be null.");
    }

    @Test
    public void testLogNoEntriesSize() {
        Log log = new Log();
        log.clearEntries();
        Assert.assertEquals(log.getEntriesSize(), 0, "Expected the number of entries to be 0.");
    }

    @Test
    public void testLogNoEntriesEntryNull() {
        Log log = new Log();
        log.clearEntries();
        Assert.assertNull(log.getEntry(0), "Expected the entry #0 to be null.");
    }

    @Test
    public void testLogNoEntriesNegIndex() {
        Log log = new Log();
        log.clearEntries();
        Assert.assertNull(log.getEntry(-1), "Expected the entry #-1 to be null.");
    }

    @Test
    public void testLogNoEntriesLargeIndex() {
        Log log = new Log();
        log.clearEntries();
        Assert.assertNull(log.getEntry(Integer.MAX_VALUE), "Expected the entry #MAX_VAL to be null.");
    }

    @Test
    public void testLogEmptyEntries() {
        Log log = new Log();
        log.createEmptyEntries();
        Assert.assertNotNull(log.getEntries(), "Expected the entries not to be null.");
    }

    @Test
    public void testLogEmptyEntriesSize() {
        Log log = new Log();
        log.createEmptyEntries();
        Assert.assertEquals(log.getEntriesSize(), 0, "Expected the number of entries to be 0.");
    }

    @Test
    public void testLogEmptyEntriesEntryNull() {
        Log log = new Log();
        log.createEmptyEntries();
        Assert.assertNull(log.getEntry(0), "Expected the entry #0 to be null.");
    }

    @Test
    public void testLogEmptyEntriesNegIndex() {
        Log log = new Log();
        log.createEmptyEntries();
        Assert.assertNull(log.getEntry(-1), "Expected the entry #-1 to be null.");
    }

    @Test
    public void testLogEmptyEntriesLargeIndex() {
        Log log = new Log();
        log.createEmptyEntries();
        Assert.assertNull(log.getEntry(Integer.MAX_VALUE), "Expected the entry #MAX_VAL to be null.");
    }

    @Test
    public void testLogEntries() {
        Log log = new Log();
        Entry entry = new Entry();
        log.addEntry(entry);
        Assert.assertNotNull(log.getEntries(), "Expected the entries not to be null.");
    }

    @Test
    public void testLogEntriesSize() {
        Log log = new Log();
        Entry entry = new Entry();
        log.addEntry(entry);
        Assert.assertEquals(log.getEntriesSize(), 1, "Expected the number of entries to be 1.");
    }

    @Test
    public void testLogEntriesEntryNotNull() {
        Log log = new Log();
        Entry entry = new Entry();
        log.addEntry(entry);
        Assert.assertNotNull(log.getEntry(0), "Expected the entry #0 not to be null.");
    }

    @Test
    public void testLogEntries2ndEntryNotNull() {
        Log log = new Log();
        Entry entry = new Entry();
        log.addEntry(entry);
        entry = new Entry();
        log.addEntry(entry);
        Assert.assertSame(log.getEntry(1), entry,"Expected the entry #1 to be the same as set.");
    }

    @Test
    public void testLogEntriesNegIndex() {
        Log log = new Log();
        Entry entry = new Entry();
        log.addEntry(entry);
        Assert.assertNull(log.getEntry(-1), "Expected the entry #-1 to be null.");
    }

    @Test
    public void testLogEntriesLargeIndex() {
        Log log = new Log();
        Entry entry = new Entry();
        log.addEntry(entry);
        Assert.assertNull(log.getEntry(Integer.MAX_VALUE), "Expected the entry #MAX_VAL to be null.");
    }

}
