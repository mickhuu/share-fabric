package cn.edu.gxmzu.ai.mickhu;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @description Fbric Web 启动类
 * @author mickhu
 * @date 2022-04-07 21:18
 */
@Slf4j
@SpringBootApplication
@MapperScan(basePackages = {"cn.edu.gxmzu.ai.mickhu.mapper"})
public class FabricWebApplication {

//    @Resource(name = "masterDataSource")
    private DataSource updateDataSource;

//    @Resource(name = "usersDataSource")
//    private DataSource usersDataSource;

    public static void main(String[] args) {
        SpringApplication.run(FabricWebApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
        // update 数据源
//        log.info("[run][获得数据源：{}]", updateDataSource.getClass());

        // users 数据源
//        logger.info("[run][获得数据源：{}]", usersDataSource.getClass());

//    }

}
