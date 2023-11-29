package org.example;

import java.util.Scanner;

public class TrabajoIntegrador {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Equipo equipo = new Equipo();

        int menu = 0;
        int id_equipo = 0;

        System.out.println("Elegir opcion:");
        System.out.println("1 - Informacion de equipo");
        System.out.println("2 - Informacion de la totalidad de equipos");
        System.out.println("3 - Agregar equipo");
        System.out.println("4 - Modificar datos del equipo");
        System.out.println("5 - Eliminar un equipo");
        System.out.println("6 - Salir");
        menu = sc.nextInt();


        switch(menu){
            case 1:{
                equipo.mostrarListaEquipos();
                System.out.println("Ingrese el ID del equipo para ver su información:");
                id_equipo = sc.nextInt();
                equipo.mostrarEquipo(id_equipo);
                break;
            }
            case 2:{
                equipo.mostrarEquipos();
                break;
            }

            case 3: {
                Equipo equipo_ = new Equipo();
                EquipoBean nuevoEquipo = equipo_.solicitarDatosEquipo(sc);
                equipo_.crearEquipo(nuevoEquipo);
                break;
            }

            case 4: {
                Equipo equipo_ = new Equipo();
                equipo_.mostrarListaEquipos();
                System.out.println("Ingrese el ID del equipo a actualizar: ");
                int idEquipoActualizar = sc.nextInt();
                EquipoBean equipoExistente = equipo_.obtenerEquipoPorId(idEquipoActualizar);
                if (equipoExistente != null) {
                    EquipoBean datosActualizados = equipo_.solicitarDatosEquipo(sc);
                    datosActualizados.setIdEquipo(idEquipoActualizar);
                    equipo_.actualizarEquipo(datosActualizados);
                } else {
                    System.out.println("No se encontró el equipo para actualizar.");
                }
                break;
            }

            case 5: {
                equipo.mostrarListaEquipos();
                System.out.println("Ingrese el ID del equipo a eliminar: ");
                int idEquipoEliminar = sc.nextInt();
                equipo.eliminarEquipo(idEquipoEliminar);
                break;
            }

            case 6:{
                break;
            }
        }

    }
}
