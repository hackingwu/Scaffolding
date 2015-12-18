<#assign objectName = className?uncap_first />
package ${package};

import ${package?substring(0,package?last_index_of("service"))}domain.${className};
import java.util.List;

public interface ${className}Service{

    ${className} save(${className} ${objectName});

    void delete(${className} ${objectName});

    List<${className}> findAll();

    void delete(String id);

    ${className} findById(String id);
}