import cn.hackingwu.Scaffolding;
import org.junit.Test;

/**
 * @author hackingwu.
 * @since 2015/12/18
 */
public class ScaffoldingTest {
    @Test
    public void testGenerateAll(){
        Scaffolding.generateByCommand("generate-all","cn.hackingwu.domain.TestDomain");
    }
}
