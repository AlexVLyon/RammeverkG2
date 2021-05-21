package org.Easy2dGameEngine;

import org.Easy2dGameEngine.ScreenAndTextureUpload.Entity;

public class GameObject extends Entity {
    private int hp, attack, defence;
    private String name;


    public GameObject(String file, int x, int y) {
        super(file, x, y);
    }


    public void DamageGameObject(GameObject gameObject) {
        int gethp = gameObject.getHp();
        int damageReceived = getAttack() - gameObject.defence;
        if (damageReceived >= 0)
            gethp -= damageReceived;

        gameObject.setHp(gethp);
    }


    public boolean Dead() {
        if (this.hp <= 0) {
            System.out.println("Dead");
            return true;

        }
        return false;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
