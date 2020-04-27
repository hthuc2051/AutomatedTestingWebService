package com.fpt.automatedtesting.common;


import com.fpt.automatedtesting.headlecturers.HeadLecturerExcelDto;
import com.fpt.automatedtesting.lecturers.LecturerExcelDto;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImportExcelFile {
    public static List<?> importExcelFileByRole(ExcelFileDto dto) throws IOException {
        XSSFWorkbook workbook = null;
        try{
            if(dto.getRole().equals(CustomConstant.HEAD_LECTURER))
            {
                List<HeadLecturerExcelDto> listHead = new ArrayList<HeadLecturerExcelDto>();
                String excelFile = FileManager.uploadFileToReturnString(dto.getExcelFile());
                workbook = new XSSFWorkbook(excelFile);
                XSSFSheet worksheet = workbook.getSheetAt(0);
                for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
                    HeadLecturerExcelDto headLecturerInfor = new HeadLecturerExcelDto();
                    XSSFRow row = worksheet.getRow(i);
                    if(row != null)
                    {
                        if(row.getPhysicalNumberOfCells()>1)
                        {
                            headLecturerInfor.setNo((int) row.getCell(0).getNumericCellValue());
                            headLecturerInfor.setLecturerCode(row.getCell(1).getStringCellValue());
                            headLecturerInfor.setFirstName(row.getCell(2).getStringCellValue());
                            headLecturerInfor.setMiddleName(row.getCell(3).getStringCellValue());
                            headLecturerInfor.setLastName(row.getCell(4).getStringCellValue());
                            headLecturerInfor.setEmail(row.getCell(5).getStringCellValue());
                            headLecturerInfor.setContactPhone(row.getCell(6).getNumericCellValue()+"");
                            listHead.add(headLecturerInfor);
                        }
                    }
                }
                return listHead;
            }else if(dto.getRole().equals(CustomConstant.LECTURER))
            {
                List<LecturerExcelDto> listLecturer = new ArrayList<LecturerExcelDto>();
                String excelFile = FileManager.uploadFileToReturnString(dto.getExcelFile());
                workbook = new XSSFWorkbook(excelFile);
                XSSFSheet worksheet = workbook.getSheetAt(0);
                for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
                    LecturerExcelDto headLecturerInfor = new LecturerExcelDto();
                    XSSFRow row = worksheet.getRow(i);
                    if(row != null)
                    {
                        if(row.getPhysicalNumberOfCells()>1) {
                            headLecturerInfor.setNo((int) row.getCell(0).getNumericCellValue());
                            headLecturerInfor.setStudentCode(row.getCell(1).getStringCellValue());
                            headLecturerInfor.setFirstName(row.getCell(2).getStringCellValue());
                            if(row.getCell(3) != null)
                            {
                                headLecturerInfor.setMiddleName(row.getCell(3).getStringCellValue());
                            }else
                            {
                                headLecturerInfor.setMiddleName("");
                            }
                            headLecturerInfor.setLastName(row.getCell(4).getStringCellValue());
                            headLecturerInfor.setEmail(row.getCell(5).getStringCellValue());
                            listLecturer.add(headLecturerInfor);
                        }
                    }
                }
                return listLecturer;
            }
        }catch(Exception e)
        {
           e.printStackTrace();

        }finally {
            workbook.close();
        }
       return null;
    }
}
