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

public class AttributeRequiredExceptionTest {

    @Test
    public void testCreationWithParent() {
        AttributeRequiredException requiredAttribute = new AttributeRequiredException("test", null);
        Assert.assertEquals(requiredAttribute.getObject(), "test", "Expected the parent to be the same as set.");
    }

    @Test
    public void testCreationWithAttribute() {
        AttributeRequiredException requiredAttribute = new AttributeRequiredException(null,"test");
        Assert.assertEquals(requiredAttribute.getAttribute(), "test", "Expected the attribute to be the same as set.");
    }

    @Test
    public void testEqualsWithSame() {
        AttributeRequiredException requiredAttribute = new AttributeRequiredException("test", null);
        Assert.assertTrue(requiredAttribute.equals(requiredAttribute), "Expected the object to be equals to itself.");
    }

    @Test
    public void testEqualsWithObject() {
        AttributeRequiredException requiredAttribute = new AttributeRequiredException("test", null);
        Assert.assertFalse(requiredAttribute.equals(new Object()),
                "Expected the object not to be equals to an object.");
    }

    @Test
    public void testEqualsWithDifferentParent() {
        AttributeRequiredException ra1 = new AttributeRequiredException("parent1", "attribute");
        AttributeRequiredException ra2 = new AttributeRequiredException("parent2", "attribute");
        Assert.assertFalse(ra1.equals(ra2),
                "Expected the object not to be equals to another object with different parent.");
    }

    @Test
    public void testEqualsWithDifferentAttribute() {
        AttributeRequiredException ra1 = new AttributeRequiredException("parent", "attribute1");
        AttributeRequiredException ra2 = new AttributeRequiredException("parent", "attribute2");
        Assert.assertFalse(ra1.equals(ra2),
                "Expected the object not to be equals to another object with different attribute.");
    }

    @Test
    public void testEquals() {
        AttributeRequiredException ra1 = new AttributeRequiredException("parent", "attribute");
        AttributeRequiredException ra2 = new AttributeRequiredException("parent", "attribute");
        Assert.assertTrue(ra1.equals(ra2),
                "Expected the object to be equals to another object.");
    }

    @Test
    public void testEqualsToNull() {
        AttributeRequiredException ra1 = new AttributeRequiredException("parent", "attribute");
        Assert.assertFalse(ra1.equals(null),
                "Expected the object not to be equals to null.");
    }

}
