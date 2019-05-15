package es.edu.ull.metaheuristicas.utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import es.edu.ull.metaheuristicas.sa.Destino;
import es.edu.ull.metaheuristicas.sa.Ubicacion;
public class LeerFicherosExcel {

	String nombreArchivo = "POIsTenerife.xlsx";
	String rutaArchivo = "D:\\" + nombreArchivo;
	String hoja = "Datos";

	@SuppressWarnings("deprecation")
	public void leerArchivo(){
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				double x=0;
				double y=0;
				String nombre="";
				int tipo=0;
				for(int i=0; i<5; i++) {
					cell = cellIterator.next();
					if(i==1) {
						x=cell.getNumericCellValue();
					}
					if(i==2) {
						y=cell.getNumericCellValue();
					}
					if(i==3) {
						nombre=cell.getStringCellValue();
					}
					if(i==4) {
						tipo=(int) cell.getNumericCellValue();
					}	
				}
				Destino.agregarUbicacion(new Ubicacion(x, y, nombre, tipo));
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

}