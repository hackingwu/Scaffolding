package ${package};
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${package?substring(0,package?last_index_of("controller"))}service.${className}Service;
import ${package?substring(0,package?last_index_of("controller"))}domain.${className};
import java.util.HashMap;
import java.util.Map;

<#assign objectName = className?uncap_first />

@RestController
@RequestMapping("/{objectName}")
public class ${className}Controller{

    @Autowired
    ${className}Service ${objectName}Service;

    /**
    * add
    */
    @RequestMapping(method=RequestMethod.POST)
    public ${className} create(@RequestBody ${className} ${objectName}){
        return ${objectName}Service.save(${objectName});
    }

    /**
    * delete
    */
    @RequestMapping(value="/{r"{id}"}",method=RequestMethod.DELETE)
    public void delete(@PathVariable String id){
        ${objectName}Service.delete(id);
    }

    /**
    * update
    */
    @RequestMapping(value="/{r"{id}"}",method=RequestMethod.PUT)
    public ${className} update(@PathVariable String id,@RequestBody ${className} ${objectName}){
        //${className} old${className} = ${objectName}Service.findById(id);
        ${objectName}.setId(id);
        return ${objectName}Service.save(${objectName});
    }

    /**
    * query all
    */
    @RequestMapping(method=RequestMethod.GET)
    public Map<String,Object> list(){
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("items",${objectName}Service.findAll());
        return data;
    }

    /**
    * query one
    */
    @RequestMapping(value="/{r"{id}"}",method=RequestMethod.GET)
    public ${className} get(@PathVariable String id){
        return ${objectName}Service.findById(id);
    }
}