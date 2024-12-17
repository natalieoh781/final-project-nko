public class LoveInterest extends Character {
    private int visuals;
    private int distress;
    private String name;
    
    public LoveInterest(int health, int strength, String background) {
        super(health, strength, background);
        this.visuals = 50;
        this.distress = 0;
    }
    
    public void cutHair() {
        if(distress > 50) {
            this.visuals += 20;
            this.setStrength(this.getStrength() + 15);
            this.distress = 0;
            System.out.println("With determination in their eyes, they cut their hair short!");
            System.out.println("Visuals increased by 20");
            System.out.println("Strength increased by 15");
            System.out.println("Distress reset to 0");
        }
    }
    
    public void cryTo(Protagonist p) {
        int loveIncrease = 25;
        p.setLoveMeter(p.getLoveMeter() + loveIncrease);
        System.out.println("Through tears, they express their feelings...");
        System.out.println("Hero's love meter increased by " + loveIncrease);
    }
    
    public int getVisuals() {
        return visuals;
    }
    
    public void setVisuals(int visuals) {
        this.visuals = visuals;
    }
    
    public int getDistress() {
        return distress;
    }
    
    public void setDistress(int distress) {
        this.distress = distress;
        if(distress > 50) {
            cutHair();
        }
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Love Interest Status:\n" +
               super.toString() + "\n" +
               "Visuals: " + visuals + "\n" +
               "Distress: " + distress;
    }
    
    public String toStringVisuals() {
        return "Love Interest Visuals: " + visuals;
    }
    
    public String toStringDistress() {
        return "Love Interest Distress: " + distress;
    }
}
