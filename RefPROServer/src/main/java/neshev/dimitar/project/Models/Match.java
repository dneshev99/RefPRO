package neshev.dimitar.project.Models;

import org.springframework.data.annotation.Id;

public class Match {
    @Id
    String ID;

    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;
    private Referee referee;
    private Log matchLog;

    public Match(String homeTeamName, String awayTeamName, int homeTeamScore, int awayTeamScore, Referee referee, Log matchLog) {
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.referee = referee;
        this.matchLog = matchLog;
    }

    public Match () {}

    public String getID() {
        return ID;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public Referee getReferee() {
        return referee;
    }

    public Log getMatchLog() {
        return matchLog;
    }
}
