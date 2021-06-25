package kodlama.io.hrms.core.utilities.adapters;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.ErrorDataResult;
import kodlama.io.hrms.core.utilities.results.SuccessDataResult;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryManagerAdapter implements CloudinaryService {
	
	@Override
	 public String upload(MultipartFile file) {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
	                "cloud_name", "busrauylas",
	                "api_key", "973386927549849",
	                "api_secret", "_XRo_4wwhAOWQKHjjveWyw7c9Qw"));
	        try {
	            File uploadedFile = convertMultiPartToFile(file);
	            Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
	            return uploadResult.get("url").toString();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	}
	
	 private File convertMultiPartToFile(MultipartFile file) throws IOException {
		 File convFile = new File(file.getOriginalFilename());
	     FileOutputStream fos = new FileOutputStream(convFile);
	     fos.write(file.getBytes());
	     fos.close();
	     return convFile;
	 }
}