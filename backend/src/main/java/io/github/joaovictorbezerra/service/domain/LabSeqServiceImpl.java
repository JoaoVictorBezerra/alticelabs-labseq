package io.github.joaovictorbezerra.service.domain;

import io.github.joaovictorbezerra.constants.api.ApiConstants;
import io.github.joaovictorbezerra.exception.exceptions.NonPositiveTermException;
import io.github.joaovictorbezerra.service.LabSeqService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.jboss.logging.Logger;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@ApplicationScoped
@Named(ApiConstants.LABSEQ_SERVICE_IMPL)
public class LabSeqServiceImpl implements LabSeqService {

    @Inject
    Logger log;

    private final ConcurrentMap<Integer, BigInteger> cache = new ConcurrentHashMap<>();

    public LabSeqServiceImpl() {
        cache.put(0, BigInteger.ZERO);
        cache.put(1, BigInteger.ONE);
        cache.put(2, BigInteger.ZERO);
        cache.put(3, BigInteger.ONE);
    }

    public BigInteger computeLabSeq(int termNumber) {
        if (termNumber < 0)
            throw new NonPositiveTermException("Invalid term number: " + termNumber);

        var cachedResult = cache.get(termNumber);
        if (cachedResult != null) {
            log.infof("Returned value %d from cache. Position %d", cache.get(termNumber), termNumber);
            return cachedResult;
        }

        BigInteger result = this.computeWithCaching(termNumber);
        log.infof("Computed new value %d for position %d", result, termNumber);

        return result;
    }

    private BigInteger computeWithCaching(int termNumber) {
        BigInteger
                a = BigInteger.ZERO,
                b = BigInteger.ONE,
                c = BigInteger.ZERO,
                d = BigInteger.ONE,
                result = BigInteger.ZERO;

        for (int i = 4; i <= termNumber; i++) {
            result = a.add(b);
            a = b;
            b = c;
            c = d;
            d = result;
        }

        BigInteger finalResult = result;
        cache.computeIfAbsent(termNumber, k -> finalResult);
        return result;
    }
}