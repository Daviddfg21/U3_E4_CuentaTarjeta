import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.Credito;
import dominio.Cuenta;

import java.util.Date;

public class CreditoTest {

    private Cuenta cuenta;
    private Credito tarjetaCredito;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta("123456", "Titular de la cuenta");
        tarjetaCredito = new Credito("789012", "Titular de la tarjeta", new Date(), 1000.0);
        cuenta.setTarjetaAsociada(tarjetaCredito);
    }

    @Test
    public void testCreditoRetirarExceedingCreditLimit() {
        assertThrows(Exception.class, () -> tarjetaCredito.retirar(1100.0));
    }

    @Test
    public void testCreditoRetirarWithinCreditLimit() throws Exception {
        tarjetaCredito.retirar(500.0);
        assertEquals(-500.0, tarjetaCredito.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoIngresar() throws Exception {
        tarjetaCredito.ingresar(200.0);
        assertEquals(200.0, tarjetaCredito.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoPagoEnEstablecimiento() throws Exception {
        tarjetaCredito.pagoEnEstablecimiento("Tienda", 300.0);
        assertEquals(-300.0, tarjetaCredito.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoGetSaldo() {
        assertEquals(1000.0, tarjetaCredito.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoLiquidar() {
        tarjetaCredito.liquidar(1, 2024);
        assertEquals(0.0, tarjetaCredito.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoLiquidarNoMovements() {
        tarjetaCredito.liquidar(2, 2024);
        assertEquals(0.0, tarjetaCredito.getSaldo(), 0.01);
    }
}

