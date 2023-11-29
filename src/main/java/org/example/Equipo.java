package org.example;

import java.sql. *;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Equipo extends AbstractEquipoOperation {


    @Override
    public void mostrarEquipo(int idEquipo) {

        EquipoBean equipo = obtenerEquipoPorId(idEquipo);
        if (equipo != null) {
            System.out.println("Información del Equipo:");
            System.out.println("ID: " + equipo.getIdEquipo());
            System.out.println("Nombre: " + equipo.getNombre());
            System.out.println("Cantidad titulares: " + equipo.getTitulares());
            System.out.println("Cantidad suplentes: " + equipo.getSuplentes());
            System.out.println("Director técnico: " + equipo.getDirectorTecnico());
            System.out.println("Puntos: " + equipo.getPuntos());
            System.out.println("Partidos jugados: " + equipo.getPartidosJugados());
        } else {
            System.out.println("No existe equipo para el ID seleccionado.");
        }
    }

    @Override
    public void mostrarEquipos() {

        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM equipo")) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("idEquipo"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Cantidad titulares: " + rs.getInt("titulares"));
                System.out.println("Cantidad suplentes: " + rs.getInt("suplentes"));
                System.out.println("Director técnico: " + rs.getString("directorTecnico"));
                System.out.println("Puntos: " + rs.getInt("puntos"));
                System.out.println("Partidos jugados: " + rs.getInt("partidosJugados"));
                System.out.println("****");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public List<EquipoBean> obtenerEquipos() {

        List<EquipoBean> equipos = new ArrayList<>();
        try (Connection con = Conexion.conectar();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM equipo")) {
            while (rs.next()) {
                EquipoBean equipo = new EquipoBean();
                equipo.setIdEquipo(rs.getInt("idEquipo"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setTitulares(rs.getInt("titulares"));
                equipo.setSuplentes(rs.getInt("suplentes"));
                equipo.setDirectorTecnico(rs.getString("directorTecnico"));
                equipo.setPuntos(rs.getInt("puntos"));
                equipo.setPartidosJugados(rs.getInt("partidosJugados"));
                equipos.add(equipo);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return equipos;

    }

    @Override
    public void mostrarListaEquipos() {

        List<EquipoBean> equipos = obtenerEquipos();
        if (equipos.isEmpty()) {
            System.out.println("No hay equipos registrados.");
            return;
        }
        System.out.println("Lista de Equipos:");
        System.out.println("ID | Nombre");
        for (EquipoBean equipo : equipos) {
            System.out.println(equipo.getIdEquipo() + " | " + equipo.getNombre());
        }

    }

    @Override
    public EquipoBean solicitarDatosEquipo(Scanner scanner) {

        EquipoBean nuevoEquipo = new EquipoBean();
        scanner.nextLine();

        System.out.println("Ingrese el nombre del equipo:");
        nuevoEquipo.setNombre(scanner.nextLine());

        System.out.println("Ingrese la cantidad de titulares:");
        nuevoEquipo.setTitulares(scanner.nextInt());

        System.out.println("Ingrese la cantidad de suplentes:");
        nuevoEquipo.setSuplentes(scanner.nextInt());

        scanner.nextLine();
        System.out.println("Ingrese el nombre del director técnico:");
        nuevoEquipo.setDirectorTecnico(scanner.nextLine());

        System.out.println("Ingrese la cantidad de puntos:");
        nuevoEquipo.setPuntos(scanner.nextInt());

        System.out.println("Ingrese la cantidad de partidos jugados:");
        nuevoEquipo.setPartidosJugados(scanner.nextInt());

        return nuevoEquipo;

    }

    @Override
    public EquipoBean obtenerEquipoPorId(int idEquipo) {

        EquipoBean equipo = null;
        try (Connection con = Conexion.conectar();
             PreparedStatement pstmt = con.prepareStatement("SELECT * FROM equipo WHERE idEquipo = ?")) {

            pstmt.setInt(1, idEquipo);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                equipo = new EquipoBean();
                equipo.setIdEquipo(rs.getInt("idEquipo"));
                equipo.setNombre(rs.getString("nombre"));
                equipo.setTitulares(rs.getInt("titulares"));
                equipo.setSuplentes(rs.getInt("suplentes"));
                equipo.setDirectorTecnico(rs.getString("directorTecnico"));
                equipo.setPuntos(rs.getInt("puntos"));
                equipo.setPartidosJugados(rs.getInt("partidosJugados"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return equipo;

    }

    @Override
    public void crearEquipo(EquipoBean equipo) {

        try (Connection con = Conexion.conectar();
             PreparedStatement pstmt = con.prepareStatement("INSERT INTO equipo (nombre, titulares, suplentes, directorTecnico, puntos, partidosJugados) VALUES (?, ?, ?, ?, ?, ?)")) {

            pstmt.setString(1, equipo.getNombre());
            pstmt.setInt(2, equipo.getTitulares());
            pstmt.setInt(3, equipo.getSuplentes());
            pstmt.setString(4, equipo.getDirectorTecnico());
            pstmt.setInt(5, equipo.getPuntos());
            pstmt.setInt(6, equipo.getPartidosJugados());

            pstmt.executeUpdate();
            System.out.println("Equipo creado correctamente.");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void actualizarEquipo(EquipoBean equipo) {

        try (Connection con = Conexion.conectar();
             PreparedStatement pstmt = con.prepareStatement("UPDATE equipo SET nombre=?, titulares=?, suplentes=?, directorTecnico=?, puntos=?, partidosJugados=? WHERE idEquipo=?")) {

            pstmt.setString(1, equipo.getNombre());
            pstmt.setInt(2, equipo.getTitulares());
            pstmt.setInt(3, equipo.getSuplentes());
            pstmt.setString(4, equipo.getDirectorTecnico());
            pstmt.setInt(5, equipo.getPuntos());
            pstmt.setInt(6, equipo.getPartidosJugados());
            pstmt.setInt(7, equipo.getIdEquipo());

            pstmt.executeUpdate();
            System.out.println("Equipo actualizado correctamente.");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    @Override
    public void eliminarEquipo(int idEquipo) {

        try (Connection con = Conexion.conectar();
             PreparedStatement pstmt = con.prepareStatement("DELETE FROM equipo WHERE idEquipo=?")) {

            pstmt.setInt(1, idEquipo);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Equipo eliminado correctamente.");
            } else {
                System.out.println("No se encontró el equipo para eliminar.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
