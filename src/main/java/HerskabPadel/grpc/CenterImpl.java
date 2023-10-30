package HerskabPadel.grpc;

import HerskabPadel.services.CenterServiceImpl;

@GRpcService public class CenterImpl extends CenterGRPC.CenterImplBase {

    private final CenterServiceImpl centerService;
}
