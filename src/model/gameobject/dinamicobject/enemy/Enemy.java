package model.gameobject.dinamicobject.enemy;

public interface Enemy {
    void shoot();

    void takeDamage(double damage);

    void notifyDropCoin();

    double getLife();
}
