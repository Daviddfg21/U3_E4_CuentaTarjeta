import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.Credito;
import dominio.Cuenta;
import dominio.Debito;

import java.util.Date;

public class TarjetaTest {

    private Cuenta cuenta;
    private Debito tarjetaDebito;
    private Credito tarjetaCredito;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta("123456", "Titular de la cuenta");
        tarjetaDebito = new Debito("789012", "Titular de la tarjeta débito", new Date());
        tarjetaCredito = new Credito("345678", "Titular de la tarjeta crédito", new Date(), 1000.0);

        cuenta.setTarjetaAsociada(tarjetaDebito);
        tarjetaCredito.setCuenta(cuenta);
    }

    @Test
    public void testDebitoRetirar() throws Exception {
        cuenta.ingresar(500.0);
        tarjetaDebito.retirar(300.0);
        assertEquals(200.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testDebitoIngresar() throws Exception {
        tarjetaDebito.ingresar(200.0);
        assertEquals(200.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testDebitoPagoEnEstablecimiento() throws Exception {
        cuenta.ingresar(500.0);
        tarjetaDebito.pagoEnEstablecimiento("Tienda", 300.0);
        assertEquals(200.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testDebitoGetSaldo() {
        assertEquals(0.0, tarjetaDebito.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoRetirar() throws Exception {
        tarjetaCredito.retirar(200.0);
        assertEquals(-200.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoIngresar() throws Exception {
        tarjetaCredito.ingresar(150.0);
        assertEquals(150.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoPagoEnEstablecimiento() throws Exception {
        tarjetaCredito.pagoEnEstablecimiento("Restaurante", 50.0);
        assertEquals(-50.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testCreditoGetSaldo() {
        assertEquals(1000.0, tarjetaCredito.getSaldo(), 0.01);
    }
}

