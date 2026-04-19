package com.example.DTO;

public class MarketShare {
    private String region;
    private String marketShare;
    private String growth;

    public MarketShare(String region, String marketShare, String growth) {
        this.region = region;
        this.marketShare = marketShare;
        this.growth = growth;
    }

    public String getRegion() { return region; }
    public String getMarketShare() { return marketShare; }
    public String getGrowth() { return growth; }

    public void setRegion(String region) { this.region = region; }
    public void setMarketShare(String marketShare) { this.marketShare = marketShare; }
    public void setGrowth(String growth) { this.growth = growth; }
}