/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.commons.map.extend

import org.codehaus.groovy.runtime.InvokerHelper
import org.openehealth.ipf.commons.map.MappingService

/**
 * @author Martin Krasser
 */
class MappingExtensionHelper {


    static def methodMissingLogic = { MappingService mappingService, def normalizer, String name, args ->
        MappingExtensionHelper.simpleMethodMissingLogic(mappingService, normalizer(delegate), name, args)
    }

    static def simpleMethodMissingLogic = { MappingService mappingService, Object src, String name, args ->
        def result
        if (name.startsWith('mapReverse')) {
            def key = name.substring('mapReverse'.length()).firstLower()
            result = InvokerHelper.invokeMethod(mappingService, 'getKey', [key, src, *args])
        } else if (name.startsWith('map')) {
            def key = name.substring('map'.length()).firstLower()
            result = InvokerHelper.invokeMethod(mappingService, 'get', [key, src, *args])
        } else {
            throw new RuntimeException(new MissingMethodException(name, delegate.class, args))
        }
        result
    }
    
}
