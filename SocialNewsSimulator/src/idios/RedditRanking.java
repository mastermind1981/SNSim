package idios;


public class RedditRanking extends VoteBasedRanking implements RankingStrategy {

    protected double itemRank(Item item) {
        int up = ups.get(item);
        int down = downs.get(item);
        return Math.log10(Math.max(Math.abs(up - down), 1)) + Math.signum(up - down) * item.timestamp / 45000.0;
    }

}
