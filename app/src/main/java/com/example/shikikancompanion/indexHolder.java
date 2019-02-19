package com.example.shikikancompanion;

import android.util.Log;

public class indexHolder {
    private int id;
    private String name;
    private String picture;
    private String type;
    private int rarity;

    //extra
    private String time;
    private String obtain;
    private int hp;
    private int firepower;
    private int evasion;
    private int accuracy;
    private int rof;
    private int clip;
    private int armor;
    private int speed;
    private int ap;
    private int critical;
    private String tile;
    private String skill;
    private String skilld;
    private int skillic;
    private int skillcd;
    private String skillUrl;
    private String tiled;
    private String intro;
    private String pictureUrl;
    private String  pictureDUrl;


    public indexHolder(){}

    public indexHolder(int id, String name, String picture, String type, int rarity){
        this.id = id;
        this.name = name;
        this.picture = picture;
        this.type = type;
        this.rarity = rarity;
    }

    public indexHolder(int id, String name, int rarity, String type, String time, String obtain, int hp, int firepower, int evasion, int accuracy, int rof, int clip, int armor, int speed, int ap, int critical, String skill, String skilld, int skillic, int skillcd, String skillUrl, String tile, String tiled, String intro, String pictureUrl, String pictureDUrl){
        this.id = id;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.type = type;
        this.rarity = rarity;
        this.time = time;
        this.obtain = obtain;
        this.hp = hp;
        this.firepower = firepower;
        this.evasion = evasion;
        this.accuracy = accuracy;
        this.rof = rof;
        this.clip = clip;
        this.armor = armor;
        this.speed = speed;
        this.ap = ap;
        this.critical = critical;
        this.tile = tile;
        this.skill = skill;
        this.skilld = skilld;
        this.skillic = skillic;
        this.skillcd = skillcd;
        this.skillUrl = skillUrl;
        this.tiled = tiled;
        this.intro = intro;
        this.pictureUrl = pictureUrl;
        this.pictureDUrl = pictureDUrl;
        Log.e("skillic", skillic +"");
        Log.e("skillcd", skillcd +"");
    }




    public int getId(){ return id; }
    public String getName(){ return name;}
    public String getPicture() {
        return picture;
    }
    public String getType() { return type; }
    public int getRarity() { return rarity; }

    public String getTime() { return time; }
    public String getObtain() { return obtain; }
    public int getHp() { return hp; }
    public int getFirepower() { return firepower; }
    public int getEvasion() { return evasion; }
    public int getAccuracy() { return accuracy; }
    public int getRof() { return rof; }
    public int getClip() { return clip; }
    public int getArmor() { return armor; }
    public int getSpeed() { return speed; }
    public int getAp() { return ap; }
    public int getCritical() { return critical; }
    public String getTile() { return tile; }
    public String getSkill() { return skill; }
    public String getSkilld() { return skilld; }
    public int getSkillic(){ return skillic;}
    public int getSkillcd(){ return skillcd;}
    public String getSkillUrl(){ return skillUrl;}
    public String getTiled() { return tiled; }
    public String getIntro() { return intro; }
    public String getPictureUrl() { return pictureUrl; }
    public String getPictureDUrl() { return pictureDUrl; }
}
