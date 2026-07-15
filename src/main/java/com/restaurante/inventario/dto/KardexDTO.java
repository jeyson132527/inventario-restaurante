package com.restaurante.inventario.dto;

import java.time.LocalDate;

public class KardexDTO {

    private LocalDate fecha;

    private String movimiento;

    private String producto;

    private double entrada;

    private double salida;

    private double saldo;

    public KardexDTO() {
    }

    public KardexDTO(LocalDate fecha,
                     String movimiento,
                     String producto,
                     double entrada,
                     double salida,
                     double saldo) {

        this.fecha = fecha;
        this.movimiento = movimiento;
        this.producto = producto;
        this.entrada = entrada;
        this.salida = salida;
        this.saldo = saldo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public double getEntrada() {
        return entrada;
    }

    public void setEntrada(double entrada) {
        this.entrada = entrada;
    }

    public double getSalida() {
        return salida;
    }

    public void setSalida(double salida) {
        this.salida = salida;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}