/*
 * Copyright 2017 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.openehealth.ipf.commons.audit.event;

import org.openehealth.ipf.commons.audit.AuditException;
import org.openehealth.ipf.commons.audit.codes.ActiveParticipantRoleIdCode;
import org.openehealth.ipf.commons.audit.codes.EventActionCode;
import org.openehealth.ipf.commons.audit.codes.EventIdCode;
import org.openehealth.ipf.commons.audit.codes.EventOutcomeIndicator;
import org.openehealth.ipf.commons.audit.codes.ParticipantObjectIdTypeCode;
import org.openehealth.ipf.commons.audit.types.EventType;
import org.openehealth.ipf.commons.audit.types.PurposeOfUse;

import java.util.Collections;

/**
 * Builds an Audit Event representing a Begin Transferring DICOM Instances event as specified in
 * http://dicom.nema.org/medical/dicom/current/output/html/part15.html#sect_A.5.3.3
 * <p>
 * This message describes the event of a system beginning to transfer a set of DICOM instances
 * from one node to another node within control of the system's security domain.
 * This message may only include information about a single patient.
 * </p>
 *
 * @author Christian Ohr
 * @since 3.5
 */
public class BeginTransferringDicomInstancesBuilder extends BaseAuditMessageBuilder<BeginTransferringDicomInstancesBuilder> {

    public BeginTransferringDicomInstancesBuilder(
            EventOutcomeIndicator outcome,
            String eventOutcomeDescription,
            EventType eventType,
            PurposeOfUse... purposesOfUse) {
        super();
        setEventIdentification(outcome,
                eventOutcomeDescription,
                EventActionCode.Execute,
                EventIdCode.BeginTransferringDICOMInstances,
                eventType,
                purposesOfUse
        );
    }


    /**
     * @param userId          The identity of the process sending the data
     * @param altUserId       Alternate UserID
     * @param userName        UserName
     * @param networkId       Network Access Point ID
     * @param userIsRequestor Whether the destination participant represents the requestor (i.e. pull request)
     * @return this
     */
    public BeginTransferringDicomInstancesBuilder setSendingProcessParticipant(String userId,
                                                                               String altUserId,
                                                                               String userName,
                                                                               String networkId,
                                                                               boolean userIsRequestor) {
        return addSourceActiveParticipant(userId, altUserId, userName, networkId, userIsRequestor);
    }

    /**
     * @param userId          The identity of the process receiving the data
     * @param altUserId       Alternate UserID
     * @param userName        UserName
     * @param networkId       Network Access Point ID
     * @param userIsRequestor Whether the destination participant represents the requestor (i.e. pull request)
     * @return this
     */
    public BeginTransferringDicomInstancesBuilder setReceivingProcessParticipant(String userId,
                                                                                 String altUserId,
                                                                                 String userName,
                                                                                 String networkId,
                                                                                 boolean userIsRequestor) {
        return addDestinationActiveParticipant(userId, altUserId, userName, networkId, userIsRequestor);
    }

    /**
     * @param patientId   patient ID
     * @param patientName patient name
     * @return this
     */
    public BeginTransferringDicomInstancesBuilder setPatientParticipantObject(String patientId, String patientName) {
        if (patientId != null) {
            addPatientParticipantObject(patientId, patientName, Collections.emptyList(), null);
        }
        return self();
    }

    /**
     * @param studyId study ID
     * @param uids    one or more SOP Class UID values
     * @return this
     */
    public BeginTransferringDicomInstancesBuilder addTransferredStudyParticipantObject(String studyId, String uids) {
        return addStudyParticipantObject(
                studyId,
                Collections.singletonList(getTypeValuePair("ContainsSOPClass", uids)));
    }

    @Override
    public void validate() {
        super.validate();
        if (getMessage().findActiveParticipants(ap -> ap.getRoleIDCodes().contains(ActiveParticipantRoleIdCode.Source)).size() != 1) {
            throw new AuditException("Must have one ActiveParticipant with RoleIDCode Source");
        }
        if (getMessage().findActiveParticipants(ap -> ap.getRoleIDCodes().contains(ActiveParticipantRoleIdCode.Destination)).size() != 1) {
            throw new AuditException("Must have one ActiveParticipant with RoleIDCode Destination");
        }
        if (getMessage().findParticipantObjectIdentifications(poi -> poi.getParticipantObjectIDTypeCode() == ParticipantObjectIdTypeCode.PatientNumber).size() != 1) {
            throw new AuditException("Must have one ParticipantObjectIdentification with ParticipantObjectIDTypeCode PatientNumber");
        }
        if (getMessage().findParticipantObjectIdentifications(poi -> poi.getParticipantObjectIDTypeCode() == ParticipantObjectIdTypeCode.StudyInstanceUID).isEmpty()) {
            throw new AuditException("Must have one or more ParticipantObjectIdentification with ParticipantObjectIDTypeCode StudyInstanceUID");
        }
    }
}
