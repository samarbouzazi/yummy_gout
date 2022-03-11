/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author DELL PRCISION 3551
 */
public class Excel {
    //        try {
//            Connection cnx = MaCon.getInstance().getCnx();
//            
//            String excelFilePath = "tableFours.xlsx";       
//            
//            
//            String sql = "SELECT * FROM fournisseurs";
// 
//            PreparedStatement ste =cnx.prepareStatement(sql);
// 
//            ResultSet result = ste.executeQuery(sql);
// 
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            XSSFSheet sheet = workbook.createSheet("liste fournisseurs");
// 
//            writeHeaderLine(sheet);
// 
//            writeDataLines(result, workbook, sheet);
// 
//            FileOutputStream outputstream;
//            try {
//            
//            outputstream = new FileOutputStream(excelFilePath);
//            workbook.write(outputstream);
//           workbook.close();
//            ste.close();
//            } catch (FileNotFoundException ex) {
//                System.out.println(ex.getMessage());
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
// 
//        
//    }
// 
//    private void writeHeaderLine(XSSFSheet sheet) {
// 
//        Row headerRow = sheet.createRow(0);
// 
//        Cell headerCell = headerRow.createCell(0);
//        headerCell.setCellValue("id");
// 
//        headerCell = headerRow.createCell(1);
//        headerCell.setCellValue("nom");
// 
//        headerCell = headerRow.createCell(2);
//        headerCell.setCellValue("prenom");
// 
//        headerCell = headerRow.createCell(3);
//        headerCell.setCellValue("catégorie");
// 
//        headerCell = headerRow.createCell(4);
//        headerCell.setCellValue("numero tel");
//        
//        headerCell = headerRow.createCell(5);
//        headerCell.setCellValue("adresse");
//    }
// 
//    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
//            XSSFSheet sheet) throws SQLException {
//        int rowCount = 1;
// 
//        while (result.next()) {
//            String idf = result.getString("idf");
//            String nomf = result.getString("nomf");
//            String prenomf = result.getString("prenomf");
//            String catf = result.getString("catf");
//            int telf = result.getInt("telf");
//            String addf = result.getString("addf");
// 
//            Row row = sheet.createRow(rowCount++);
// 
//            int columnCount = 0;
//            Cell cell = row.createCell(columnCount++);
//            cell.setCellValue(idf);
// 
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(nomf);
// 
//            cell = row.createCell(columnCount++);
// 
//            CellStyle cellStyle = workbook.createCellStyle();
//            CreationHelper creationHelper = workbook.getCreationHelper();
//            //cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
//            cell.setCellStyle(cellStyle);
// 
//            cell.setCellValue(prenomf);
// 
//            cell = row.createCell(columnCount++);
//            cell.setCellValue(catf);
// 
//            cell = row.createCell(columnCount);
//            cell.setCellValue(telf);
// 
//            cell = row.createCell(columnCount);
//            cell.setCellValue(addf);
//        }
//    }
    
}
