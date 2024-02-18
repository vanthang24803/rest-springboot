package com.example.project.untils;


import com.example.project.models.Order;
import com.example.project.models.OrderDetail;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Locale;

@Getter
@Setter
public class EmailTemplateUntils {

    public static String createOrderConfirmationEmailBody(Order order) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(new Locale("de", "DE"));
        String htmlContent = "";
        try {
            htmlContent = new String(Files.readAllBytes(Paths.get("src/main/java/com/example/project/templates/order.html")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        htmlContent = htmlContent.replace("{ID}", String.valueOf(order.getId()));
        htmlContent = htmlContent.replace("{CUSTOMER}", order.getCustomer());
        htmlContent = htmlContent.replace("{ADDRESS}", order.getAddress());
        htmlContent = htmlContent.replace("{PHONE}", order.getNumberPhone());
        htmlContent = htmlContent.replace("{QUANTITY}", String.valueOf(order.getQuantity()));
        htmlContent = htmlContent.replace("{TOTAL_PRICE}",
                String.valueOf(numberFormat.format(order.getTotalPrice())));
        htmlContent = htmlContent.replace("{STATUS}", String.valueOf(order.getStatus()));

        StringBuilder detailsBuilder = new StringBuilder();
        for (OrderDetail detail : order.getDetails()) {
            detailsBuilder.append("<tr>")
                    .append("<td style=\"border: 1px solid #dddddd; text-align: left; padding: 8px;\">").append(detail.getName()).append("</td>")
                    .append("<td style=\"border: 1px solid #dddddd; text-align: left; padding: " +
                            "8px;\">").append(detail.getPrice()).append(" VNĐ</td>")
                    .append("<td style=\"border: 1px solid #dddddd; text-align: left; padding: 8px;\">").append(detail.getOption()).append("</td>")
                    .append("<td style=\"border: 1px solid #dddddd; text-align: left; padding: 8px;\">").append(detail.getQuantity()).append("</td>")
                    .append("</tr>");
        }

        htmlContent = htmlContent.replace("{DETAILS}", detailsBuilder.toString());

        return htmlContent;
    }
}
