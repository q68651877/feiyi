
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import tk.mybatis.mapper.generator.MapperPlugin;

import java.util.Arrays;

public class CustomizedMapperPlugin extends MapperPlugin {

    private static String[] fileds_no_need_notnull_check = new String[]{"created_by", "created_date", "last_modified_by", "last_modified_date", "version"};

    public CustomizedMapperPlugin() {
    }

    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {

        if (!introspectedColumn.isNullable() && !introspectedColumn.isAutoIncrement() && this.needNotNullCheck(introspectedColumn)) {         //非主键增加
            topLevelClass.addImportedType("javax.validation.constraints.NotNull");
            field.addAnnotation("@NotNull");
        }
        if (introspectedColumn.isStringColumn()) {
            topLevelClass.addImportedType("javax.validation.constraints.Size");
            field.addAnnotation("@Size(min = 0, max = " + introspectedColumn.getLength() + " , message = \"长度必须在{min}和{max}之间\")");
        }
        return super.modelFieldGenerated(field, topLevelClass, introspectedColumn,
                introspectedTable, modelClassType);
    }

    private boolean needNotNullCheck(IntrospectedColumn introspectedColumn) {
        System.out.println(introspectedColumn.getActualColumnName());
        if (Arrays.asList(this.fileds_no_need_notnull_check).contains(introspectedColumn.getActualColumnName()))
            return false;
        return true;
    }
}
