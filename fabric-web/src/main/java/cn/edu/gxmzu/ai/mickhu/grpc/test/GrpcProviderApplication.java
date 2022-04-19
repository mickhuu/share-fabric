package cn.edu.gxmzu.ai.mickhu.grpc.test;

import cn.edu.gxmzu.ai.mickhu.annotation.GrpcService;
import io.grpc.BindableService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-12 22:59
 */
@SpringBootApplication
public class GrpcProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(GrpcProviderApplication.class, args);
//        GrpcServer gRpcServer = context.getBean(GrpcServer.class);
//        gRpcServer.awaitTermination();
    }
}
