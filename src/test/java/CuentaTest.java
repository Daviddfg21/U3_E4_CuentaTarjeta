import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.Cuenta;
import dominio.Movimiento;


public class CuentaTest {

    private Cuenta cuenta;

    @BeforeEach
    public void setUp() {
        cuenta = new Cuenta("123456", "Titular de la cuenta");
    }

    @Test
    public void testIngresarPositivo() throws Exception {
        cuenta.ingresar(100.0);
        assertEquals(100.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testIngresarNegativo() {
        assertThrows(Exception.class, () -> cuenta.ingresar(-50.0));
    }

    @Test
    public void testRetirarPositivo() throws Exception {
        cuenta.ingresar(200.0);
        cuenta.retirar(50.0);
        assertEquals(150.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testRetirarNegativo() {
        assertThrows(Exception.class, () -> cuenta.retirar(-30.0));
    }

    @Test
    public void testRetirarSaldoInsuficiente() {
        assertThrows(Exception.class, () -> cuenta.retirar(50.0));
    }

    @Test
    public void testGetSaldo() throws Exception {
        cuenta.ingresar(300.0);
        cuenta.retirar(100.0);
        assertEquals(200.0, cuenta.getSaldo(), 0.01);
    }

    @Test
    public void testAgregarMovimiento() {
        Movimiento movimiento = new Movimiento();
        movimiento.setConcepto("Prueba de movimiento");
        movimiento.setImporte(50.0);
        cuenta.addMovimiento(movimiento);

        MatcherAssert.assertThat(cuenta.getMovimientos(), hasItem(movimiento));
    }
}

