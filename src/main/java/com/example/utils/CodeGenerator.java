package com.example.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Scanner;

public class CodeGenerator {
    public static void main(String[] args) {
        //代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //获取当前Java应用程序的工作目录（项目路径）
        String projectPath = System.getProperty("user.dir");
        //设置生成路径
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        //是否覆盖以前文件
        globalConfig.setFileOverride(true);
        //是否打开生成目录
        globalConfig.setOpen(false);
        //设置项目作者名称
        globalConfig.setAuthor("djd");
        //设置主键策略
        globalConfig.setIdType(IdType.AUTO);
        //生成基本ResultMap
        globalConfig.setBaseResultMap(true);
        //生成基本ColumnList
        globalConfig.setBaseColumnList(true);
        //格式化Service
        globalConfig.setServiceName("%sService");
        //设置时间类型
        globalConfig.setDateType(DateType.ONLY_DATE);

        autoGenerator.setGlobalConfig(globalConfig);

        //数据库配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/springboot?useUnicode=true&characterEncoding=utf-8&allowMultiQueries=true&useSSL=false");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345678");
        autoGenerator.setDataSource(dsc);


        //生成包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.example");
        pc.setMapper("mapper");
        pc.setXml("mapper.xml");
        pc.setEntity("entity");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        autoGenerator.setPackageInfo(pc);

        //配置代码生成策略，指定要生成代码的表、表名、字段名等信息
        StrategyConfig sc = new StrategyConfig();
        //名策略设置为下划线转驼峰命名
        sc.setNaming(NamingStrategy.underline_to_camel);
        sc.setColumnNaming(NamingStrategy.underline_to_camel);
        //自动lombok
        sc.setEntityLombokModel(true);
        //生成带有 @RestController 注解的 Controller 类
        sc.setRestControllerStyle(true);

        //控制器类的映射路径使用连字符风格，即将驼峰命名法转换为连字符分隔的路径形式
        sc.setControllerMappingHyphenStyle(true);
        // 设置生成的 Mapper 接口上是否添加 @Mapper 注解
//        sc.setMapperAnnotation(true);
        //设置逻辑删除
        sc.setLogicDeleteFieldName("deleted");
        //设置自动填充配置
        //字段名为 "create_time"，表示要填充的字段名为 create_time。
        //FieldFill.INSERT 表示该字段在插入数据时进行填充。
        //这意味着在执行插入操作时，会自动填充 create_time 字段的值，通常是当前时间。
        TableFill gmt_create = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmt_modified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmt_create);
        tableFills.add(gmt_modified);
        sc.setTableFillList(tableFills);

        //乐观锁
        sc.setVersionFieldName("version");
        sc.setRestControllerStyle(true);//驼峰命名


        //  sc.setTablePrefix("tbl_"); 设置表名前缀
        sc.setInclude(scanner("表名，多个英文逗号分割").split(","));
        autoGenerator.setStrategy(sc);

        // 生成代码
        autoGenerator.execute();


    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        String help = "请输入" + tip + "：";
        System.out.println(help);
        String ipt = scanner.next();
        if (StringUtils.isNotBlank(ipt)) {
            return ipt;
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
