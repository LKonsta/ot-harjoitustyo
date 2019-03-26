
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate kassa;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }
    
    @Test
    public void ToimiikoKassa() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisMaksunToimintaEdullisesti() {
        int takaisin = kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(260, takaisin);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());  
    }
    
    @Test
    public void kateisMaksunToimintaMaukkaasti() {
        int takaisin2 = kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(100, takaisin2);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisMaksunEiToimintaEdullisesti() {
        int takaisin = kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(200, takaisin);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisMaksunEiToimintaMaukkaasti() {
        int takaisin2 = kassa.syoMaukkaasti(350);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(350, takaisin2);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiMaksunToiminta() {
        Maksukortti kortti = new Maksukortti(1000);
        boolean takaisin = kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertTrue(takaisin);
        assertEquals(760, kortti.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());

        boolean takaisin2 = kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertTrue(takaisin2);
        assertEquals(360, kortti.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty()); 
    }
    
    @Test
    public void korttiMaksunEiToiminta() {
        Maksukortti kortti = new Maksukortti(100);
        boolean takaisin = kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertFalse(takaisin);
        assertEquals(100, kortti.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());

        boolean takaisin2 = kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
        assertFalse(takaisin2);
        assertEquals(100, kortti.saldo());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KassaKorttiLatausTesti() {
        Maksukortti kortti = new Maksukortti(100);
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
}
