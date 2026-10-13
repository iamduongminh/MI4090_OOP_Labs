// 20237362
// Dương Quang Minh
public interface INetworkable {

    String getIpAddress();

    void connect(String ipAddress);

    void disconnect();

    boolean isConnected();

}
