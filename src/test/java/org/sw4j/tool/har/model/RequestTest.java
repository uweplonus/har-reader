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

public class RequestTest {

    @Test
    public void testRequestNoCookies() {
        Request request = new Request();
        Assert.assertNull(request.getCookies(), "Expected the cookies to be null.");
    }

    @Test
    public void testRequestNoCookiesSize() {
        Request request = new Request();
        Assert.assertEquals(request.getCookiesSize(), 0, "Expected the number of cookies to be 0.");
    }

    @Test
    public void testRequestCookiesCookieNull() {
        Request request = new Request();
        Assert.assertNull(request.getCookie(0), "Expected the cookie #0 to be null.");
    }

    @Test
    public void testRequestNoCookiesNegIndex() {
        Request request = new Request();
        Assert.assertNull(request.getCookie(-1), "Expected the cookie #-1 to be null.");
    }

    @Test
    public void testRequestNoCookiesLargeIndex() {
        Request request = new Request();
        Assert.assertNull(request.getCookie(Integer.MAX_VALUE), "Expected the cookie #MAX_VAL to be null.");
    }

}
