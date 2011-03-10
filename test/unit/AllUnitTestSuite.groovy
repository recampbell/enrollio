/**
 * Created by IntelliJ IDEA.
 * User: nate
 * Date: 3/1/11
 * Time: 3:00 PM
 * To change this template use File | Settings | File Templates.
 */
import junit.framework.Test

public class AllUnitTestSuite extends AllTestSuite {

  private static final String BASEDIR = "./test/unit"
  private static final String PATTERN = "**/*Tests.groovy"

  public static Test suite() {
    return suite(BASEDIR, PATTERN)
  }

}
