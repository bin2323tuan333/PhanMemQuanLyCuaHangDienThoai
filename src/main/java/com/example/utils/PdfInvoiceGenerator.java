package com.example.utils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.io.font.PdfEncodings;
import com.example.DTO.CartInfo;
import com.example.DTO.CustomerInfo;
import com.itextpdf.layout.properties.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import com.example.DTO.SystemSetting;
import com.example.services.SystemService;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.util.List;

public class PdfInvoiceGenerator {
  public boolean saveInvoiceWithChooser(Window ownerWindow, CustomerInfo customer, List<CartInfo> cartList, String total) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Lưu hóa đơn PDF");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    String fileName = "HD_" + customer.getCustomerName().replaceAll("\\s+", "") + "_" + System.currentTimeMillis() % 1000 + ".pdf";
    fileChooser.setInitialFileName(fileName);
    File file = fileChooser.showSaveDialog(ownerWindow);
    
    if (file != null) {
      return generatePdf(file.getAbsolutePath(), customer, cartList, total);
    }
    return false;
  }
  
  private boolean generatePdf(String path, CustomerInfo customer, List<CartInfo> cartList, String total) {
    try {
      PdfWriter writer = new PdfWriter(path);
      PdfDocument pdf = new PdfDocument(writer);
      Document document = new Document(pdf);
      
      // Load font
      try (var fontStream = getClass().getResourceAsStream("/fonts/Arial.ttf")) {
        if (fontStream == null) {
          throw new IllegalStateException("Font not found: /fonts/Arial.ttf");
        }
        byte[] fontBytes = fontStream.readAllBytes();
        PdfFont vietnameseFont = PdfFontFactory.createFont(
                fontBytes, PdfEncodings.IDENTITY_H,
                PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED
        );
        document.setFont(vietnameseFont);
      }
      
      // Lấy thông tin cửa hàng
      SystemService systemService = new SystemService();
      SystemSetting shop = systemService.getSystemInfo();
      
      // ───── HEADER CỬA HÀNG ─────
      document.add(new Paragraph(shop.getShopName())
                           .setBold().setFontSize(20)
                           .setTextAlignment(TextAlignment.CENTER));
      
      document.add(new Paragraph("Địa chỉ: " + shop.getShopAddress())
                           .setFontSize(10)
                           .setTextAlignment(TextAlignment.CENTER));
      
      document.add(new Paragraph("SĐT: " + shop.getShopPhone()
                                         + "    |    MST: " + shop.getTaxCode())
                           .setFontSize(10)
                           .setTextAlignment(TextAlignment.CENTER));
      
      // Đường kẻ phân cách
      document.add(new Paragraph("─────────────────────────────────────────")
                           .setTextAlignment(TextAlignment.CENTER).setFontSize(10));
      
      // ───── TIÊU ĐỀ HÓA ĐƠN ─────
      document.add(new Paragraph("HÓA ĐƠN BÁN HÀNG")
                           .setBold().setFontSize(16)
                           .setTextAlignment(TextAlignment.CENTER));
      
      document.add(new Paragraph("Ngày: " + java.time.LocalDate.now())
                           .setFontSize(10)
                           .setTextAlignment(TextAlignment.CENTER));
      
      document.add(new Paragraph(" ")); // khoảng trống
      
      // ───── THÔNG TIN KHÁCH HÀNG ─────
      document.add(new Paragraph("Khách hàng: " + customer.getCustomerName()).setFontSize(11));
      document.add(new Paragraph("Số điện thoại: " + customer.getPhone()).setFontSize(11));
      
      document.add(new Paragraph(" "));
      
      // ───── BẢNG SẢN PHẨM ─────
      Table table = new Table(new float[] { 30f, 200f, 50f, 100f, 100f });
      table.setWidth(UnitValue.createPercentValue(100));
      
      // Header bảng
      for (String header : new String[] { "STT", "Sản phẩm", "SL", "Đơn giá", "Thành tiền" }) {
        table.addCell(new Cell().add(new Paragraph(header).setBold())
                              .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                              .setTextAlignment(TextAlignment.CENTER));
      }
      
      int stt = 1;
      for (CartInfo item : cartList) {
        table.addCell(new Cell().add(new Paragraph(String.valueOf(stt++)))
                              .setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Cell().add(new Paragraph(item.getProductInfo().getProductName())));
        table.addCell(new Cell().add(new Paragraph(String.valueOf(item.getQuantity())))
                              .setTextAlignment(TextAlignment.CENTER));
        table.addCell(new Cell().add(new Paragraph(
                        String.format("%,.0f đ", item.getProductInfo().getPrice())))
                              .setTextAlignment(TextAlignment.RIGHT));
        table.addCell(new Cell().add(new Paragraph(
                        String.format("%,.0f đ", item.getProductInfo().getPrice() * item.getQuantity())))
                              .setTextAlignment(TextAlignment.RIGHT));
      }
      
      document.add(table);
      
      // ───── TỔNG TIỀN ─────
      document.add(new Paragraph(" "));
      document.add(new Paragraph("Tổng thanh toán: " + total)
                           .setBold().setFontSize(13)
                           .setTextAlignment(TextAlignment.RIGHT));
      
      // ───── FOOTER ─────
      document.add(new Paragraph(" "));
      document.add(new Paragraph("Cảm ơn quý khách đã mua hàng!")
                           .setItalic().setFontSize(10)
                           .setTextAlignment(TextAlignment.CENTER));
      
      document.close();
      return true;
      
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
