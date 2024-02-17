package com.example.project.untils;

import com.example.project.models.Order;
import com.example.project.models.OrderDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ExcelExportUtils {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public void exportOrdersToExcel(List<Order> orders) {
        this.workbook = new XSSFWorkbook();
        this.sheet = workbook.createSheet("Orders");


        Row headerRow = sheet.createRow(0);
        createCell(headerRow, 0, "ID");
        createCell(headerRow, 1, "Email");
        createCell(headerRow, 2, "Customer");
        createCell(headerRow, 3, "Number Phone");
        createCell(headerRow, 4, "Address");
        createCell(headerRow, 5, "Payment");
        createCell(headerRow, 6, "Status");
        createCell(headerRow, 7, "Shipping");
        createCell(headerRow, 8, "Quantity");
        createCell(headerRow, 9, "Total Price");
        createCell(headerRow, 10, "Created At");
        createCell(headerRow, 11, "Updated At");

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            Row row = sheet.createRow(i + 1);
            createCell(row, 0, order.getId().toString());
            createCell(row, 1, order.getEmail());
            createCell(row, 2, order.getCustomer());
            createCell(row, 3, order.getNumberPhone());
            createCell(row, 4, order.getAddress());
            createCell(row, 5, order.getPayment().toString());
            createCell(row, 6, order.getStatus().toString());
            createCell(row, 7, Boolean.toString(order.isShipping()));
            createCell(row, 8, Integer.toString(order.getQuantity()));
            createCell(row, 9, Long.toString(order.getTotalPrice()));
            createCell(row, 10, order.getCreatedAt().format(formatter));
            createCell(row, 11, order.getUpdatedAt().format(formatter));
        }

        try (FileOutputStream fileOut = new FileOutputStream("orders.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exportOrderDetailsToExcel(List<Order> orders) {
        XSSFSheet detailSheet = workbook.createSheet("Order Details");

        Row headerRow = detailSheet.createRow(0);
        createCell(headerRow, 0, "ID");
        createCell(headerRow, 1, "Product ID");
        createCell(headerRow, 2, "Option ID");
        createCell(headerRow, 3, "Name");
        createCell(headerRow, 4, "Thumbnail");
        createCell(headerRow, 5, "Option");
        createCell(headerRow, 6, "Price");
        createCell(headerRow, 7, "Sale");
        createCell(headerRow, 8, "Quantity");
        createCell(headerRow, 9, "Order ID");

        int rowIndex = 1;
        for (Order order : orders) {
            for (OrderDetail detail : order.getDetails()) {
                Row row = detailSheet.createRow(rowIndex++);
                createCell(row, 0, detail.getId().toString());
                createCell(row, 1, detail.getProductId());
                createCell(row, 2, detail.getOptionId());
                createCell(row, 3, detail.getName());
                createCell(row, 4, detail.getThumbnail());
                createCell(row, 5, detail.getOption());
                createCell(row, 6, Double.toString(detail.getPrice()));
                createCell(row, 7, Integer.toString(detail.getSale()));
                createCell(row, 8, Integer.toString(detail.getQuantity()));
                createCell(row, 9, order.getId().toString());
            }
        }
    }


    private void createCell(Row row, int column, String value) {
        Cell cell = row.createCell(column);
        cell.setCellValue(value);
    }
}
