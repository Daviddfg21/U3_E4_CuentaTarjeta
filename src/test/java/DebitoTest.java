import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.Cuenta;
import dominio.Debito;

import java.util.Date;

public class DebitoTest {

    private Cuenta cuenta;
    private Debito tarjetaDebito;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta("123456", "Titular de la cuenta");
        tarjetaDebito = new Debito("789012", "Titular de la tarjeta", new Date());
        cuenta.setTarjetaAsociada(tarjetaDebito);
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
}

