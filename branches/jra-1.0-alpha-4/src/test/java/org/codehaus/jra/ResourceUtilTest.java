/**
 * Copyright 2006 Envoi Solutions LLC
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
    
    public void testScores() {
        int m1 = ResourceUtil.getMatchScore("/customer/123", "/customer/{123}");
        int m2 = ResourceUtil.getMatchScore("/customer/details/123", "/customer/details/{123}");
        int m3 = ResourceUtil.getMatchScore("/customer/details/123", "/customer/{123}");
        
        assertEquals(9, m1);
        assertEquals(17, m2);
        assertEquals(9, m3);
    }
}