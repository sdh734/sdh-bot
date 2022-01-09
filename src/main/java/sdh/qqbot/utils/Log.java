package sdh.qqbot.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sdh.qqbot.AppApplication;

public class Log {
    private static final Logger logger = LoggerFactory.getLogger(AppApplication.class);

    public static void i(String message) {
        logger.info(message);
    }

    public static void w(String message) {
        logger.warn(message);
    }

    public static void e(String message) {
        logger.error(message);
    }
}
