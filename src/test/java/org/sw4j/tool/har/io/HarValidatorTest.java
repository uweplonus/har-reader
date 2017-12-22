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

import java.util.List;
import org.sw4j.tool.har.model.CreatorBrowser;
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HarValidatorTest {

    private Har model;

    @BeforeMethod
    public void setUp() {
        model = new Har();
        Log log = new Log();
        model.setLog(log);
        log.setVersion("1.2");
        CreatorBrowser creator = new CreatorBrowser(CreatorBrowser.Type.CREATOR);
        log.setCreator(creator);
        creator.setName("creator");
        CreatorBrowser browser = new CreatorBrowser(CreatorBrowser.Type.BROWSER);
        log.setBrowser(browser);
        browser.setName("browser");
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

}
