package org.openehealth.ipf.platform.camel.hl7.reifier;

import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.spi.RouteContext;
import org.openehealth.ipf.platform.camel.core.adapter.ProcessorAdapter;
import org.openehealth.ipf.platform.camel.core.reifier.ProcessorAdapterReifier;
import org.openehealth.ipf.platform.camel.hl7.model.HapiAdapterDefinition;

/**
 * @author Christian Ohr
 */
public class HapiAdapterReifier extends ProcessorAdapterReifier<HapiAdapterDefinition> {

    public HapiAdapterReifier(RouteContext routeContext, ProcessorDefinition<?> definition) {
        super(routeContext, (HapiAdapterDefinition) definition);
    }

    @Override
    protected ProcessorAdapter doCreateProcessor() {
        return definition.getAdapter();
    }
}
