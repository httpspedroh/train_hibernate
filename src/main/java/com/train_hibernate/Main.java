package com.train_hibernate;

// ----------------------------------------------------------------------------------------------------- //

// Import apache logger
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
 
// ----------------------------------------------------------------------------------------------------- //

public class Main {

    // Create logger
    private static final Logger logger = LogManager.getLogger(Main.class);
 
    public static void main(String[] args) {

        logger.info("Hello, World!");
    }
}