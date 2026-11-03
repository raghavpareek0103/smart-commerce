package com.raghav.ecommerce.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OtpUtilsTest {

    @Test
    @DisplayName("OTP hamesha 6 digit ka aur sirf numbers ka hona chahiye")
    void generateOTP_isSixDigitsNumeric() {
        for (int i = 0; i < 200; i++) {
            String otp = OtpUtils.generateOTP();
            assertThat(otp).hasSize(6).matches("\\d{6}");
        }
    }

    @Test
    @DisplayName("Har baar alag OTP banna chahiye (random)")
    void generateOTP_isRandom() {
        Set<String> otps = new HashSet<>();
        for (int i = 0; i < 50; i++) {
            otps.add(OtpUtils.generateOTP());
        }
        // 50 me se kam se kam 40 alag hone chahiye
        assertThat(otps.size()).isGreaterThan(40);
    }
}