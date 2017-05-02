import com.tianzeng.react.enums.SourcePermissions;
import com.tianzeng.react.moudel.Permission;
import com.tianzeng.react.moudel.Source;
import com.tianzeng.react.utils.JsonTreeHelper;
import org.junit.Test;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianzeng on 17-4-27.
 */
public class JsonTreeHelperTest {
    @Test
        public void test() throws SecurityException,
                NoSuchMethodException, IllegalArgumentException,
                IllegalAccessException, InvocationTargetException {
        Source s1 = new Source();
        s1.setId(1L);
        s1.setDescription("用户");
        s1.setName("/api/users");
        Permission p1 = new Permission();
        p1.setPermissions(SourcePermissions.CREATE);
        p1.setDescription("用户创建");

        TestClass t1 = new TestClass(0,s1 ,null);
        TestClass t2 = new TestClass(1, p1,"0" );
        List<Object> list = new ArrayList<Object>() ;
        list.add(t1) ;
        list.add(t2) ;

        try {
            System.out.println("jsonTree:" +
                    JsonTreeHelper.getTreeJsonStrNormal(new String[]{"id","name","pro"},list)
            );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
    }
}
