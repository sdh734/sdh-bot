package sdh.qqbot.utils.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * MybatisPlus自动生成代码工具类
 *
 * @author SDH
 */
public class mybatisPlusGenerator {
    /**
     * 数据库
     */
    public static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/qqbot?serverTimezone=GMT%2B8";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "123456";

    /**
     * 包名
     */
    public static final String PARENT_PACKAGE = "sdh.qqbot";

    /**
     * 项目路径
     */
    public static final String PROJECT_PATH = System.getProperty("user.dir");
    /**
     * controller service entity mapper输出目录
     */
    public static final String OUTPUT_DIR = PROJECT_PATH + "/src/main/java/";

    /**
     * mapperXml输出目录
     */
    public static final String MAPPER_XML_DIR = PROJECT_PATH + "/src/main/resources/mapper/";

    public static void main(String[] args) {
        //String moduleName = scanner("请输入模块名？");
        String tableNames = scanner("请输入表名，多个英文逗号分隔？所有输入 all");

        // 数据库
        DataSourceConfig.Builder datasourceBuilder = new DataSourceConfig.Builder(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        // 特殊字段（两种方式）
        Column createTime = new Column("create_time", FieldFill.INSERT);
        Property updateTime = new Property("updateTime", FieldFill.INSERT_UPDATE);
        FastAutoGenerator.create(datasourceBuilder)
                // 全局配置
                .globalConfig(builder -> builder.author("SDH").fileOverride().outputDir(OUTPUT_DIR))
                // 包配置
                .packageConfig(builder -> builder.parent(PARENT_PACKAGE).mapper("mapper").entity("entity.database").controller("controller").pathInfo(Collections.singletonMap(OutputFile.xml, MAPPER_XML_DIR)))
                // 策略配置
                .strategyConfig(builder -> builder.addInclude(getTables(tableNames)).addTablePrefix("t_")
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(createTime, updateTime)
                        .mapperBuilder().enableMapperAnnotation().enableBaseResultMap().enableBaseColumnList()
                        .build())
                /*  模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                .templateEngine(new BeetlTemplateEngine())
                .templateEngine(new FreemarkerTemplateEngine())
                */
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    // 交互输入
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(tip);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的信息！");
    }

}
