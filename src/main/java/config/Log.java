package config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author 0x安权
 * 2024/11/20 11:18
 */


// 日志
public class Log{


    private static final Logger logger = LogManager.getLogger(Log.class);

    public static void logInfo(String message) {
        logger.info(message);
    }

    public static void logWarning(String message) {
        logger.warn(message);
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

}
