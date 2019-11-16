package com.sunny.codegenrator;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Scanner;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: fang
 * @Date: 2019/11/6
 */
public class CodeGenrator {
    /**
     * 读取控制台内容
     */
    public static String scanner(String input) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("请输入：" + input + ",");
        System.out.println(stringBuilder.toString());
        if (scanner.hasNext()) {
            String str = scanner.next();
            if (StringUtils.isNotEmpty(str)) {
                return str;
            }
        }
        throw new MybatisPlusException("请输入正确的" + input + "!");
    }

    public static void main(String[] args) {

        //代码生成器
        AutoGenerator generator = new AutoGenerator();
        //全局配置文件
        GlobalConfig config = new GlobalConfig();
        System.getProperty("user.dir");
        //获取当前工程路径（user,dir）
        String pojectPath = System.getProperty("user.dir");
        config.setOutputDir(pojectPath + "/src/main/java");
        //config.setOutputDir("D:\\JAVA\\IntelljIdea-workspace\\layuicms\\src\\main\\java");
        config.setAuthor("sunny");
        config.setOpen(false);
        config.setServiceName("%sService");
        generator.setGlobalConfig(config);

        /*配置数据源*/
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/cms?useUnicode=true&characterEncoding=utf8&useSSL=true&serverTimezone=UTC");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        generator.setDataSource(dsc);

        /*包配置 entity mapper servier/impl controller */
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.sunny.layuicms");
        pc.setEntity("entity");
        pc.setController("controller");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper.xml");
        generator.setPackageInfo(pc);

        /*策略配置*/
        StrategyConfig sc = new StrategyConfig();
        //表名和字段名是否驼峰命名
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        //设置要继承的父类
        //Controller公共父类
//        sc.setSuperControllerClass("com.sunny.layuicms");
        //实体父类的公共字段
//        sc.setSuperEntityColumns("user_id", "username");
        //开启lombok和RestController
        sc.setEntityLombokModel(true);
        sc.setRestControllerStyle(true);
        //要设置哪些表，以逗号分隔
        sc.setInclude(scanner("表名，以逗号分隔表名：").split(","));
        sc.setControllerMappingHyphenStyle(true);
        sc.setTablePrefix(pc.getModuleName() + "_");
//        sc.setTablePrefix("sys_");
        generator.setStrategy(sc);
        //代码生成器执行
        generator.execute();
    }
}
