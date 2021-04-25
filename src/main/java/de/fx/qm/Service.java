package de.fx.qm;

public class Service {
    private Helper helper = new Helper();

    public String makeMeStars(int int1, int int2) {
        String stars = "";
        for (int i = 0; i < helper.add(int1, int2); i++) {
            stars += "*";
        }
        return stars;
    }
}
