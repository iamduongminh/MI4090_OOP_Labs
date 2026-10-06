public interface INetworkable {

    String ipAddress();

    void connect(String ipAddress);

    void disconnect();

    boolean isConnected();

}
