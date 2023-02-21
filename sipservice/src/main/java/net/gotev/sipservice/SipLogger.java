package net.gotev.sipservice;

import org.pjsip.pjsua2.LogEntry;
import org.pjsip.pjsua2.LogWriter;
import org.pjsip.pjsua2.pj_log_decoration;

/**
 * connect
 * <p>
 *
 *
 */
public class SipLogger extends LogWriter {
    public void write(LogEntry entry) {
        Logger.debug("PJSIP "+entry.getThreadName(), entry.getMsg());
    }

    /**
     * Change decor flags as needed
     * @return decor flags
     */
    public long getDecor() {
        return pj_log_decoration.PJ_LOG_HAS_CR
                | pj_log_decoration.PJ_LOG_HAS_NEWLINE
                | pj_log_decoration.PJ_LOG_HAS_COLOR
                | pj_log_decoration.PJ_LOG_HAS_INDENT
                | pj_log_decoration.PJ_LOG_HAS_LEVEL_TEXT
                | pj_log_decoration.PJ_LOG_HAS_TIME
                | pj_log_decoration.PJ_LOG_HAS_SENDER
                | pj_log_decoration.PJ_LOG_HAS_SPACE;
    }
}
