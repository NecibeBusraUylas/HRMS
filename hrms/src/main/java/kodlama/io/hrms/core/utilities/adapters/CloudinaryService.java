package kodlama.io.hrms.core.utilities.adapters;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	 String upload(MultipartFile file);
}