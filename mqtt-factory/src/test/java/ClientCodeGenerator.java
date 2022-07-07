import com.smart.Application;
import com.smart.service.MenuService;
import com.smartsoft.util.ClientGenerator;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional()
@Rollback(false)
public class ClientCodeGenerator {

    @Resource
    private MenuService menuService;


    public static void main(String[] args) throws IOException {
        String fileName = "Order.json";
        ClientGenerator.generateVue(fileName);
    }

//    @Test
//    public void processMenu() throws IOException {
//        JSONObject jsonObject = ClientGenerator.parseJsonFile("user.json");
//        String module = (String) jsonObject.get("module");
//        String name = (String) jsonObject.get("name");
//        Menu menu = new Menu();
//        menu.setIcon("ios-stats");
//        menu.setName(name);
//        menu.setRouterName(module+"-"+name);
//        menu.setStatus((byte) 1);
//        menu.setCreatedBy(0L);
//        menu.setLastModifiedBy(0L);
//        this.menuService.save(menu);
//    }

}
