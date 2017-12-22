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

import java.util.LinkedList;
import java.util.List;
import org.sw4j.tool.har.model.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HarValidatorTest {

    private Har model;

    private List<Page> pages;

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

        pages = new LinkedList<>();
        Page page1 = new Page();
        pages.add(page1);
        Page page2 = new Page();
        pages.add(page2);
    }

    @Test
    public void testHarNull() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(null);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected a single attribute to be missing.");
    }

    @Test
    public void testNoMissing() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testLogVersionMissing() {
        model.getLog().setVersion(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log", "Expected the parent to be \"log\".");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "version",
                "Expected the attribute to be \"version\".");
    }

    @Test
    public void testCreatorMissing() {
        model.getLog().setCreator(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected an attribute to be missing.");
        Assert.assertEquals(missingAttributes.get(0).getParent(), "log", "Expected the parent to be \"log\".");
        Assert.assertEquals(missingAttributes.get(0).getAttribute(), "creator",
                "Expected the attribute to be \"creator\".");
    }

    @Test
    public void testCreatorNameMissing() {
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
    public void testBrowserNameMissing() {
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
        model.getLog().getBrowser().setComment(null);

        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertTrue(missingAttributes.isEmpty(), "Expected no attribute to be missing.");
    }

    @Test
    public void testPagesMissing() {
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
        for (Page page: pages) {
            model.getLog().addPage(page);
        }

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

}
