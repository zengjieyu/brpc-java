package com.baidu.brpc.example.nshead;

import com.baidu.brpc.client.BrpcProxy;
import com.baidu.brpc.client.RpcClient;
import com.baidu.brpc.client.RpcClientOptions;
import com.baidu.brpc.client.loadbalance.LoadBalanceType;
import com.baidu.brpc.example.standard.Echo;
import com.baidu.brpc.example.standard.Echo.EchoResponse;
import com.baidu.brpc.example.standard.EchoService;
import com.baidu.brpc.protocol.Options.ProtocolType;

public class RpcClientTest {

    public static void main(String[] args) {

        RpcClientOptions clientOption = new RpcClientOptions();
        clientOption.setProtocolType(ProtocolType.PROTOCOL_NSHEAD_PROTOBUF_VALUE);
        clientOption.setWriteTimeoutMillis(1000);
        clientOption.setReadTimeoutMillis(5000);
        clientOption.setLoadBalanceType(LoadBalanceType.WEIGHT.getId());
        clientOption.setEncoding("gbk");

        // 高端口，在开发机上测试
        String serviceUrl = "list://localhost:8080";

        RpcClient rpcClient = new RpcClient(serviceUrl, clientOption);

        // sync call
        EchoService echoService = BrpcProxy.getProxy(rpcClient, EchoService.class);

        Echo.EchoRequest request = Echo.EchoRequest.newBuilder().setMessage("hello world").build();

        EchoResponse response = echoService.echo(request);


        System.out.println("--------nshead protobuf sync call response-----------------");
        System.out.println(response.getMessage());
        rpcClient.stop();


    }
}
