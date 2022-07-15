import com.smartsoft.util.JavaGenerator;
import com.smartsoft.util.TemplateGenerator;

import static com.smart.core.ProjectConstant.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 */
public class CodeGenerator {
    //JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://1.15.183.199:3306/my_first_project";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "Pyx19961115!";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {
        genCodeByCustomModelName("user", "User");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     *
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, tableName);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     *
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName) {
        TemplateGenerator templateGenerator =
                new TemplateGenerator(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, JDBC_DIVER_CLASS_NAME);
        templateGenerator.generateJson(tableName, modelName);

        JavaGenerator javaGenerator =
                new JavaGenerator(BASE_PACKAGE, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, JDBC_DIVER_CLASS_NAME);
        javaGenerator.generate(tableName, modelName);
    }

}
