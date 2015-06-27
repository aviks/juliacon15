
import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Socket;
import javax.json.Json;
import javax.json.JsonObject;
import java.io.StringWriter;

public class Sim {
    
    public static void main(String[] argv) {
        ZContext ctx = new ZContext();
        Socket request = ctx.createSocket(ZMQ.REQ);
        request.connect("tcp://127.0.0.1:9999");

        JsonObject callArgs = Json
                .createObjectBuilder()
                .add("cmd", "simulate")
                .add("args",
                        Json.createArrayBuilder().add("10000000"))
                .build();

        StringWriter s = new StringWriter();
        Json.createWriter(s).write(callArgs);

        request.send(s.toString());
        
        String response = request.recvStr();
        System.out.println(response);

        ctx.close();
    }

}