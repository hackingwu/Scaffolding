package ${package};
import org.springframework.data.mongodb.repository.MongoRepository;
import ${package?substring(0,package?last_index_of("repository"))}domain.${className};



public interface ${className}Repository extends MongoRepository<${className},String> {

}