package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void KortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10",kortti.toString());
    }
    
    @Test
    public void RahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10",kortti.toString());
    }
    
    @Test
    public void SaldoVaheneeOikeinJosRahaaOnTarpeeksi() {
        kortti.otaRahaa(5);
        
        assertEquals("saldo: 0.5",kortti.toString());
    }
    
    @Test
    public void SaldoEiMuutuJosRahaaEiOleTarpeeksi() {
        kortti.otaRahaa(11);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void MetodiPalauuttaaTrueJosRahatRiittaa() {
        
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void MetodiPalauuttaaFalseJosRahatEiRiittaa() {
        
        assertFalse(kortti.otaRahaa(100));
    }
    
}
