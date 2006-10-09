/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.codehaus.jra;

import java.util.Map;

import junit.framework.TestCase;

public class ResourceUtilTest extends TestCase {
    public void testGetParams() {
        Map<String, String> params = ResourceUtil.getURIParameters("/customers/123", "/customers/{id}");
        
        assertNotNull(params);
        assertEquals(1, params.size());
        assertEquals("123", params.get("id"));
        
        params = ResourceUtil.getURIParameters("/customers/123/profile", "/customers/{id}/profile");
        
        assertNotNull(params);
        assertEquals(1, params.size());
        assertEquals("123", params.get("id"));
    }
}
