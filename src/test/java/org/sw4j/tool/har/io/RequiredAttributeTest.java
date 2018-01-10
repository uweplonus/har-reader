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

import org.testng.Assert;
import org.testng.annotations.Test;

public class RequiredAttributeTest {

    @Test
    public void testCreationWithParent() {
        HarValidator.RequiredAttribute requiredAttribute = new HarValidator.RequiredAttribute("test", null);
        Assert.assertEquals(requiredAttribute.getParent(), "test", "Expected the parent to be the same as set.");
    }

    @Test
    public void testCreationWithAttribute() {
        HarValidator.RequiredAttribute requiredAttribute = new HarValidator.RequiredAttribute(null,"test");
        Assert.assertEquals(requiredAttribute.getAttribute(), "test", "Expected the attribute to be the same as set.");
    }

    @Test
    public void testEqualsWithSame() {
        HarValidator.RequiredAttribute requiredAttribute = new HarValidator.RequiredAttribute("test", null);
        Assert.assertTrue(requiredAttribute.equals(requiredAttribute), "Expected the object to be equals to itself.");
    }

    @Test
    public void testEqualsWithObject() {
        HarValidator.RequiredAttribute requiredAttribute = new HarValidator.RequiredAttribute("test", null);
        Assert.assertFalse(requiredAttribute.equals(new Object()),
                "Expected the object not to be equals to an object.");
    }

    @Test
    public void testEqualsWithDifferentParent() {
        HarValidator.RequiredAttribute ra1 = new HarValidator.RequiredAttribute("parent1", "attribute");
        HarValidator.RequiredAttribute ra2 = new HarValidator.RequiredAttribute("parent2", "attribute");
        Assert.assertFalse(ra1.equals(ra2),
                "Expected the object not to be equals to another object with different parent.");
    }

    @Test
    public void testEqualsWithDifferentAttribute() {
        HarValidator.RequiredAttribute ra1 = new HarValidator.RequiredAttribute("parent", "attribute1");
        HarValidator.RequiredAttribute ra2 = new HarValidator.RequiredAttribute("parent", "attribute2");
        Assert.assertFalse(ra1.equals(ra2),
                "Expected the object not to be equals to another object with different attribute.");
    }

    @Test
    public void testEquals() {
        HarValidator.RequiredAttribute ra1 = new HarValidator.RequiredAttribute("parent", "attribute");
        HarValidator.RequiredAttribute ra2 = new HarValidator.RequiredAttribute("parent", "attribute");
        Assert.assertTrue(ra1.equals(ra2),
                "Expected the object to be equals to another object.");
    }

    @Test
    public void testEqualsToNull() {
        HarValidator.RequiredAttribute ra1 = new HarValidator.RequiredAttribute("parent", "attribute");
        Assert.assertFalse(ra1.equals(null),
                "Expected the object not to be equals to null.");
    }

    @Test
    public void testToString() {
        HarValidator.RequiredAttribute requiredAttribute = new HarValidator.RequiredAttribute("parent", "attribute");
        Assert.assertEquals(requiredAttribute.toString(), "RequiredAttribute{parent='parent', attribute='attribute'}",
                "Expected the generated string to include classname and properties.");
    }

}
