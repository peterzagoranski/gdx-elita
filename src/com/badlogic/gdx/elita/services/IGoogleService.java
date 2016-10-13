package com.badlogic.gdx.elita.services;

public interface IGoogleService {
    void showBannerAds(final boolean value);
    void showAds();
    void trackAnalytics(String name);
    void trackLevelEnded(int levelId, int score);

    void signIn();
    void signOut();
    void rateGame();
    void submitScore(String levelId, long score);
    void showLeaderBoards();
    void showLeaderBoard(String levelId);
    void showAchievements();
    boolean isSignedIn();
    void shareMsg(String msg);
}
