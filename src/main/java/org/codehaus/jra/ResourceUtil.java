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

import java.util.HashMap;
import java.util.Map;

public class ResourceUtil {

    public static int getMatchScore(String reqUri, String resource) {
        int reqIdx = 0;
        for (int i = 0; i < resource.length(); i++) {
            char c = resource.charAt(i);
            if (c == '{') {
                int next = resource.indexOf('}', i);
                if (next == -1) {
                    throw new IllegalStateException("Missing end bracket in URI " + resource);
                } else {
                    i = next + 1;
                    
                    if (i == resource.length()) {
                        // if } is the last part of the resource, assume the
                        // rest of the request uri matches
                        return reqUri.length();
                    }

                    // find the character immediately after the }
                    c = resource.charAt(i);
                    // see if we can find it
                    int endMatch = reqUri.indexOf(c, reqIdx);
                    if (endMatch == -1) {
                        // we couldn't find the character, so the expression didn't match.
                        return -1;
                    } else {
                        reqIdx = endMatch;
                        continue;
                    }
                }
            } else {
                if (reqUri.length() <= reqIdx) {
                    return -1;
                } if (c == reqUri.charAt(reqIdx)) {
                    reqIdx++;
                } else {
                    return -1;
                }
            }
        }
        return reqIdx;
    }

    public static Map<String,String> getURIParameters(String reqUri, String resource) {
        Map<String, String> params = new HashMap<String, String>();
        
        int reqIdx = 0;
        for (int i = 0; i < resource.length(); i++) {
            char c = resource.charAt(i);
            if (c == '{') {
                int begToken = reqIdx;
                int next = resource.indexOf('}', i);
                if (next == -1) {
                    throw new IllegalStateException("Missing end bracket in URI " + resource);
                } else {
                    String id = resource.substring(i+1, next);
                    i = next + 1;
                    
                    if (i == resource.length()) {
                        // if } is the last part of the resource, assume the
                        // rest of the request uri matches
                        params.put(id, reqUri.substring(begToken));
                        return params;
                    }

                    // find the character immediately after the }
                    c = resource.charAt(i);
                    // see if we can find it
                    int endMatch = reqUri.indexOf(c, reqIdx);
                    if (endMatch == -1) {
                        // we couldn't find the character, so the expression didn't match.
                        throw new IllegalStateException("Could not fully match the expression!");
                    } else {
                        params.put(id, reqUri.substring(begToken, endMatch));
                        reqIdx = endMatch + 1;
                        continue;
                    }
                }
            } else {
                if (reqUri.length() <= reqIdx) {
                    throw new IllegalStateException("Could not fully match the expression!");
                } if (c == reqUri.charAt(reqIdx)) {
                    reqIdx++;
                } else {
                    throw new IllegalStateException("Could not fully match the expression! " + reqIdx);
                }
            }
        }
        
        return params;
    }
}
