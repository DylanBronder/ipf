/*
 * Copyright 2010 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.mllp;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.openehealth.ipf.commons.ihe.hl7v2.MessageAdapterValidator;
import org.openehealth.ipf.gazelle.validation.profile.ItiPixPdqProfile;
import org.openehealth.ipf.gazelle.validation.profile.PixPdqTransactions;
import org.openehealth.ipf.modules.hl7dsl.MessageAdapter;
import org.openehealth.ipf.platform.camel.hl7.validation.ConformanceProfileValidators;
import org.openehealth.ipf.platform.camel.ihe.hl7v2.Hl7v2MarshalUtils;
import org.openehealth.ipf.platform.camel.ihe.mllp.iti10.Iti10Component;
import org.openehealth.ipf.platform.camel.ihe.mllp.iti21.Iti21Component;
import org.openehealth.ipf.platform.camel.ihe.mllp.iti22.Iti22Component;
import org.openehealth.ipf.platform.camel.ihe.mllp.iti64.Iti64Component;
import org.openehealth.ipf.platform.camel.ihe.mllp.iti8.Iti8Component;
import org.openehealth.ipf.platform.camel.ihe.mllp.iti9.Iti9Component;

import ca.uhn.hl7v2.parser.Parser;

import static org.openehealth.ipf.platform.camel.core.adapter.ValidatorAdapter.validationEnabled;

/**
 * Validating processors for MLLP-based IPF IHE components.
 *
 * @author Dmytro Rud
 */
abstract public class PixPdqCamelValidators {
    public static final MessageAdapterValidator VALIDATOR = new MessageAdapterValidator();


    /**
     * Returns a validating processor for ITI-8 request messages
     * (Patient Identity Feed).
     */
    public static Processor iti8RequestValidator() {
        return ConformanceProfileValidators.validatingProcessor(PixPdqTransactions.ITI8);
    }

    /**
     * Returns a validating processor for ITI-8 response messages
     * (Patient Identity Feed).
     */
    public static Processor iti8ResponseValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_8_ACK);
    }

    /**
     * Returns a validating processor for ITI-9 request messages.
     * (Patient Identity Query).
     */
    public static Processor iti9RequestValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_9_QBP_Q23);
    }

    /**
     * Returns a validating processor for ITI-9 response messages
     * (Patient Identity Query).
     */
    public static Processor iti9ResponseValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_9_RSP_K23);
    }

    /**
     * Returns a validating processor for ITI-10 request messages
     * (PIX Update Notification).
     */
    public static Processor iti10RequestValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_10_ADT_A31);
    }

    /**
     * Returns a validating processor for ITI-10 response messages
     * (PIX Update Notification).
     */
    public static Processor iti10ResponseValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_10_ACK);
    }

    /**
     * Returns a validating processor for ITI-21 request messages
     * (Patient Demographics Query).
     */
    public static Processor iti21RequestValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_21_QBP_Q22);
    }

    /**
     * Returns a validating processor for ITI-21 response messages
     * (Patient Demographics Query).
     */
    public static Processor iti21ResponseValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_21_RSP_K22);
    }

    /**
     * Returns a validating processor for ITI-22 request messages
     * (Patient Demographics and Visit Query).
     */
    public static Processor iti22RequestValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_22_QBP_ZV1);
    }

    /**
     * Returns a validating processor for ITI-22 response messages
     * (Patient Demographics and Visit Query).
     */
    public static Processor iti22ResponseValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_22_RSP_ZV2);
    }

    /**
     * Returns a validating processor for ITI-64 request messages
     * (XAD-PID Change Management).
     */
    public static Processor iti64RequestValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_64_ADT_A43);
    }

    /**
     * Returns a validating processor for ITI-64 response messages
     * (XAD-PID Change Management).
     */
    public static Processor iti64ResponseValidator() {
        return ConformanceProfileValidators.validatingProcessor(ItiPixPdqProfile.ITI_64_ACK_A43);
    }


    /**
     * Returns a validating processor for ITI messages that validates as specified
     * in the Validation Rules in the message's HapiContext
     */
    public static Processor validatingProcessor() {
        return ConformanceProfileValidators.validatingProcessor();
    }




}
