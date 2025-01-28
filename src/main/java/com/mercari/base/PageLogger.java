package com.mercari.base;

import java.util.logging.Logger;

public class PageLogger {
        private static Logger logger = Logger.getLogger(PageLogger.class.getName());

        public static void log(String message) {
            logger.info(message);
        }
    }

