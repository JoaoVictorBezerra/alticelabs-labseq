package io.github.joaovictorbezerra.service.domain;

import io.github.joaovictorbezerra.constants.api.ApiConstants;
import io.github.joaovictorbezerra.service.LabSeqService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jboss.logging.Logger;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Named(ApiConstants.LABSEQ_SERVICE_IMPL)
public class LabSeqServiceImpl implements LabSeqService {

    @Inject
    Logger log;

    private final Map<Integer, BigInteger> cache = new HashMap<>();

    private final Map<Integer, BigInteger> baseValues = Map.of(
            0, BigInteger.ONE,
            1, BigInteger.ONE,
            2, BigInteger.ZERO,
            3, BigInteger.ZERO
    );


    public BigInteger computeLabSeq(int termNumber) {
        BigInteger a = BigInteger.ZERO, b = BigInteger.ONE, c = BigInteger.ZERO, d = BigInteger.ONE;
        BigInteger result = BigInteger.ZERO;

        if (cache.containsKey(termNumber)) {
            log.infof("Returned value %d from cache. Position %d", cache.get(termNumber), termNumber);
            return cache.get(termNumber);
        }

        if (termNumber <= 3) return baseValues.get(termNumber);

        for (int i = 4; i <= termNumber; i++) {
            result = a.add(b);

            a = b;
            b = c;
            c = d;
            d = result;
        }

        cache.put(termNumber, result);

        return result;
    }
}
