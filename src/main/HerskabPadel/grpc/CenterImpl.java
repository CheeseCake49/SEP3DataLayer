package main.HerskabPadel.grpc;

import main.HerskabPadel.services.CenterServiceImpl;

@GRpcService public class CenterImpl extends CenterGRPC.CenterImplBase {

    private final CenterServiceImpl centerService;
}
