import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dominio.Movimiento;

import java.util.Date;

public class MovimientoTest {

    private Movimiento movimiento;

    @BeforeEach
    public void setUp() {
        movimiento = new Movimiento();
    }

    @Test
    public void testSetAndGetConcepto() {
        movimiento.setConcepto("Prueba de concepto");
        assertEquals("Prueba de concepto", movimiento.getConcepto());
    }

    @Test
    public void testSetAndGetFecha() {
        Date fecha = new Date();
        movimiento.setFecha(fecha);
        assertEquals(fecha, movimiento.getFecha());
    }

    @Test
    public void testSetAndGetImporte() {
        movimiento.setImporte(50.0);
        assertEquals(50.0, movimiento.getImporte(), 0.01);
    }
}
