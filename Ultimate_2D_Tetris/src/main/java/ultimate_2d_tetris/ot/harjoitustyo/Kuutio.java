package ultimate_2d_tetris.ot.harjoitustyo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Kuutio extends Rectangle {

    int x;
    int y;
    int w = 40;
    int h = 40;
    Kentta kentta;
    boolean tormays;

    public Kuutio(int x, int y, int w, int h, Color vari, Kentta kentta) {
        super(w, h, vari);

        this.kentta = kentta;
        this.x = x;
        this.y = y;

        setTranslateX(x);
        setTranslateY(y);
    }

    void liikuAlas() {
        if (getTranslateY() + 40 < 800 && !(TormaaAlas())) {
            setTranslateY(getTranslateY() + 40);
            y += 40;
        } else {
            tormays = true;
        }
    }

    void liikuVasen() {
            if (!(TormaaVasen())) {
                setTranslateX(getTranslateX() - 40);
                x -= 40;
            }
        
    }

    void liikuOikea() {
            if (!(TormaaOikea())) {
                setTranslateX(getTranslateX() + 40);
                x += 40;
            }
        
    }
//        private void liikuPohja() {
//            for (int m = (int) kuutio.getTranslateY()/40;m>0;m--) {
//                if (kentta[m][(int) kuutio.getTranslateX()/40] != 0) {
//                    setTranslateY(m*40);
//                    y=m*40;
//                }
//            }
//        }
    boolean TormaaAlas() {
        if (getTranslateY() +40 >= 800) {
            return true;
        }
        
        return kentta.getKohta(((y / 40) + 1), (x / 40)) != 0;
    }

    boolean TormaaVasen() {
        if (getTranslateX() == 0) {
            return true;
        }
        if (kentta.getKohta(getKohtaY(), getKohtaX() - 1) != 0) {
            return true;
        }
        return false;
    }

    boolean TormaaOikea() {
        if (getTranslateX() == 360) {
            return true;
        }
        if (kentta.getKohta(getKohtaY(), getKohtaX() + 1) != 0) {
            return true;
        }
        return false;
    }
    boolean tormaa() {
        if (TormaaAlas()) {
            return true;
        }
        return tormays;
    }
    public int getKohtaY() {
        return (int) getTranslateY() / 40;
    }
    public int getKohtaX() {
        return (int) getTranslateX() / 40;
    }

}



