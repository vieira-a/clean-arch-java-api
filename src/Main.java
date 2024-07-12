import com.arch.presentation.module.user.UserModule;
import com.arch.presentation.router.user.UserRouter;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) {
        int serverPort = 8080;

        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(serverPort), 0);

            UserModule userModule = new UserModule();
            UserRouter userRouter = new UserRouter(server, userModule);
            userRouter.configureRoutes();

            server.start();
            System.out.println("Server started on port " + serverPort);
        } catch (IOException e) {
            System.err.println("Failed to start server: " + e.getMessage());
        }
    }
}
