<#assign objectName = className?uncap_first />
<#assign objectRepository = objectName + "Repository"/>
package ${package};

import ${package?substring(0,package?last_index_of("impl"))}${className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${package?substring(0,package?last_index_of("service"))}domain.${className};
import java.util.List;
import ${package?substring(0,package?last_index_of("service"))}repository.${className}Repository;
import org.springframework.stereotype.Service;

@Service("${objectName}Service")
public class ${className}ServiceImpl implements ${className}Service{


    @Autowired
    private ${className}Repository ${objectRepository};

    @Override
    public ${className} save(${className} ${objectName}){
        return ${objectRepository}.save(${objectName});
    }

    @Override
    public void delete(${className} ${objectName}){
        ${objectRepository}.delete(${objectName});
    }

    @Override
    public List<${className}> findAll(){
        return ${objectRepository}.findAll();
    }

    @Override
    public void delete(String id){
        ${objectRepository}.delete(id);
    }

    @Override
    public ${className} findById(String id){
        return ${objectRepository}.findOne(id);
    }
}