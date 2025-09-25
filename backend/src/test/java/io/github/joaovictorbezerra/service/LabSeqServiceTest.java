package io.github.joaovictorbezerra.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;

@QuarkusTest
public class LabSeqServiceTest {
    @Inject
    LabSeqService labSeqService;

    @Test
    @DisplayName("Should compute labseq correctly")
    public void computeLabSeq() {
        var result = labSeqService.computeLabSeq(10);
        Assertions.assertEquals(BigInteger.valueOf(3), result);
    }

    @Test
    @DisplayName("Should display non-positive term exception")
    public void throwExceptionIfNonPositiveTerm() {
        int negativeTerm = -5;
        Assertions.assertThrows(RuntimeException.class,
                () -> labSeqService.computeLabSeq(negativeTerm)
        );
    }

    @Test
    @DisplayName("Should calculate last value faster than previous value")
    public void testPerformanceWithCaching() {
        labSeqService.computeLabSeq(50);
        labSeqService.computeLabSeq(51);

        var start1 = System.nanoTime();
        labSeqService.computeLabSeq(1000);
        var time1 = System.nanoTime() - start1;

        var start2 = System.nanoTime();
        labSeqService.computeLabSeq(1001);
        var time2 = System.nanoTime() - start2;

        System.out.println("First value " + time1 + " ms");
        System.out.println("Last value " + time2 + " ms");

        Assertions.assertTrue(time1 > time2, "Cached call should be faster. First value " + time1 + " ms" + " Last value " + time2);

    }

    @Test
    @DisplayName("Should compute value less than 10 seconds")
    public void testPerformanceWithLargeInput() {
        int TERM_SIZE_FOR_PERFORMANCE_TESTING = 100000;
        assertTimeout(Duration.ofSeconds(10),
                () -> labSeqService.computeLabSeq(TERM_SIZE_FOR_PERFORMANCE_TESTING));

    }
}
