package net.gotev.sipservice;

/**
 * connect
 * <p>
 *
 *
 */
public enum SipAccountTransport {
    UDP,
    TCP,
    TLS;

    public static SipAccountTransport getTransportByCode(int code) {
        switch (code) {
            case 0:
            default: return UDP;
            case 1: return TCP;
            case 2: return TLS;
        }
    }
}
