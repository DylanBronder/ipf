#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}

import org.apache.camel.model.ProcessorDefinition


     static extensions = {
         
         ProcessorDefinition.metaClass.reverse = {
             delegate.transmogrify { it.reverse() }
         }
         
     }
     
}