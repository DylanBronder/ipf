/*
 * Copyright 2013 the original author or authors.
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
package org.openehealth.ipf.commons.ihe.xds.core.transform.requests;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.Before;
import org.junit.Test;
import org.openehealth.ipf.commons.ihe.xds.core.SampleData;
import org.openehealth.ipf.commons.ihe.xds.core.ebxml.*;
import org.openehealth.ipf.commons.ihe.xds.core.requests.RemoveMetadata;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.SubmitObjectsRequest;
import org.openehealth.ipf.commons.ihe.xds.core.validate.requests.RemoveMetadataRequestValidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.openehealth.ipf.commons.ihe.xds.XDS.Interactions.ITI_62;

/**
 * Tests for {@link RemoveMetadataRequestTransformer}.
 * @author Boris Stanojevic
 */
public class RemoveMetadataTransformerTest {
    private RemoveMetadataRequestTransformer transformer;
    private RemoveMetadata request;

    
    @Before
    public void setUp() {
        transformer = new RemoveMetadataRequestTransformer();
        request = SampleData.createRemoveMetadata();
    }

    @Test
    public void testToEbXML() {
        EbXMLRemoveMetadataRequest ebXML = transformer.toEbXML(request);
        assertNotNull(ebXML);
        assertEquals(2, ebXML.getReferences().size());
        assertEquals("1.2.3", ebXML.getReferences().get(0).getHome());
        assertEquals("5.6.7", ebXML.getReferences().get(1).getHome());
        assertNull(ebXML.getHome());
        assertNull(ebXML.getId());
    }

    @Test
    public void testToEbXMLNull() {
        assertNull(transformer.toEbXML(null));
    }
    
    @Test
    public void testToEbXMLEmpty() {
        EbXMLRemoveMetadataRequest result = transformer.toEbXML(new RemoveMetadata());
        assertNotNull(result);
        assertNotNull(result.getReferences());
        assertEquals(0, result.getReferences().size());
    }
    
    @Test
    public void testFromEbXML() {
        EbXMLRemoveMetadataRequest ebXML = transformer.toEbXML(request);
        RemoveMetadata result = transformer.fromEbXML(ebXML);
        
        assertEquals(request.toString(), result.toString());
    }
    
    @Test
    public void testFromEbXMLNull() {
        assertNull(transformer.toEbXML(null));
    }
    
    @Test
    public void testFromEbXMLEmpty() {
        EbXMLRemoveMetadataRequest ebXML = transformer.toEbXML(new RemoveMetadata());
        assertEquals(new RemoveMetadata(), transformer.fromEbXML(ebXML));
    }
    
    @Test
    public void verifyEbXmlSerialization() throws JAXBException {
        var ebXML = transformer.toEbXML(SampleData.createRemoveMetadata());
        JAXBContext jaxbContext = JAXBContext.newInstance(SubmitObjectsRequest.class);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter writer = new StringWriter();
        marshaller.marshal(ebXML.getInternal(), writer);
        new RemoveMetadataRequestValidator().validate(ebXML, ITI_62);
        assertFalse("AdhocQuery is not expected in ITI-62 request", writer.toString().contains("AdhocQuery"));
    }
}
