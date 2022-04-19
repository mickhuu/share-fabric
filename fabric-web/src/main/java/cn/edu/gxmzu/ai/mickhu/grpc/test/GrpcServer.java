package cn.edu.gxmzu.ai.mickhu.grpc.test;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.util.MutableHandlerRegistry;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-12 22:51
 */
@Slf4j
public class GrpcServer {

    private Server server;

    public void start(List<BindableService> services, int port) {
        // MyGrpcServerInterceptor myGrpcServerInterceptor = new MyGrpcServerInterceptor();

        MutableHandlerRegistry mutableHandlerRegistry = new MutableHandlerRegistry();
        for (BindableService service : services) {
            // 用于添加拦截器
            // ServerServiceDefinition interceptedService = ServerInterceptors.intercept(service, myGrpcServerInterceptor);
            mutableHandlerRegistry.addService(service);
            log.info("Add grpc service impl [{}]", service.getClass());
        }

        try {
            server = ServerBuilder.forPort(port)
                    .fallbackHandlerRegistry(mutableHandlerRegistry)
                    //.addService(new HelloServiceImpl())
                    .build()
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("GRpc Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.out.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                GrpcServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.out.println("*** server shut down");
        }));
    }

    public Server getServer() {
        return server;
    }

    public void awaitTermination() {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }
}
