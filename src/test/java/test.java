import com.fxmms.www.domain.Admin;
import com.fxmms.www.service.AdminService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mark on 16/11/14.
 */

public class test {
   /* public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:service-applicationContext.xml");
        MacQo macQo = (MacQo) ctx.getBean("macQo");
        macQo.getClass();

    }*/

    @Test
    public void aa() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:service-applicationContext.xml");
        AdminService adminService = ctx.getBean("adminService",AdminService.class);
        Admin admin = new Admin();
        admin.setUserName("gangge");
        adminService.insertAdmin(admin);
    }


}
