package sep3datalayer.grpc;

import net.devh.boot.grpc.server.service.GrpcService;
import sep3datalayer.grpc.protobuf.CenterServiceGrpc;
import sep3datalayer.services.CenterServiceImpl;

@GrpcService public class CenterImpl extends CenterServiceGrpc.CenterServiceImplBase {

    private final CenterServiceImpl centerService;

    public CenterImpl(CenterServiceImpl centerService) {
        this.centerService = centerService;
    }

}
