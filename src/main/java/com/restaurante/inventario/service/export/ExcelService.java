package com.restaurante.inventario.service.export;

import com.restaurante.inventario.entity.Producto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ExcelService {

    public ByteArrayInputStream exportarProductos(List<Producto> productos) {

        try {

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Inventario");

            Row encabezado = sheet.createRow(0);

            encabezado.createCell(0).setCellValue("Código");
            encabezado.createCell(1).setCellValue("Producto");
            encabezado.createCell(2).setCellValue("Categoría");
            encabezado.createCell(3).setCellValue("Unidad");
            encabezado.createCell(4).setCellValue("Stock Mínimo");

            int fila = 1;

            for (Producto p : productos) {

                Row row = sheet.createRow(fila++);

                row.createCell(0).setCellValue(p.getCodigo());

                row.createCell(1).setCellValue(p.getNombre());

                row.createCell(2).setCellValue(
                        p.getCategoria() != null ?
                                p.getCategoria().getNombre() : "");

                row.createCell(3).setCellValue(p.getUnidadMedida());

                row.createCell(4).setCellValue(p.getStockMinimo());

            }

            for (int i = 0; i < 5; i++) {

                sheet.autoSizeColumn(i);

            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();

            workbook.write(out);

            workbook.close();

            return new ByteArrayInputStream(out.toByteArray());

        }

        catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}