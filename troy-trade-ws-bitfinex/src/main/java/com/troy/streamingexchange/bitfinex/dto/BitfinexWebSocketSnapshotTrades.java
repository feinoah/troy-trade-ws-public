package com.troy.streamingexchange.bitfinex.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.troy.streamingexchange.bitfinex.dto.marketdata.BitfinexTrade;

import java.util.ArrayList;
import java.util.List;

@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class BitfinexWebSocketSnapshotTrades extends BitfinexWebSocketTradesTransaction {
    public BitfinexWebSocketTrade[] trades;

    public BitfinexWebSocketSnapshotTrades() {
    }

    public BitfinexWebSocketSnapshotTrades(String channelId, BitfinexWebSocketTrade[] trades) {
        super(channelId);
        this.trades = trades;
    }

    public BitfinexWebSocketTrade[] getTrades() {
        return trades;
    }

    public BitfinexTrade[] toBitfinexTrades() {
        List<BitfinexTrade> bitfinexTrades = new ArrayList<>(getTrades().length);
        for (BitfinexWebSocketTrade websocketTrade : trades) {
            bitfinexTrades.add(websocketTrade.toBitfinexTrade());
        }

        return bitfinexTrades.toArray(new BitfinexTrade[bitfinexTrades.size()]);
    }
}
