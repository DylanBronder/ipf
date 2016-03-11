/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.commons.ihe.fhir.iti78;

import org.openehealth.ipf.commons.ihe.core.atna.AuditorManager;
import org.openehealth.ipf.commons.ihe.fhir.FhirQueryAuditDataset;

/**
 * Strategy for auditing ITI-78 transactions on the client side
 *
 * @author Christian Ohr
 * @since 3.1
 */
public class Iti78ClientAuditStrategy extends Iti78AuditStrategy {

    public Iti78ClientAuditStrategy() {
        super(false);
    }

    @Override
    public void doAudit(FhirQueryAuditDataset auditDataset) {
        AuditorManager.getFhirAuditor().auditIti78(
                false,
                auditDataset.getEventOutcomeCode(),
                auditDataset.getServiceEndpointUrl(),
                auditDataset.getClientIpAddress(),
                auditDataset.getQueryString());
    }

}
