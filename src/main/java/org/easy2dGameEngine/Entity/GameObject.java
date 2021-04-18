package org.easy2dGameEngine.Entity;

public class GameObject extends Entity {
    private int hp,attack,defence,name;


    public GameObject(String file, int x, int y){
        super(file,x,y);
    }

public void MoveGameObjectX(int x){
        MoveEntityX(x);
}

public void MoveGameObjectY(int y){
        MoveEntityY(y);
    }



    public void DamageGameObject(GameObject gameObject){

        int damageReceived = getAttack() - gameObject.defence;
        if (damageReceived <= 0)
        gameObject.hp -=  0;
        else
            gameObject.hp -= damageReceived;
    }

    public boolean Dead(){
        if(this.hp <= 0)
            return true;

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

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
