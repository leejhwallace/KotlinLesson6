package com.example.myapplication;

import androidx.compose.runtime.Composable;

public class Pretzel {
    private static final Pretzel[] pretzels = new Pretzel[]{
            new Pretzel("Common pretzel", "This is just a common pretzel, nothing much",R.drawable.pretzel,1f,25).setUnlocked(true),
            new Pretzel("Farmer pretzel", "Farmer's hard work allows 2x boost of current pretzel currency.",R.drawable.pretzelfarmer,2f,20),
            new Pretzel("Employment pretzel", "Morden day employment allows for a 5x boost of current pretzel currency.",R.drawable.pretzelemployment,5f,15),
            new Pretzel("King pretzel", "The king multiplies the pretzels currency by 10, cause he is rich.",R.drawable.pretzelking,10f,10),
            new Pretzel("Kodde's pretzel", "Kodee's pretzel multiplies the pretzels currency by 100, cause he is the GOAT!",R.drawable.kodeepretzel,100f,1),
    };
    private static final float MAX_UPGRADE_MULTI = 10f;
    private String name = "#Unknown Pretzel#"+System.identityHashCode(this);
    private String description = "";
    private int imageId;
    private float multi = 1.0f;
    private int weight = 1;
    private float upgradeMulti = 0f;
    private int upgradeCost = 0;
    private boolean unlocked = false;
    private static Pretzel equippedPretzel = pretzels[0];
    public Pretzel(int imageId){
        this.imageId = imageId;
    }
    public Pretzel(String name,int imageId){
        this.name = name;
        this.imageId = imageId;
    }
    public Pretzel(String name,int imageId,float multi){
        this.name = name;
        this.imageId = imageId;
        this.multi = multi;
    }

    public Pretzel(String name, String description, int imageId, float multi,int weight) {
        this.name = name;
        this.description = description;
        this.imageId = imageId;
        this.multi = multi;
        this.weight = weight;
    }
    public void upgrade(){
        if (MAX_UPGRADE_MULTI>this.upgradeMulti){
            this.upgradeMulti+=0.1f;
            setUpgradeCost(Pretzel.calculateCost(this));
        }
    }
    public static int calculateCost(Pretzel p){
        return (int) (Math.sqrt(p.upgradeMulti * p.multi * p.weight));
    }
    public static Pretzel[] getPretzels() {
        return pretzels;
    }

    public static Pretzel getEquippedPretzel() {
        return equippedPretzel;
    }

    public static void setEquippedPretzel(Pretzel equippedPretzel) {
        Pretzel.equippedPretzel = equippedPretzel;
    }

    public String getName() {
        return name;
    }

    public Pretzel setName(String name) {
        this.name = name;
        return this;
    }

    public int getImageId() {
        return imageId;
    }

    public Pretzel setImageId(int imageId) {
        this.imageId = imageId;
        return this;
    }

    public float getMulti() {
        return multi;
    }

    public Pretzel setMulti(float multi) {
        this.multi = multi;
        return this;
    }

    public float getUpgradeMulti() {
        return upgradeMulti;
    }

    public Pretzel setUpgradeMulti(float upgradeMulti) {
        this.upgradeMulti = upgradeMulti;
        return this;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public Pretzel setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
        return this;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public Pretzel setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public Pretzel setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Pretzel setDescription(String description) {
        this.description = description;
        return this;
    }
}
