package com.restaurante.inventario.service.export;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.restaurante.inventario.entity.Producto;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfService {

    public ByteArrayInputStream exportarProductos(List<Producto> productos){

        Document document = new Document(PageSize.A4);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try{

            PdfWriter.getInstance(document,out);

            document.open();

            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD,18);

            Paragraph p = new Paragraph("REPORTE DE INVENTARIO",titulo);

            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            document.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(5);

            tabla.setWidthPercentage(100);

            tabla.addCell("Código");
            tabla.addCell("Producto");
            tabla.addCell("Categoría");
            tabla.addCell("Unidad");
            tabla.addCell("Stock Mínimo");

            for(Producto producto : productos){

                tabla.addCell(producto.getCodigo());

                tabla.addCell(producto.getNombre());

                tabla.addCell(
                        producto.getCategoria()!=null
                                ?producto.getCategoria().getNombre()
                                :"");

                tabla.addCell(producto.getUnidadMedida());

                tabla.addCell(String.valueOf(producto.getStockMinimo()));

            }

            document.add(tabla);

            document.close();

        }catch(Exception e){

            throw new RuntimeException(e);

        }

        return new ByteArrayInputStream(out.toByteArray());

    }

}