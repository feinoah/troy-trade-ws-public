package com.troy.trade.ws.dto;

import com.troy.commons.utils.Assert;
import com.troy.commons.utils.DateUtils;
import com.troy.trade.ws.dto.currency.CurrencyPair;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Ticker implements Serializable {
    private final CurrencyPair currencyPair;
    private final BigDecimal open;
    private final BigDecimal last;
    private final BigDecimal bid;
    private final BigDecimal ask;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal vwap;
    private final BigDecimal volume;
    private final BigDecimal quoteVolume;
    /** the timestamp of the ticker according to the exchange's server, null if not provided */
    private final Date timestamp;

    private final BigDecimal bidSize;
    private final BigDecimal askSize;



    /**
     * Constructor
     *
     * @param currencyPair The tradable identifier (e.g. BTC in BTC/USD)
     * @param last Last price
     * @param bid Bid price
     * @param ask Ask price
     * @param high High price
     * @param low Low price
     * @param vwap Volume Weighted Average Price
     * @param volume 24h volume in base currency
     * @param quoteVolume 24h volume in counter currency
     * @param timestamp - the timestamp of the ticker according to the exchange's server, null if not
     *     provided
     * @param bidSize The instantaneous size at the bid price
     * @param askSize The instantaneous size at the ask price
     */
    private Ticker(
            CurrencyPair currencyPair,
            BigDecimal open,
            BigDecimal last,
            BigDecimal bid,
            BigDecimal ask,
            BigDecimal high,
            BigDecimal low,
            BigDecimal vwap,
            BigDecimal volume,
            BigDecimal quoteVolume,
            Date timestamp,
            BigDecimal bidSize,
            BigDecimal askSize) {
        this.open = open;
        this.currencyPair = currencyPair;
        this.last = last;
        this.bid = bid;
        this.ask = ask;
        this.high = high;
        this.low = low;
        this.vwap = vwap;
        this.volume = volume;
        this.quoteVolume = quoteVolume;
        this.timestamp = timestamp;
        this.bidSize = bidSize;
        this.askSize = askSize;
    }

    public CurrencyPair getCurrencyPair() {

        return currencyPair;
    }

    public BigDecimal getOpen() {

        return open;
    }

    public BigDecimal getLast() {

        return last;
    }

    public BigDecimal getBid() {

        return bid;
    }

    public BigDecimal getAsk() {

        return ask;
    }

    public BigDecimal getHigh() {

        return high;
    }

    public BigDecimal getLow() {

        return low;
    }

    public BigDecimal getVwap() {

        return vwap;
    }

    public BigDecimal getVolume() {

        return volume;
    }

    public BigDecimal getQuoteVolume() {
        if (quoteVolume == null && volume != null && last != null) {
            return volume.multiply(last);
        }
        return quoteVolume;
    }

    public Date getTimestamp() {

        return timestamp;
    }

    public BigDecimal getBidSize() {
        return bidSize;
    }

    public BigDecimal getAskSize() {
        return askSize;
    }

    @Override
    public String toString() {

        return "Ticker [currencyPair="
                + currencyPair
                + ", open="
                + open
                + ", last="
                + last
                + ", bid="
                + bid
                + ", ask="
                + ask
                + ", high="
                + high
                + ", low="
                + low
                + ",avg="
                + vwap
                + ", volume="
                + volume
                + ", quoteVolume="
                + quoteVolume
                + ", timestamp="
                + DateUtils.toMillisNullSafe(timestamp)
                + ", bidSize="
                + bidSize
                + ", askSize="
                + askSize
                + "]";
    }

    /**
     * Builder to provide the following to {@link Ticker}:
     *
     * <ul>
     *   <li>Provision of fluent chained construction interface
     * </ul>
     */
    public static class Builder {

        private CurrencyPair currencyPair;
        private BigDecimal open;
        private BigDecimal last;
        private BigDecimal bid;
        private BigDecimal ask;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal vwap;
        private BigDecimal volume;
        private BigDecimal quoteVolume;
        private Date timestamp;
        private BigDecimal bidSize;
        private BigDecimal askSize;

        // Prevent repeat builds
        private boolean isBuilt = false;

        public Ticker build() {

            validateState();

            Ticker ticker =
                    new Ticker(
                            currencyPair,
                            open,
                            last,
                            bid,
                            ask,
                            high,
                            low,
                            vwap,
                            volume,
                            quoteVolume,
                            timestamp,
                            bidSize,
                            askSize);

            isBuilt = true;

            return ticker;
        }

        private void validateState() {

            if (isBuilt) {
                throw new IllegalStateException("The entity has been built");
            }
        }

        public Ticker.Builder currencyPair(CurrencyPair currencyPair) {
            Assert.notNull(currencyPair, "Null currencyPair");
            this.currencyPair = currencyPair;
            return this;
        }

        public Ticker.Builder open(BigDecimal open) {

            this.open = open;
            return this;
        }

        public Ticker.Builder last(BigDecimal last) {

            this.last = last;
            return this;
        }

        public Ticker.Builder bid(BigDecimal bid) {

            this.bid = bid;
            return this;
        }

        public Ticker.Builder ask(BigDecimal ask) {

            this.ask = ask;
            return this;
        }

        public Ticker.Builder high(BigDecimal high) {

            this.high = high;
            return this;
        }

        public Ticker.Builder low(BigDecimal low) {

            this.low = low;
            return this;
        }

        public Ticker.Builder vwap(BigDecimal vwap) {

            this.vwap = vwap;
            return this;
        }

        public Ticker.Builder volume(BigDecimal volume) {

            this.volume = volume;
            return this;
        }

        public Ticker.Builder quoteVolume(BigDecimal quoteVolume) {

            this.quoteVolume = quoteVolume;
            return this;
        }

        public Ticker.Builder timestamp(Date timestamp) {

            this.timestamp = timestamp;
            return this;
        }

        public Ticker.Builder bidSize(BigDecimal bidSize) {
            this.bidSize = bidSize;
            return this;
        }

        public Ticker.Builder askSize(BigDecimal askSize) {
            this.askSize = askSize;
            return this;
        }
    }
}
