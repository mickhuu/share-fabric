package cn.edu.gxmzu.ai.mickhu.grpc;

import com.bolingcavalry.grpctutorials.lib.HelloReply;
import com.bolingcavalry.grpctutorials.lib.SimpleGrpc;
import com.bolingcavalry.grpctutorials.lib.HelloRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author mickhu
 * @describe
 * @date 2022-04-09 18:25
 */
@Slf4j
public class GrpcClient {

    public void start(String host, String port, String message) {
        ManagedChannelBuilder<?> channelBuilder = ManagedChannelBuilder.forTarget(host + ":" + port);
        channelBuilder.usePlaintext();
        //channelBuilder.defaultLoadBalancingPolicy("random") //设置
        //channelBuilder.intercept(new MyClientInterceptor());
        //CompressorRegistry compressorRegistry = CompressorRegistry.getDefaultInstance();
        //compressorRegistry.register(null);
        //channelBuilder.compressorRegistry(compressorRegistry);
        //channelBuilder.decompressorRegistry()
        //channelBuilder.disableRetry();
        //channelBuilder.idleTimeout(10,TimeUnit.MINUTES);
        //channelBuilder.keepAliveTimeout()

        ManagedChannel channel = channelBuilder.build();
//        HelloRequest request = HelloRequest.newBuilder()
//                .setGreeting("this is a client request")
//                .build();
//
//        HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(channel);
//        HelloResponse helloResponse = helloServiceBlockingStub.sayHello(request);
//        System.out.println(helloResponse.getReply());

        SimpleGrpc.SimpleBlockingStub simpleStub = SimpleGrpc.newBlockingStub(channel);
        final HelloReply response = simpleStub.sayHello(HelloRequest.newBuilder().setName(message).build());

        System.out.println(response.getMessage());

        try {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("send message error");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        GrpcClient gRpcClient = new GrpcClient();

        gRpcClient.start("localhost","50051", "this is a client request");
    }
}