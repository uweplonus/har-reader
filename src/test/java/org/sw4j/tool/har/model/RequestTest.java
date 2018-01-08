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
        request.clearCookies();
        Assert.assertNull(request.getCookies(), "Expected the cookies to be null.");
    }

    @Test
    public void testRequestNoCookiesSize() {
        Request request = new Request();
        request.clearCookies();
        Assert.assertEquals(request.getCookiesSize(), 0, "Expected the number of cookies to be 0.");
    }

    @Test
    public void testRequestNoCookiesCookieNull() {
        Request request = new Request();
        request.clearCookies();
        Assert.assertNull(request.getCookie(0), "Expected the cookie #0 to be null.");
    }

    @Test
    public void testRequestNoCookiesNegIndex() {
        Request request = new Request();
        request.clearCookies();
        Assert.assertNull(request.getCookie(-1), "Expected the cookie #-1 to be null.");
    }

    @Test
    public void testRequestNoCookiesLargeIndex() {
        Request request = new Request();
        request.clearCookies();
        Assert.assertNull(request.getCookie(Integer.MAX_VALUE), "Expected the cookie #MAX_VAL to be null.");
    }

    @Test
    public void testRequestEmptyCookies() {
        Request request = new Request();
        request.createEmptyCookies();
        Assert.assertNotNull(request.getCookies(), "Expected the cookies not to be null.");
    }

    @Test
    public void testRequestEmptyCookiesSize() {
        Request request = new Request();
        request.createEmptyCookies();
        Assert.assertEquals(request.getCookiesSize(), 0, "Expected the number of cookies to be 0.");
    }

    @Test
    public void testRequestEmptyCookiesCookieNull() {
        Request request = new Request();
        request.createEmptyCookies();
        Assert.assertNull(request.getCookie(0), "Expected the cookie #0 to be null.");
    }

    @Test
    public void testRequestEmptyCookiesNegIndex() {
        Request request = new Request();
        request.createEmptyCookies();
        Assert.assertNull(request.getCookie(-1), "Expected the cookie #-1 to be null.");
    }

    @Test
    public void testRequestEmptyCookiesLargeIndex() {
        Request request = new Request();
        request.createEmptyCookies();
        Assert.assertNull(request.getCookie(Integer.MAX_VALUE), "Expected the cookie #MAX_VAL to be null.");
    }

    @Test
    public void testRequestCookies() {
        Request request = new Request();
        Cookie cookie = new Cookie();
        request.addCookie(cookie);
        Assert.assertNotNull(request.getCookies(), "Expected the cookies not to be null.");
    }

    @Test
    public void testRequestCookiesSize() {
        Request request = new Request();
        Cookie cookie = new Cookie();
        request.addCookie(cookie);
        Assert.assertEquals(request.getCookiesSize(), 1, "Expected the number of cookies to be 1.");
    }

    @Test
    public void testRequestCookiesCookieNotNull() {
        Request request = new Request();
        Cookie cookie = new Cookie();
        request.addCookie(cookie);
        Assert.assertNotNull(request.getCookie(0), "Expected the cookie #0 not to be null.");
    }

    @Test
    public void testRequestCookies2ndCookieNotNull() {
        Request request = new Request();
        Cookie cookie = new Cookie();
        request.addCookie(cookie);
        cookie = new Cookie();
        request.addCookie(cookie);
        Assert.assertSame(request.getCookie(1), cookie, "Expected the cookie #1 to be the same as set.");
    }

    @Test
    public void testRequestCookiesNegIndex() {
        Request request = new Request();
        Cookie cookie = new Cookie();
        request.addCookie(cookie);
        Assert.assertNull(request.getCookie(-1), "Expected the cookie #-1 to be null.");
    }

    @Test
    public void testRequestCookiesLargeIndex() {
        Request request = new Request();
        Cookie cookie = new Cookie();
        request.addCookie(cookie);
        Assert.assertNull(request.getCookie(Integer.MAX_VALUE), "Expected the cookie #MAX_VAL to be null.");
    }

    @Test
    public void testRequestNoHeaders() {
        Request request = new Request();
        request.clearHeaders();
        Assert.assertNull(request.getHeaders(), "Expected the headers to be null.");
    }

    @Test
    public void testRequestNoHeadersSize() {
        Request request = new Request();
        request.clearHeaders();
        Assert.assertEquals(request.getHeadersSize(), 0, "Expected the number of headers to be 0.");
    }

    @Test
    public void testRequestNoHeadersHeaderNull() {
        Request request = new Request();
        request.clearHeaders();
        Assert.assertNull(request.getHeader(0), "Expected the header #0 to be null.");
    }

    @Test
    public void testRequestNoHeadersNegIndex() {
        Request request = new Request();
        request.clearHeaders();
        Assert.assertNull(request.getHeader(-1), "Expected the header #-1 to be null.");
    }

    @Test
    public void testRequestNoHeadersLargeIndex() {
        Request request = new Request();
        request.clearHeaders();
        Assert.assertNull(request.getHeader(Integer.MAX_VALUE), "Expected the header #MAX_VAL to be null.");
    }

    @Test
    public void testRequestEmptyHeaders() {
        Request request = new Request();
        request.createEmptyHeaders();
        Assert.assertNotNull(request.getHeaders(), "Expected the headers not to be null.");
    }

    @Test
    public void testRequestEmptyHeadersSize() {
        Request request = new Request();
        request.createEmptyHeaders();
        Assert.assertEquals(request.getHeadersSize(), 0, "Expected the number of headers to be 0.");
    }

    @Test
    public void testRequestEmptyHeadersHeaderNull() {
        Request request = new Request();
        request.createEmptyHeaders();
        Assert.assertNull(request.getHeader(0), "Expected the header #0 to be null.");
    }

    @Test
    public void testRequestEmptyHeadersNegIndex() {
        Request request = new Request();
        request.createEmptyHeaders();
        Assert.assertNull(request.getHeader(-1), "Expected the header #-1 to be null.");
    }

    @Test
    public void testRequestEmptyHeadersLargeIndex() {
        Request request = new Request();
        request.createEmptyHeaders();
        Assert.assertNull(request.getHeader(Integer.MAX_VALUE), "Expected the header #MAX_VAL to be null.");
    }

    @Test
    public void testRequestHeaders() {
        Request request = new Request();
        Header header = new Header();
        request.addHeader(header);
        Assert.assertNotNull(request.getHeaders(), "Expected the headers not to be null.");
    }

    @Test
    public void testRequestHeadersSize() {
        Request request = new Request();
        Header header = new Header();
        request.addHeader(header);
        Assert.assertEquals(request.getHeadersSize(), 1, "Expected the number of headers to be 1.");
    }

    @Test
    public void testRequestHeadersHeaderNotNull() {
        Request request = new Request();
        Header header = new Header();
        request.addHeader(header);
        Assert.assertNotNull(request.getHeader(0), "Expected the header #0 not to be null.");
    }

    @Test
    public void testRequestHeaders2ndHeaderNotNull() {
        Request request = new Request();
        Header header = new Header();
        request.addHeader(header);
        header = new Header();
        request.addHeader(header);
        Assert.assertSame(request.getHeader(1), header, "Expected the header #1 to be the same as set.");
    }

    @Test
    public void testRequestHeadersNegIndex() {
        Request request = new Request();
        Header header = new Header();
        request.addHeader(header);
        Assert.assertNull(request.getHeader(-1), "Expected the header #-1 to be null.");
    }

    @Test
    public void testRequestHeadersLargeIndex() {
        Request request = new Request();
        Header header = new Header();
        request.addHeader(header);
        Assert.assertNull(request.getHeader(Integer.MAX_VALUE), "Expected the header #MAX_VAL to be null.");
    }

}
