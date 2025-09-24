package io.github.joaovictorbezerra.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

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
}
