package ultimatetetris;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Kuutio extends ImageView {

    int x;
    int y;
    Kentta kentta;
    boolean tormays;
    /**
     * Uuden kuution luominen 
     * @param x x-koordinaatti.
     * @param y y-koordinaatti.
     * @param vari kuva joka sijaintiin asetetaan.
     * @param kentta tieto kentästä jolle kuvat asetetaan.
     */
    public Kuutio(int x, int y, Image vari, Kentta kentta) {
        super(vari);

        this.kentta = kentta;
        this.x = x;
        this.y = y;

        setTranslateX(x);
        setTranslateY(y);
    }

    
    
    void liikuAlas() {
        if (getTranslateY() + 30 < 600 && !tormaaAlas()) {
            setTranslateY(getTranslateY() + 30);
            y += 30;
        } else {
            tormays = true;
        }
    }

    void liikuVasen() {
        if (!tormaaVasen()) {
            setTranslateX(getTranslateX() - 30);
            x -= 30;
        }   
    }

    void liikuOikea() {
        if (!tormaaOikea()) {
            setTranslateX(getTranslateX() + 30);
            x += 30;
        }   
    }
    boolean tormaaAlas() {
        if (getTranslateY() + 30 >= 600) {
            return true;
        }
        return kentta.getKohta((getKohtaY() + 1), getKohtaX()) != 0;
    }

    boolean tormaaVasen() {
        if (getTranslateX() == 0) {
            return true;
        }
        if (kentta.getKohta(getKohtaY(), getKohtaX() - 1) != 0) {
            return true;
        }
        return false;
    }

    boolean tormaaOikea() {
        if (getTranslateX() == 270) {
            return true;
        }
        if (kentta.getKohta(getKohtaY(), getKohtaX() + 1) != 0) {
            return true;
        }
        return false;
    }
    boolean tormaa() {
        if (tormaaAlas()) {
            return true;
        }
        return tormays;
    }
    /**
     *  Hakee kuution kohdan y.
     * @return palauttaa y kohdan.
     */
    public int getKohtaY() {
        return (int) getTranslateY() / 30;
    }
    /**
     * Hakee kuution kohdan x
     * @return palauttaa x kohdan.
     */
    public int getKohtaX() {
        return (int) getTranslateX() / 30;
    }

}