import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {
    
    Maksukortti kortti;
    
     @Before
     public void hello() {
         kortti = new Maksukortti(10);
     }
     
     @Test
     public void konstruktoriAsettaaSaldonOikein() {
         
         String vastaus = kortti.toString();
         
         assertEquals("Kortilla on rahaa 10.0 euroa", vastaus);
     }
     
    @Test
    public void syoEdullisestiVahentaaSaldonOikein() {
        
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukaastiVahentaaSaldonOikein() {

        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }
    
    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {

        kortti.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {

        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        
        kortti.syoEdullisesti();

        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortilleVoiLadataRahaa() {
        kortti.lataaRahaa(25);
        assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaRahaa() {
        kortti.lataaRahaa(-125);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void kortinSaldoEiYlitaMaksimiarvoa() {
        kortti.lataaRahaa(200);
        assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaaVainEdullisestiVerran() {
        Maksukortti korttiEdullinen = new Maksukortti(2.5);
        korttiEdullinen.syoEdullisesti();
        
        assertEquals("Kortilla on rahaa 0.0 euroa", korttiEdullinen.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaaVainMaukkaastiVerran() {
        Maksukortti korttiMaukas = new Maksukortti(4.0);
        korttiMaukas.syoMaukkaasti();

        assertEquals("Kortilla on rahaa 0.0 euroa", korttiMaukas.toString());
    }
     
}