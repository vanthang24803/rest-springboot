package com.example.project.untils;

import com.example.project.dtos.request.OrderDto;
import com.example.project.dtos.request.OrderDetailDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailTemplateUntils {
    public static String createOrderConfirmationEmailBody(OrderDto orderDto) {
        StringBuilder body = new StringBuilder();

        body.append("<td class=\"esd-stripe\" align=\"center\">"
                + "<table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">"
                + "<tbody>"
                + "<tr>"
                + "<td class=\"esd-structure es-p20\" align=\"left\">"
                + "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
                + "<tbody>"
                + "<tr>"
                + "<td width=\"800\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">"
                + "<table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">"
                + "<tbody>"
                + "<tr>"
                + "<td align=\"center\" class=\"esd-block-image es-p10t es-p10b\" style=\"font-size: 0px;\"><a target=\"_blank\"><img src=\"https://eenufwy.stripocdn.email/content/guids/CABINET_83c79fe6710c9b2a88d2db353ad3b6f6/images/99411618298697800.png\" alt style=\"display: block;\" width=\"100\"></a></td>"
                + "</tr>"
                + "<tr>"
                + "<td align=\"center\" class=\"esd-block-text es-p10b es-m-txt-c\">"
                + "<h1 style=\"font-size: 46px; line-height: 100%;\">Order confirmation</h1>"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td align=\"center\" class=\"esd-block-text es-p5t es-p20b es-p40r es-p40l es-m-p0r es-m-p0l\">"
                + "<p>Thank you for your order. Your order is being prepared!</p>"
                + "</td>"
                + "</tr>"
                + "<tr>"
                + "<td align=\"left\" class=\"esd-block-text es-p5t es-p20b es-p40r es-p40l " +
                "es-m-p0r es-m-p0l\">"
                + "<p>Customer: " + orderDto.getCustomer() + "</p>"
                + "<p>Address: " + orderDto.getAddress() + "</p>"
                + "<p>Phone: " + orderDto.getNumberPhone() + "</p>"
                + "<p>Quantity: " + orderDto.getQuantity() + "</p>"
                + "<p>Total price: " + orderDto.getTotalPrice() + "</p>"
                + "<p>Detail: ");
                    for (OrderDetailDto detail : orderDto.getDetails()) {
                        body.append("<p> - ")
                                .append(detail.getName())
                                .append(" x ")
                                .append(detail.getQuantity())
                                .append("</p>")
                                .append("</br>");
                    }
                body.append("</p></td>"
                        + "</tr>"
                        + "</tbody>"
                        + "</table>"
                        + "</td>"
                        + "</tr>"
                        + "</tbody>"
                        + "</table>"
                        + "</td>"
                        + "</tr>"
                        + "</tbody>"
                        + "</table>"
                        + "</td>");

        return body.toString();
    }
}
