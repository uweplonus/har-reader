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
        Assert.assertNull(log.getPages(), "Expected the pages to be null.");
    }

    @Test
    public void testLogNoPagesSize() {
        Log log = new Log();
        Assert.assertEquals(log.getPagesSize(), 0, "Expected the number of pages to be 0.");
    }

    @Test
    public void testLogPagesPageNull() {
        Log log = new Log();
        Assert.assertNull(log.getPage(0), "Expected the page #0 to be null.");
    }

    @Test
    public void testLogNoPagesNegIndex() {
        Log log = new Log();
        Assert.assertNull(log.getPage(-1), "Expected the page #-1 to be null.");
    }

    @Test
    public void testLogNoPagesLargeIndex() {
        Log log = new Log();
        Assert.assertNull(log.getPage(Integer.MAX_VALUE), "Expected the page #MAX_VAL to be null.");
    }

    @Test
    public void testLogNoEntries() {
        Log log = new Log();
        Assert.assertNull(log.getEntries(), "Expected the entries to be null.");
    }

    @Test
    public void testLogNoEntriesSize() {
        Log log = new Log();
        Assert.assertEquals(log.getEntriesSize(), 0, "Expected the number of entries to be 0.");
    }

    @Test
    public void testLogEntriesEntryNull() {
        Log log = new Log();
        Assert.assertNull(log.getEntry(0), "Expected the entry #0 to be null.");
    }

    @Test
    public void testLogNoEntriesNegIndex() {
        Log log = new Log();
        Assert.assertNull(log.getEntry(-1), "Expected the entry #-1 to be null.");
    }

    @Test
    public void testLogNoEntriesLargeIndex() {
        Log log = new Log();
        Assert.assertNull(log.getEntry(Integer.MAX_VALUE), "Expected the entry #MAX_VAL to be null.");
    }

}
