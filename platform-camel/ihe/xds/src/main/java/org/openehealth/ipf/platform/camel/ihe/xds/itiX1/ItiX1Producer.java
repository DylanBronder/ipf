/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.ihe.xds.itiX1;

import org.openehealth.ipf.commons.core.DomBuildersThreadLocal;
import org.openehealth.ipf.commons.ihe.ws.JaxWsClientFactory;
import org.openehealth.ipf.commons.ihe.ws.WsTransactionConfiguration;
import org.openehealth.ipf.commons.ihe.xds.core.audit.XdsSubmitAuditDataset;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.lcm.SubmitObjectsRequest;
import org.openehealth.ipf.commons.ihe.xds.core.stub.ebrs30.rs.RegistryResponseType;
import org.openehealth.ipf.commons.ihe.xds.itiX1.ItiX1PortType;
import org.openehealth.ipf.platform.camel.ihe.ws.AbstractWsEndpoint;
import org.openehealth.ipf.platform.camel.ihe.xds.XdsSubmissionProducer;

/**
 * @author Dmytro Rud
 */
public class ItiX1Producer extends XdsSubmissionProducer<SubmitObjectsRequest, RegistryResponseType> {

    private static final DomBuildersThreadLocal DOM_BUILDERS = new DomBuildersThreadLocal();

    public ItiX1Producer(AbstractWsEndpoint<XdsSubmitAuditDataset, WsTransactionConfiguration> endpoint, JaxWsClientFactory<XdsSubmitAuditDataset> clientFactory) {
        super(endpoint, clientFactory, SubmitObjectsRequest.class, RegistryResponseType.class);
    }

    @Override
    protected RegistryResponseType callService(Object client, SubmitObjectsRequest request) throws Exception {
        injectTargetHomeCommunityId(client, request);
        return ((ItiX1PortType) client).documentRegistryUpdateDocumentSet(request);
    }

}