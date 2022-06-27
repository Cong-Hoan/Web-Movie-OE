package edu.poly.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

public class UploadUtils {
	public static String processUploadField(String fieldName, HttpServletRequest request, String storedFolder,String storedFileName) throws IOException, ServletException {
		Part filePart = request.getPart(fieldName);//lay tt truong dc upload
		
		if(filePart == null || filePart.getSize() == 0) {
			return "";
		}
		
		if(storedFolder == null) {//ts truyen vao pt
			storedFolder = "/uploads";//de gt ngam dinh
		}
		
		if(storedFileName == null) {//kt neu store = rong tham so truyen vao 
			storedFileName = Path.of(filePart.getSubmittedFileName()).getFileName().toString();//=0 thi lay ten file + mr dc upload toi sever
		}else {
			storedFileName += "." + FilenameUtils.getExtension(Path.of(filePart.getSubmittedFileName()).toString());
		}
		
		String uploadFolder = request.getServletContext().getRealPath(storedFolder);
		
		Path uploadPath = Paths.get(uploadFolder);
		
		if(!Files.exists(uploadPath)) {//kt upload path 
			Files.createDirectory(uploadPath);
		}
		
		filePart.write(Paths.get(uploadPath.toString(), storedFileName).toString());// neu o ton tai thi tao ra thu muc 
		
		return storedFileName;
	}
}
