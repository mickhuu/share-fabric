package cn.edu.gxmzu.ai.mickhu.grpc.test;

import cn.edu.gxmzu.ai.mickhu.annotation.GrpcService;
import io.grpc.BindableService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;
/**
 * @author mickhu
 * @describe
 * @date 2022-04-14 20:17
 */
//@Component
public class ComponentBean {
//public class ComponentBean implements ApplicationContextAware, SmartInitializingSingleton, DisposableBean {

//    private ApplicationContext applicationContext;
//
//    @Bean
//    public GrpcServer gRpcServer() {
//        return new GrpcServer();
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext context) throws BeansException {
//        this.applicationContext = context;
//    }
//
//    @Override
//    public void afterSingletonsInstantiated() {
//        Map<String, Object> grpcServices = applicationContext.getBeansWithAnnotation(GrpcService.class);
//        ArrayList<BindableService> services = new ArrayList<>();
//        for (Map.Entry<String, Object> entry : grpcServices.entrySet()) {
//            if (!(entry.getValue() instanceof BindableService)) {
//                throw new IllegalStateException("The bean named " + entry.getKey()
//                        + " is marked with the @GrpcService , must implement the " + BindableService.class.getName());
//            }
//            services.add((BindableService) entry.getValue());
//        }
//        GrpcServer gRpcServer = applicationContext.getBean(GrpcServer.class);
//        gRpcServer.start(services, 9090);
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        GrpcServer gRpcServer = applicationContext.getBean(GrpcServer.class);
//        gRpcServer.stop();
//    }

}
