public class Rival extends Character {
    private int jealousy;
    private int respect;
    
    public Rival(int health, int strength, String background) {
        super(health, strength, background);
        this.jealousy = 0;
        this.respect = 0;
    }
    
    public void challenge(Protagonist p) {
        if(this.getStrength() < p.getStrength()) {
            System.out.println("The rival challenges the hero but is soundly defeated!");
            int respectGain = 10;
            this.respect += respectGain;
            this.jealousy -= respectGain;
            System.out.println("The rival's respect grows by " + respectGain + "and their jealousy decreases by " + respectGain);
        } else {
            System.out.println("The rival emerges victorious in the challenge!");
            p.setHealth(p.getHealth() - 15);
            System.out.println("Hero's health decreased by 15");
        }
    }
    
    public void sabotage(Protagonist p) {
        int reputationLoss = 20;
        p.setReputation(p.getReputation() - reputationLoss);
        System.out.println("The rival spreads vicious rumors about the hero!");
        System.out.println("Hero's reputation decreased by " + reputationLoss);
    }
    
    public int getJealousy() {
        return jealousy;
    }
    
    public void setJealousy(int jealousy) {
        this.jealousy = jealousy;
    }
    
    public int getRespect() {
        return respect;
    }
    
    public void setRespect(int respect) {
        this.respect = respect;
    }
    
    @Override
    public String toString() {
        return "Rival Status:\n" +
               super.toString() + "\n" +
               "Jealousy: " + jealousy + "\n" +
               "Respect: " + respect;
    }
    
    public String toStringJealousy() {
        return "Rival Jealousy: " + jealousy;
    }
    
    public String toStringRespect() {
        return "Rival Respect: " + respect;
    }
}
