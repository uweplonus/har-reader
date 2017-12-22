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
            "      \"version\": \"2.2\"\n" +
            "    },\n" +
            "    \"browser\": {\n" +
            "      \"name\": \"HAR Test\",\n" +
            "      \"version\": \"2.3\"\n" +
            "    }\n" +
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
            Assert.assertEquals(ex.getObject(), "", "Expected the object with the missing attribute to be \"\"");
            Assert.assertEquals(ex.getAttribute(), "log", "Expected the missing attribute to be \"log\".");
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
        Assert.assertEquals("1.2", log.getVersion(), "Expected the version to be \"1.2\".");
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
        Assert.assertEquals("HAR Test", log.getCreator().getName(),
                "Expected the creator.name to be \"HAR Test\".");
    }

    @Test
    public void testReadLogCreatorVersion() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(requiredJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getCreator().getVersion(), "Expected a nonnull log.creator.version object.");
        Assert.assertEquals("2.2", log.getCreator().getVersion(),
                "Expected the creator.version to be \"2.2\".");
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
        Assert.assertEquals("HAR Test", log.getBrowser().getName(),
                "Expected the browser.name to be \"HAR Test\".");
    }

    @Test
    public void testReadLogBrowserVersion() throws AttributeRequiredException {
        HarReader hr = new HarReader(new StringReader(optionalJson));
        Log log = hr.read(true);
        Assert.assertNotNull(log.getBrowser().getVersion(), "Expected a nonnull log.browser.version object.");
        Assert.assertEquals("2.3", log.getBrowser().getVersion(),
                "Expected the browser.version to be \"2.3\".");
    }

}
