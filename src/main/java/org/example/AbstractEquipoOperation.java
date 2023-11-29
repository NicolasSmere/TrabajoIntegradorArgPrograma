package org.example;
import java.util.List;
import java.util.Scanner;
public abstract class AbstractEquipoOperation {

    public abstract void mostrarEquipo(int idEquipo);

    public abstract void mostrarEquipos();

    public abstract List<EquipoBean> obtenerEquipos();

    public abstract void mostrarListaEquipos();

    public abstract  EquipoBean  solicitarDatosEquipo(Scanner scanner);

    public abstract EquipoBean obtenerEquipoPorId(int idEquipo);

    public abstract void crearEquipo(EquipoBean equipo);

    public abstract void actualizarEquipo(EquipoBean equipo);

    public abstract void eliminarEquipo(int idEquipo);

}
