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
import org.sw4j.tool.har.model.Har;
import org.sw4j.tool.har.model.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class HarValidatorTest {

    private Har model;

    @BeforeTest
    public void setUp() {
        model = new Har();
        Log log = new Log();
        model.setLog(log);
        log.setVersion("1.2");
    }

    @Test
    public void testHarNull() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(null);
        Assert.assertEquals(missingAttributes.size(), 1, "Expected a single attribute to be missing.");
    }

    @Test
    public void testHarNoMissing() {
        List<HarValidator.RequiredAttribute> missingAttributes = HarValidator.getMissingRequiredAttributes(model);
        Assert.assertEquals(missingAttributes.size(), 0, "Expected no attribute to be missing.");
    }

}
