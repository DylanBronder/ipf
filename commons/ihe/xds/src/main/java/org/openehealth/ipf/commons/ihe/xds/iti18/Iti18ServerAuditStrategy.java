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
package org.openehealth.ipf.commons.ihe.xds.iti18;

import org.openehealth.ipf.commons.ihe.core.atna.AuditorManager;
import org.openehealth.ipf.commons.ihe.xds.core.audit.XdsQueryAuditDataset;
import org.openehealth.ipf.commons.ihe.xds.core.audit.XdsQueryAuditStrategy30;

/**
 * Server audit strategy for ITI-18.
 * @author Dmytro Rud
 */
public class Iti18ServerAuditStrategy extends XdsQueryAuditStrategy30 {

    public Iti18ServerAuditStrategy() {
        super(true);
    }

    @Override
    public void doAudit(XdsQueryAuditDataset auditDataset) {
        AuditorManager.getRegistryAuditor().auditRegistryStoredQueryEvent(
                auditDataset.getEventOutcomeCode(),
                auditDataset.getUserId(), 
                auditDataset.getUserName(),
                auditDataset.getClientIpAddress(), 
                auditDataset.getServiceEndpointUrl(),
                auditDataset.getQueryUuid(), 
                auditDataset.getRequestPayload(),
                auditDataset.getHomeCommunityId(),
                auditDataset.getPatientId(),
                auditDataset.getPurposesOfUse());
    }

}
