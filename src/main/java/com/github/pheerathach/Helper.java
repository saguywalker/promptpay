package com.github.pheerathach;

import java.math.BigDecimal;

class Helper {

    private Helper() {

    }

    // Special Thanks to thedayofcondor @ https://stackoverflow.com/questions/13209364/convert-c-crc16-to-java-crc16/13209435
    protected static int crc16(final byte[] buffer) {
        int crc = 0xFFFF;
        for (int j = 0; j < buffer.length; j++) {
            crc = ((crc >>> 8) | (crc << 8)) & 0xffff;
            crc ^= (buffer[j] & 0xff);
            crc ^= ((crc & 0xff) >> 4);
            crc ^= (crc << 12) & 0xffff;
            crc ^= ((crc & 0xFF) << 5) & 0xffff;
        }
        crc &= 0xffff;
        return crc;
    }

    protected static void validateLength(String name, String data, int maxLength) {
        if (data.length() > maxLength) {
            throw new IllegalStateException(name + " must not be more than " + maxLength + " char(s).");
        }
    }

    protected static void validateNumeric(String name, String data) {
        if (!data.matches("[0-9]+")) {
            throw new IllegalStateException(name + " must contain only numbers.");
        }
    }

    protected static void validateAlphanumeric(String name, String data) {
        if (!data.matches("[A-Za-z0-9]+")) {
            throw new IllegalStateException(name + " must contain only numbers and English characters.");
        }
    }

    protected static void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Amount must be a positive number.");
        }
    }
}
